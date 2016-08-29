package org.fp.studies.fp

import org.fp.concepts._
import org.fp.resources._
import org.fp.bookmarks._

//
import org.specs2.specification.dsl.mutable.{AutoExamples, TextDsl}

/**
  * [[disjunction]] in [[Scalaz]]
  *
  */
object Spec2 extends org.specs2.mutable.Specification with AutoExamples with TextDsl {

  s"$bookmarks: $ann_ScalazDisjunction1"
  s2"""$keyPoint Disjunction - aka ${Scalaz.md} Either `\/[A, B]` is an alternative to `Either[A, B]`.
     |
       | * `-\/` is Left  (usually represents failure by convention)
     | * `\/-` is Right (usually represents success by convention)
     |
       |Left or Right - which side of the $disjunction does the "-" appear?
     |
       |Prefer infix notation to express $disjunction type `v: String \/ Double`
     |
       |References
     |- http://eed3si9n.com/learning-scalaz/Either.html @todo
     |
       |A common use of a $disjunction is to explicitly represent the possibility of failure in a result as opposed to throwing an
     |exception. By convention, the `Left -\/` is used for errors and the right `\/-` is reserved for successes.
     |
       |For example, a function that attempts to parse an integer from a string may have a return type of `\/[NumberFormatException, Int]`.
     |
       |However, since there is no need to actually throw an exception, the type `A` chosen for the "left" could be any type representing
     |an error and has no need to actually extend Exception
     |
       |`\/[A, B]` is isomorphic to `scala.Either[A, B]`, but `\/` is is right-biased, so methods such as `$operator_map` and `$operator_flatMap`
     |apply only in the context of the "right" case. This right bias makes `\/` more convenient to use than `scala.Either` in a $monadicContext.
     |
       |
       |Methods such as $operator_swap, $operator_swapped, and $operator_leftMap provide functionality that `scala.Either` exposes through $leftProjection.
     |
       |`\/[A, B]` is also isomorphic to `Validation[A, B]`. The subtle but important difference is that $applicativeFunctor instances for `Validation`
     |accumulates errors ("lefts") while $applyFunctor instances for `\/` "fail fast" on the first "left" they evaluate.
     |
       |This fail-fast behavior allows `\/` to have lawful $monad instances that are consistent with their $applicativeFunctor instances, while `Validation` cannot.
     |
       """.stripMargin.p

  s2"""Builders:
       |- using the disjunction singleton instances \/- and -\/
       |- left method
       |- right method
       |- fromEither method
       |""".stripMargin.p
  eg {
    import scalaz.{\/, -\/, \/-}

    \/-("right") must beAnInstanceOf[\/-[String]]
    \/-("right") must_== \/-("right")

    \/.right("right") must beAnInstanceOf[Nothing \/ String]
    \/.right("right") must_== \/-("right")

    -\/("left") must beAnInstanceOf[-\/[String]]
    -\/("left") must_== -\/("left")

    \/.left("left") must beAnInstanceOf[String \/ Nothing]
    \/.left("left") must_== -\/("left")
  }

  s2"""There is an isomorphism between `Either[A, B]` and `\/[A, B]`.""".p
  s2"""From `\/[A, B]` to `Either[A, B]`.""".p
  eq {
    import scalaz.{\/, -\/, \/-}

    val e_right = \/.fromEither(Right("right"))

    e_right must beAnInstanceOf[Nothing \/ String]
    e_right must_== \/-("right")

    e_right.toEither must beAnInstanceOf[Either[Nothing, String]]
    e_right.toEither must_== Right("right")

    val e_left = \/.fromEither(Left("left"))

    e_left must beAnInstanceOf[String \/ Nothing]
    e_left must_== -\/("left")

    e_left.toEither must beAnInstanceOf[Either[String, Nothing]]
    e_left.toEither must_== Left("left")
  }

  s2"""From `Either[A, B]` to `\/[A, B]`.""".p
  eg {
    import scalaz.{\/, -\/, \/-}
    import scalaz.syntax.std.either._

    Left("left").disjunction must beAnInstanceOf[String \/ Nothing]
    Left("left").disjunction must_== -\/("left")

    Right("right").disjunction must beAnInstanceOf[Nothing \/ String]
    Right("right").disjunction must_== \/-("right")
  }

  s2"""From `Try[A]` to `\/[Throwable, A]`.""".p
  eg {
    import scalaz.{\/, -\/}

    val r_excp: RuntimeException = new scala.RuntimeException("runtime error")
    val e1 = \/.fromTryCatchNonFatal[Int](throw r_excp)

    e1 must beAnInstanceOf[Throwable \/ Int]
    e1 must_== -\/(r_excp)

    val excp = new java.lang.ArithmeticException()
    def div(a: Int, b: Int): Int = b match {
      case 0 => throw excp
      case _ => a / b
    }

    val e2 = \/.fromTryCatchNonFatal[Int](div(1, 0))

    e2 must beAnInstanceOf[Throwable \/ Int]
    e2 must beEqualTo(-\/(excp))
  }

  s2"""Implicit conversion to `\/[A, Nothing]` and `\/[A, B]`.""".p
  eg {
    import scalaz.{\/, -\/, \/-}
    import scalaz.syntax.either._

    "right".right must beAnInstanceOf[Nothing \/ String]
    "right".right must_== \/-("right")

    "left".left must beAnInstanceOf[String \/ Nothing]
    "left".left must_== -\/("left")

    "right".right[Int] must beAnInstanceOf[Int \/ String]
    "right".right[Int] must_== \/-("right")

    "left".left[Double] must beAnInstanceOf[String \/ Double]
    "left".left[Double] must_== -\/("left")
  }

  s2"""Use of `\/[A, B]` in $forComprehension-s.""".p
  eg {
    import scalaz.{\/, -\/, \/-}
    import scalaz.syntax.either._

    val fc1 = for {
      a <- "a".right[String]
      b <- "b".right[String]

    } yield (a, b)

    fc1 must beAnInstanceOf[String \/ (String, String)]
    fc1 must_== \/-(("a", "b"))

    val fc2 = for {
      a <- "a".right[String]
      e <- "e".left[Int]
    } yield (a, e)

    fc2 must beAnInstanceOf[String \/ (String, Int)]
    fc2 must_== -\/("e")

    val fc3 = for {
      a <- "a".right[String]
      e <- "e".left[Int]
      f <- "f".left[Int]
    } yield (a, e, f)

    fc3 must beAnInstanceOf[String \/ (String, Int, Int)]
    fc3 must_== -\/("e")
  }

  s2"""From `Option[A]` to `\/[E, A]`
       |`\/>[E]`
       |`toRightDisjunction[E](e: => E): E \/ A = o.toRight(self)(e)`
       |`toLeftDisjunction[A]`
       |`<\/[A]`
      """.stripMargin.p
  eg {
    import scalaz._
    import syntax.std.option._

    Some(10) \/> "message" must beAnInstanceOf[String \/ Int]
    Some(10) \/> "message" must_== \/-(10)

    Some(10).toRightDisjunction("message") must beAnInstanceOf[String \/ Int]
    Some(10).toRightDisjunction("message") must_== \/-(10)

    //@todo None \/> "message" must beAnInstanceOf[\/[String, A]]
    None \/> "message" must_== -\/("message")

    //@todo None.toRightDisjunction("message") must beAnInstanceOf[\/[String, A]]
    None.toRightDisjunction("message") must_== -\/("message")

    Some(10) <\/ "message" must beAnInstanceOf[Int \/ String]
    Some(10) <\/ "message" must_== -\/(10)

    Some(10).toLeftDisjunction("message") must beAnInstanceOf[Int \/ String]
    Some(10).toLeftDisjunction("message") must_== -\/(10)

    //@todo None <\/ "message" must beAnInstanceOf[\/[A, String]]
    None <\/ "message" must_== \/-("message")

    //@todo None.toLeftDisjunction("message") must beAnInstanceOf[\/[A, String]]
    None.toLeftDisjunction("message") must_== \/-("message")
  }

  eg {
    import scalaz.{\/, -\/, \/-}
    import scalaz.std.list._
    import scalaz.syntax.traverse._
    import scalaz.syntax.either._

    def f(x: Int): \/[String, Int] = if (x > 2) x.right[String] else "failure".left[Int]

    List(1, 2, 3).traverseU(f) must beAnInstanceOf[String \/ List[Int]]
    List(1, 2, 3).traverseU(f) must_== -\/("failure")

    List(3, 4, 5).traverseU(f) must beAnInstanceOf[String \/ List[Int]]
    List(3, 4, 5).traverseU(f) must_== \/-(List(3, 4, 5))

    List(3, 4, 5).map(f).sequenceU must beAnInstanceOf[String \/ List[Int]]
    List(3, 4, 5).map(f).sequenceU must_== \/-(List(3, 4, 5))

    List(1, 2, 3).map(f).sequenceU must beAnInstanceOf[String \/ List[Int]]
    List(1, 2, 3).map(f).sequenceU must_== -\/("failure")
  }

  s"$bookmarks: $ann_ScalazValidation1"
  s2"""Validation""".p
  eg {
    import scalaz.{\/, -\/, \/-, ValidationNel, NonEmptyList}

    import java.time.LocalDate

    case class Musician(name: String, born: LocalDate)

    object Errors {
      val NAME_EMPTY = "name: cannot be empty."
      val AGE_TOO_YOUNG = "age: too young."
    }

    def validate(musician: Musician): ValidationNel[String, Musician] = {
      import Errors._
      import scalaz.Scalaz._

      def validName(name: String): ValidationNel[String, String] =
        if (name.isEmpty) NAME_EMPTY.failureNel
        else name.success

      def validateAge(born: LocalDate): ValidationNel[String, LocalDate] =
        if (born.isAfter(LocalDate.now().minusYears(12))) AGE_TOO_YOUNG.failureNel
        else born.success

      s"$validation in an $applicativeFunctor: many can be composed or chained together".p
      s"$bookmarks: $ann_ScalazValidation2"
      (validName(musician.name) |@| validateAge(musician.born)) ((_, _) => musician)
    }

    val either: \/[String, Musician] = \/-(Musician("", LocalDate.now().minusYears(11)))

    val r = either match {
      case \/-(value) => either.@\?/(_ => validate(value))
      case _ => \/-(Musician("", LocalDate.now()))
    }

    //@todo improve the error collection
    s"If any failure in the chain, failure wins: All errors get appended together".p
    r must_== -\/(NonEmptyList(Errors.NAME_EMPTY, Errors.AGE_TOO_YOUNG))
  }

/*
  s"$bookmarks: $ann_ScalazValidation3"
  eg {
    import scalaz._
    import Scalaz._
    import scalaz.Validation.FlatMap._

    def loadCsv(): List[String] = {
      List("1,me,3,4", "2,he,0,100", "No3,aaaaaaaaaaaaaaaa,1,10")
    }

    case class ScoreRange(min: Int, max: Int)
    case class Entity(id: Long, name: String, scoreRange: ScoreRange)

    def batchUpdate(entities: List[Entity]) = println(entities)
    def outputErrors(errors: List[String]) = println(errors)
    def validate(records: List[String]): ValidationNel[String, List[Entity]] = {
      records.foldMap { record =>
        for {
          columns <- validateColumn(record)
          entity <- validateEntity(columns)
        } yield List(entity)
      }
    }

    //----------
    def validate2(record: String): ValidationNel[ErrorInfo, Entity] = {
      (for {
        columns <- validateColumn(record)
        entity <- validateEntity(columns)
      } yield entity) leftMap { e =>
        (e.toList, record).wrapNel
      }
    }
    //----------

    def validateColumn(record: String): ValidationNel[String, Array[String]] = {
      val columns = record.split(",")
      if (columns.size == 4) columns.successNel
      else "less columns".failureNel
    }
    def validateEntity(col: Array[String]): ValidationNel[String, Entity] = {
      (validateId(col(0)) |@|
        validateName(col(1)) |@|
        validateScoreRange(col(2), col(3))) (Entity)
    }
    def validateId(id: String): ValidationNel[String, Long] = {
      Validation.fromTryCatchNonFatal(id.toLong).leftMap(_ => NonEmptyList("invalid id"))
    }
    def validateName(name: String): ValidationNel[String, String] = {
      if (name.size <= 10) name.successNel
      else "name too long".failureNel
    }
    def validateScoreNum(num: String, column: String): ValidationNel[String, Int] = {
      Validation.fromTryCatchNonFatal(num.toInt).leftMap(_ => NonEmptyList(s"invalid $column"))
    }
    def validateMinMax(min: String, max: String): ValidationNel[String, (Int, Int)] = {
      (validateScoreNum(min, "min") |@| validateScoreNum(max, "max")) ((x, y) => (x, y))
    }
    def validateScoreRangeConstraint(min: Int, max: Int): ValidationNel[String, ScoreRange] = {
      if (min <= max) ScoreRange(min, max).successNel
      else "min is grater than max".failureNel
    }
    def validateScoreRange(min: String, max: String): ValidationNel[String, ScoreRange] = {
      validateMinMax(min, max).flatMap { case (n, x) => validateScoreRangeConstraint(n, x) }
    }

    val records: List[String] = loadCsv()
    val validated: ValidationNel[String, List[Entity]] = validate(records)

    println("== validated1 ==")
    validated match {
      case Success(entities) => batchUpdate(entities)
      case Failure(errors) => outputErrors(errors.toList)
    }

    type ErrorInfo = (List[String], String)
    val validated2: List[ValidationNel[ErrorInfo, Entity]] = records.map(validate2)
    val results2: (List[ErrorInfo], List[Entity]) = validated2.foldMap {
      case Success(s) => (Nil, List(s))
      case Failure(f) => (f.toList, Nil)
    }
    println("== validated2 ==")
    println("errors:")
    println(results2._1)
    println("entities:")
    println(results2._2)

    //  object X {
    //object X extends App {
    import scalaz.std.list._
    import scalaz.std.tuple._
    import scalaz.syntax.foldable._

    // def tuple2[A, B](ma: Monoid[A], mb: Monoid[B]): Monoid[(A, B)]
    // new Monoid[(A, B)] {
    //   def zero: (A, B) = (ma.zero, mb.zero)
    //   def append(x: (A, B), y: (A, B)): (A, B) = {
    //     (ma.append(x._1, y._1), mb.append(x._2, y._2))
    //   }
    // }

    case class Product(name: String)

    case class Item(name: String)

    val products: List[Product] = List(
      Product("foo"),
      Product("bar")
    )

    def createItem(product: Product): Item = Item(product.name)

    def createCodes(name: String, item: Item): List[String] = List(name)

    // val allItems = products.foldMap { p => List(createItem(p)) }
    // val allCodes = products.foldMap { p =>
    //   createCodes(p.name, createItem(p))
    // }

    val (allItems, allCodes) = products.foldMap { p =>
      val item = createItem(p)
      (List(item), createCodes(p.name, item))
    }

    println(allItems)
    println(allCodes)

    allItems must_== List()
    //1 must_== 1
  }
*/

  /**
    * @todo http://codegists.com/snippet/scala/scalaz-validationscala_animatedlew_scala
    * @todo http://codegists.com/snippet/scala/scalaz-nelscala_manjuraj_scala
    * @todo http://codegists.com/snippet/scala/scalaz-examplesscala_rodoherty1_scala
    * @todo http://codegists.com/snippet/scala/scalaz-monad-transformersscala_matterche_scala
    * @todo http://codegists.com/snippet/scala/scalaz-streams-task-esscala_taisukeoe_scala
    */
}