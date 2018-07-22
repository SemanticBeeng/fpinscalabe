package io.yields.platform.orchestration.shared

import org.fp.concepts._
import org.fp.bookmarks._
///
import org.specs2.ugbase.UserGuidePage

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Random


object Spark_fp_mix extends UserGuidePage {

  import cats._
  import cats.data._
  import cats.implicits._
  import monix.eval.Coeval
  import monix.execution.Scheduler.Implicits.global
  import monix.eval.Task
  import org.apache.spark.sql.{DataFrame, SparkSession, functions ⇒ f}

  def buildSession: Task[SparkSession] = Task.eval {
    SparkSession
      .builder()
      .appName("Retaining Sanity with Spark")
      .master("local")
      .config("spark.scheduler.mode", "FAIR")
      .getOrCreate()
  }

  def createDF(data: List[Int])(implicit session: SparkSession): Task[DataFrame] = Task.eval {
    import session.implicits._
    val rdd = session.sparkContext.parallelize(data)

    rdd.toDF
  }

  /**
    * https://hyp.is/rKfrHozBEeiGIneTLZPptA/github.com/iravid/blog-posts/blob/master/fp-and-spark.org
    */
  implicit class CoevalOps[A](thunk: Coeval[A]) {

    def onPool(pool: String)(implicit session: SparkSession): Task[A] =
      Coeval(session.sparkContext.setLocalProperty("spark.scheduler.pool", pool))
        .flatMap(_ => thunk)
        .doOnFinish(_ => Coeval(session.sparkContext.setLocalProperty("spark.scheduler.pool", null)))
        .task
  }

  /**
    *
    */
  case class Timings(data: Map[String, FiniteDuration])

  type TimedTask[A] = WriterT[Task, Timings, A]

  implicit class TaskOps[A](task: Task[A]) {

    def timed(key: String): TimedTask[A] =
      WriterT {
        for {
          startTime <- Task.eval(System.currentTimeMillis().millis)
          result <- task
          endTime <- Task.eval(System.currentTimeMillis().millis)
        } yield (Timings(Map(key -> (endTime - startTime))), result)
      }

    def untimed: TimedTask[A] =
      WriterT(task.map((Monoid[Timings].empty, _)))
  }

  implicit val timingsMonoid: Monoid[Timings] = new Monoid[Timings] {
    def empty: Timings = Timings(Map.empty)

    def combine(x: Timings, y: Timings): Timings = Timings(x.data ++ y.data)
  }

  implicit class TimedTaskOps[A](task: TimedTask[A]) {

    def logTimings(heading: String): Task[A] =
      for {
        resultAndLog <- task.run
        (log, result) = resultAndLog
        _ <- Task.eval {
          println {
            List(
              s"${heading}:",
              log.data.map {
                case (entry, duration) =>
                  s"\t${entry}: ${duration.toMillis.toString}ms"
              }
                .toList
                .mkString("\n"),
              s"\tTotal: ${log.data.values.map(_.toMillis).sum}ms"
            ).mkString("\n")
          }
        }
      } yield result
  }

  def computeAvg1(df: DataFrame, pool: String)(implicit session: SparkSession): Task[Double] =
    Task.eval {
      session.sparkContext.setLocalProperty("spark.scheduler.pool", pool)
      val result = df.agg(f.avg("value")).head().getDouble(0)
      session.sparkContext.setLocalProperty("spark.scheduler.pool", null)

      result
    }

  def computeAvg2(df: DataFrame)(implicit session: SparkSession): Coeval[Double] =
    Coeval(df.agg(f.avg("value")).head().getDouble(0))

  /**
    *
    */
  def is = s"Scala and FP - do they mix".title ^ s2"""

  Wrapping Spark with $monadicComposition :

  ${snippet{
  /**
    *
    * @return
    */
  def program1: Task[Double] = for {
    sparkSession <- buildSession
    result <- {
      implicit val session = sparkSession
      import scala.util.Random

      val data = List.fill(100)(Random.nextInt)

      for {
        df <- createDF(data) //.timed("DataFrame creation")
        avg <- computeAvg1(df, "pool") //.onPool("pool").timed("Average computation")
      } yield avg
    }
  } yield result

  Await.result(program1.runAsync, 30.seconds)

  def program2(data: List[Int])(implicit session: SparkSession): TimedTask[Double] =
    for {
      df <- createDF(data).timed("DataFrame creation")
      avg <- computeAvg2(df).onPool("pool").timed("Average computation")
    } yield avg


  val composed =
    for {
      session ← buildSession
      fst <- program2(List.fill(100)(Random.nextInt))(session).logTimings("First Program")
      snd <- program2(List.fill(100)(Random.nextInt))(session).logTimings("Second Program")
    } yield ()

  Await.result(composed.runAsync, 30.seconds)
  }}
  """
}
