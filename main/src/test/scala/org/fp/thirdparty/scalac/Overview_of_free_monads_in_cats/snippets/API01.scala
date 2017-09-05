package org.fp.thirdparty.scalac.Overview_of_free_monads_in_cats.snippets

/**
  *
  */
trait API01 {

  trait Base {
    case class Position(x: Double, y: Double, heading: Degree)
    case class Degree(private val d: Int) {
      val value = d % 360
    }
  }

  trait LogoInstructions {
    import Base._

    sealed trait Instruction[A]
    case class Forward(position: Position, length: Int) extends Instruction[Position]
    case class Backward(position: Position, length: Int) extends Instruction[Position]
    case class RotateLeft(position: Position, degree: Degree) extends Instruction[Position]
    case class RotateRight(position: Position, degree: Degree) extends Instruction[Position]
    case class ShowPosition(position: Position) extends Instruction[Unit]
  }

  // 8<--
  object LogoInstructions extends LogoInstructions
  object Base extends Base
  // 8<--
}

// 8<--
object API01 extends API01
// 8<--
