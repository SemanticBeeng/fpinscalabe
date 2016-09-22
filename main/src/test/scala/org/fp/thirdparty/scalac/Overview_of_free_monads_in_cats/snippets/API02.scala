package org.fp.thirdparty.scalac.Overview_of_free_monads_in_cats.snippets

/**
  *
  */
trait API02 extends API01 {

//  // 8<--
//  /**
//    * @todo had to duplicate this here because of visibility
//    */
//  object Logo {
//    sealed trait Instruction[A]
//    case class Forward(position: Position, length: Int) extends Instruction[Position]
//    case class Backward(position: Position, length: Int) extends Instruction[Position]
//    case class RotateLeft(position: Position, degree: Degree) extends Instruction[Position]
//    case class RotateRight(position: Position, degree: Degree) extends Instruction[Position]
//    case class ShowPosition(position: Position) extends Instruction[Unit]
//  }
//
//  import Logo._
//  // 8<--

  import Logo._
  import cats.free.Free

  def forward(pos: Position, l: Int): Free[Instruction, Position] = Free.liftF(Forward(pos, l))
  def backward(pos: Position, l: Int): Free[Instruction, Position] = Free.liftF(Backward(pos, l))
  def left(pos: Position, degree: Degree): Free[Instruction, Position] = Free.liftF(RotateLeft(pos, degree))
  def rightM(pos: Position, degree: Degree): Free[Instruction, Position] = Free.liftF(RotateRight(pos, degree))
  def showPosition(pos: Position): Free[Instruction, Unit] = Free.liftF(ShowPosition(pos))

}

// 8<--
object API02 extends API02
//object Logo extends API02.Logo
// 8<--