package org.fp.thirdparty.scalac.Overview_of_free_monads_in_cats.snippets

/**
  *
  */
trait API02 extends API01 {

  import Logo._
  import cats.free.Free

  def forward(pos: Position, l: Int): Free[Instruction, Position] = Free.liftF(Forward(pos, l))
  def backward(pos: Position, l: Int): Free[Instruction, Position] = Free.liftF(Backward(pos, l))
  def left(pos: Position, degree: Degree): Free[Instruction, Position] = Free.liftF(RotateLeft(pos, degree))
  def right(pos: Position, degree: Degree): Free[Instruction, Position] = Free.liftF(RotateRight(pos, degree))
  def showPosition(pos: Position): Free[Instruction, Unit] = Free.liftF(ShowPosition(pos))

}

// 8<--
object API02 extends API02
// 8<--