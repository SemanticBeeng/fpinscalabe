package org.fp.thirdparty.scalac.Overview_of_free_monads_in_cats

import org.fp.thirdparty.scalac.Overview_of_free_monads_in_cats.snippets.API02._

object Computations {
  import Base._

  def forward(pos: Position, l: Int): Position = pos.copy(
    x=pos.x + l*math.cos(pos.heading.value * math.Pi/180.0),
    y=pos.y + l*math.sin(pos.heading.value * math.Pi/180.0))

  def backward(pos: Position, l: Int): Position = pos.copy(
    x=pos.x - l*math.cos(pos.heading.value * math.Pi/180.0),
    y=pos.y - l*math.sin(pos.heading.value * math.Pi/180.0))

  def left_(pos: Position, d: Degree): Position = pos.copy(
    heading=Degree(pos.heading.value + d.value))

  def right_(pos: Position, d: Degree): Position = pos.copy(
    heading=Degree(pos.heading.value - d.value))

  def showPosition(pos: Position) =
    print(s"Position $pos")
}
