package org.fp.thirdparty.scalac.Overview_of_free_monads_in_cats.snippets

import scala.language.higherKinds


/**
  *
  */
trait API03 {

  import API02._
  import cats.InjectK
  import cats.free.Free

  trait LogoPencilInstructions {

    import Base._

    sealed trait PencilInstruction[A]
    case class PencilUp(position: Position) extends PencilInstruction[Unit]
    case class PencilDown(position: Position) extends PencilInstruction[Unit]
   }

   object dsl {

      import Base._
      import LogoInstructions._
      import LogoPencilInstructions._

      class Moves[F[_]](implicit I: InjectK[Instruction, F]) {
        def forward(pos: Position, l: Int): Free[F, Position] = Free.inject[Instruction, F](Forward(pos, l))
        def backward(pos: Position, l: Int): Free[F, Position] = Free.inject[Instruction, F](Backward(pos, l))
        def left(pos: Position, l: Degree): Free[F, Position] = Free.inject[Instruction, F](RotateLeft(pos, l))
        def right(pos: Position, l: Degree): Free[F, Position] = Free.inject[Instruction, F](RotateRight(pos, l))
        def showPosition(pos: Position): Free[F, Unit] = Free.inject[Instruction, F](ShowPosition(pos))
      }

      object Moves {
        implicit def moves[F[_]](implicit I: InjectK[Instruction, F]): Moves[F] = new Moves[F]
      }

      class PencilActions[F[_]](implicit I: InjectK[PencilInstruction, F]) {
        def pencilUp(pos: Position): Free[F, Unit] = Free.inject[PencilInstruction, F](PencilUp(pos))
        def pencilDown(pos: Position): Free[F, Unit] = Free.inject[PencilInstruction, F](PencilDown(pos))
      }

      object PencilActions {
        implicit def pencilActions[F[_]](implicit I: InjectK[PencilInstruction, F]): PencilActions[F] = new PencilActions[F]
      }
    }

    // 8<--
    object LogoPencilInstructions extends LogoPencilInstructions
    // 8<--
}

// 8<--
object API03 extends API03
// 8<--
