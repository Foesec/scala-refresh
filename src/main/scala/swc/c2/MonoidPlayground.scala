package com.flxkbr.swc.c2

import cats.Monoid
import cats.instances.int._
import cats.instances.option._
import cats.instances.string._
import cats.syntax.semigroup._

case class Order(totalCost: Double, quantity: Double)

object Order {
  implicit val orderMonoid = new Monoid[Order] {
    def empty: Order = Order(0, 0)
    def combine(x: Order, y: Order): Order = 
      Order(x.totalCost+y.totalCost, x.quantity+y.quantity)
  }
}

object MonoidPlayground {
  def main(args: Array[String]) {
    val o1 = Order(20, 3)
    val o2 = Order(200, 15)

    val orders = List(o1, o2)

    println(add(orders))
  }

  def add[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.fold(monoid.empty)(_ |+| _)
}