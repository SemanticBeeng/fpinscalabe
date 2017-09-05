package org.fp.thirdparty.scalac.Overview_of_free_monads_in_cats.snippets


import scala.language.{higherKinds, implicitConversions}

/**
  *
  */
trait API02 extends API01 {

  import cats.free.Free
  import cats.{Id, InjectK}

  object dsl {
    import Base._
    import LogoInstructions._

    class Moves[F[_]](implicit I: InjectK[Instruction, F]) {
      def forward(pos: Position, l: Int): Free[F, Position] = Free.inject[Instruction, F](Forward(pos, l))
      def backward(pos: Position, l: Int): Free[F, Position] = Free.inject[Instruction, F](Backward(pos, l))
      def left(pos: Position, l: Degree): Free[F, Position] = Free.inject[Instruction, F](RotateLeft(pos, l))
      def right_(pos: Position, l: Degree): Free[F, Position] = Free.inject[Instruction, F](RotateRight(pos, l))
      def showPosition(pos: Position): Free[F, Unit] = Free.inject[Instruction, F](ShowPosition(pos))
    }

    object Moves {
      implicit def moves[F[_]](implicit I: InjectK[Instruction, F]): Moves[F] = new Moves[F]
    }
  }
}

// 8<--
object API02 extends API02
// 8<--