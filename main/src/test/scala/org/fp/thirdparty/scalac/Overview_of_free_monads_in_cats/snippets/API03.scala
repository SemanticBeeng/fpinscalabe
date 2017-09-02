package org.fp.thirdparty.scalac.Overview_of_free_monads_in_cats.snippets

import scala.language.higherKinds

/**
  *
  */
trait API03 extends API02 {

  import cats.free.Free
  import cats.Inject

//  object Logo extends API02.Logo {

    //object dsl {
/*@todo
      sealed trait PencilInstruction[A]
      case class PencilUp(position: Position) extends PencilInstruction[Unit]
      case class PencilDown(position: Position) extends PencilInstruction[Unit]

      class Moves[F[_]](implicit I: Inject[Instruction[_], F]) {
        //def forward(pos: Position, l: Int): Free[Instruction, Position] = Free.liftF(Forward(pos, l))
        def forward(pos: Position, l: Int): Free[F, Position] = Free.inject[Instruction, F[Instruction[Position]]](Forward(pos, l))
        def backward(pos: Position, l: Int): Free[F, Position] = Free.inject[Instruction, F[Instruction[Position]]](Backward(pos, l))
        def left(pos: Position, degree: Degree): Free[F, Position] = Free.inject[Instruction, F[Instruction[Position]]](RotateLeft(pos, degree))
        def right(pos: Position, degree: Degree): Free[F, Position] = Free.inject[Instruction, F[Instruction[Position]]](RotateRight(pos, degree))
        def showPosition(pos: Position): Free[F, Unit] = Free.inject[Instruction, F](ShowPosition(pos))
      }

      object Moves {
        implicit def moves[F[_]](implicit I: Inject[Instruction[_], F[_]]): Moves[F] = new Moves[F]
      }

      class PencilActions[F[_]](implicit I: Inject[PencilInstruction[_], F[_]]) {
        def pencilUp(pos: Position): Free[F, Unit] = Free.inject[PencilInstruction, F](PencilUp(pos))
        def pencilDown(pos: Position): Free[F, Unit] = Free.inject[PencilInstruction, F](PencilDown(pos))
      }

      object PencilActions {
        implicit def pencilActions[F[_]](implicit I: Inject[PencilInstruction, F]): PencilActions[F] = new PencilActions[F]
      }
*/
    //}
//  }

  // 8<--
  //object Logo extends Logo
  // 8<--
}

// 8<--
object API03 extends API03
// 8<--
