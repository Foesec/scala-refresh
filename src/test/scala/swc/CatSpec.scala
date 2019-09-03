package com.flxkbr.swc

import org.scalatest.FlatSpec
import org.scalatest.Assertions.assert

import cats._
import cats.implicits._

class CatSpec extends FlatSpec {

  "Cat" should "be showable" in {
    val cat = Cat("felix", 10, "black")
    import cats.Show
    assert(
      Show.apply[Cat].show(cat).equals("felix is a 10 year-old black cat.")
    )
    assert(
      cat.show.equals("felix is a 10 year-old black cat.")
    )
  }
}