package org.specs2.thirdparty.blogs.btlines

import cats.{Id, Monad, Traverse, ~>}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.language.higherKinds

/**
  * #resource https://gist.github.com/btlines/2403b10db3cd140423e4b8666c03b45f
  */
object TagLessExample extends App {

  case class PurchaseOrderId(value: String)
  case class ProductId(value: String)
  case class LocationId(value: String)

  trait Stock[F[_]] {
    def poContent(poId: PurchaseOrderId): F[Map[ProductId, Int]]
    def createStock(productId: ProductId, locationId: LocationId, quantity: Int): F[Unit]
    def moveStock(productId: ProductId, source: LocationId, destination: LocationId, quantity: Int): F[Unit]
  }

  trait Inventory[F[_]] {
    def increment(locationId: LocationId, productId: ProductId, quantity: Int): F[Unit]
    def decrement(locationId: LocationId, productId: ProductId, quantity: Int): F[Unit]
  }

  trait Logging[F[_]] {
    def error(message: String): F[Unit]
    def info(message: String): F[Unit]
  }

  trait Storage[F[_]] {
    def get[K, V](key: K): F[Option[V]]
    def put[K, V](key: K, value: V): F[Unit]
  }

  val consoleLogger = new Logging[Id] {
    override def error(message: String): Id[Unit] =
      println(s"[ERROR] $message")
    override def info(message: String): Id[Unit] =
      println(s"[INFO ] $message")
  }

  val keyValueStore = new Storage[Future] {
    var store = Map.empty[Any, Any]
    override def get[K, V](key: K): Future[Option[V]] =
      Future.successful(store.get(key).asInstanceOf[Option[V]])
    override def put[K, V](key: K, value: V): Future[Unit] =
      Future.successful(store += key -> value)
  }

//  val keyValueStore = new Storage[Id] {
//    var store = Map.empty[Any, Any]
//    override def get[K, V](key: K): Id[Option[V]] =
//      store.get(key).asInstanceOf[Option[V]]
//    override def put[K, V](key: K, value: V): Id[Unit] =
//      store += key -> value
//  }

  implicit class LiftTo[F[_], A](elem: F[A]) {
    def liftTo[G[_]](implicit trans: F ~> G): G[A] = trans(elem)
  }

  def inventory[F[_], G[_], H[_]](
    logger: Logging[F],
    store: Storage[G]
  )(implicit
    storeM: Monad[G],
    transToG: F ~> G,
    transToH: G ~> H
  ) = new Inventory[H] {
    private def stockAt(locationId: LocationId, productId: ProductId): G[Int] =
      storeM.map(store.get[(LocationId, ProductId), Int](locationId -> productId))(_ getOrElse 0)

    override def increment(locationId: LocationId, productId: ProductId, quantity: Int): H[Unit] = {
      import cats.Monad.ops._
      for {
        _ <- logger.info(s"Add $quantity $productId at $locationId").liftTo[G]
        existing <- stockAt(locationId, productId)
        _ <- store.put(locationId -> productId, existing + quantity)
      } yield ()
    }.liftTo[H]

    override def decrement(locationId: LocationId, productId: ProductId, quantity: Int): H[Unit] = {
      import cats.Monad.ops._
      for {
        _ <- logger.info(s"Remove $quantity $productId from $locationId").liftTo[G]
        existing <- stockAt(locationId, productId)
        _ <- store.put(locationId -> productId, existing - quantity)
      } yield ()
    }.liftTo[H]
  }

  def stock[F[_], G[_]](inventory: Inventory[F])(implicit
    inventoryM: Monad[F],
    stockM: Monad[G],
    transToG: F ~> G
  ): Stock[G] = new Stock[G] {
    import cats.Monad.ops._
    override def poContent(poId: PurchaseOrderId): G[Map[ProductId, Int]] = {
      stockM.pure(Map(ProductId("Mars") -> 200, ProductId("Milkyway") -> 150, ProductId("Galaxy") -> 100))
    }
    override def createStock(productId: ProductId, locationId: LocationId, quantity: Int): G[Unit] = {
      inventory.increment(locationId, productId, quantity).liftTo[G]
    }
    override def moveStock(productId: ProductId, source: LocationId, destination: LocationId, quantity: Int): G[Unit] = {
      inventory
        .decrement(source, productId, quantity)
        .flatMap(_ => inventory.increment(source, productId, quantity))
        .liftTo[G]
    }
  }

  def inbound[F[_], G[_]](logger: Logging[F], stock: Stock[G])(poId: PurchaseOrderId)(implicit stockM: Monad[G], transToG: F ~> G, traverse: Traverse[List]): G[Unit] = {
    import cats.Monad.ops._
    import cats.Traverse.ops._
    for {
      _ <- logger.info(s"Inbounding $poId").liftTo[G]
      content <- stock.poContent(poId)
      _ <- traverse.traverse(content.toList) {
        case (productId, quantity) =>
          stock.createStock(productId, LocationId("dock"), quantity)
      }
      _ <- traverse.traverse(content.toList) {
        case (productId, quantity) =>
          stock.moveStock(productId, LocationId("dock"), LocationId("warehouse"), quantity)
      }
    } yield ()
  }

  import cats.instances.future._

  implicit def identityTransform[T[_]] = new (T ~> T) {
    override def apply[A](fa: T[A]): T[A] = fa
  }
  implicit def monadTransform[T[_] : Monad] = new (Id ~> T) {
    override def apply[A](a: Id[A]): T[A] = Monad[T].pure(a)
  }

  implicit val executor = scala.concurrent.ExecutionContext.global
  val inventoryService: Inventory[Future] = inventory(consoleLogger, keyValueStore)
  val stockService: Stock[Future] = stock(inventoryService)
  import cats.instances.list._
  val res = inbound(consoleLogger, stockService)(PurchaseOrderId("po-1"))
  Await.result(res, Duration.Inf)
}
