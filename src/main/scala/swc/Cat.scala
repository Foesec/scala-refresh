package com.flxkbr.swc

import cats._
import cats.implicits._

final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val catShow: Show[Cat] =
    Show.show(cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat.")

  implicit val catEq: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>
      (cat1.name === cat2.name) &&
      (cat1.age === cat2.age) &&
      (cat1.color === cat2.color)
    }

  def main(args: Array[String]): Unit = {
    val cat1 = Cat("one", 1, "black")
    val cat2 = Cat("two", 2, "red")

    println("Equals " + (cat1 === cat1))
    println("Not equals" + (cat1 =!= cat2))

    println("Compare ints: " + (1 === 1))
    println("Compare ints: " + (1 =!= 2))

    println("In option " + (cat1.some =!= none[Cat]))
    println("In option " + (cat1.some === cat1.some))
  }
}
