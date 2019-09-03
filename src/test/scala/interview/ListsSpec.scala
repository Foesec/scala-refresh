package com.flxkbr.interview

import org.scalatest.FlatSpec
import com.flxkbr.BaseTest

class ListsSpec extends BaseTest {

  "middleElements()" should "find single middle element" in {
    assertResult(List(3)) {
      Lists.middleElements(List(1, 2, 3, 4, 5))
    }
  }

  it should "find two middle elements" in {
    assertResult(List(3, 4)) {
      Lists.middleElements(List(1, 2, 3, 4, 5, 6))
    }
  }

  it should "be able to handle lists with one element" in {
    assertResult(List("solo")) {
      Lists.middleElements(List("solo"))
    }
  }

  it should "be able to handle empty lists" in {
    assertResult(List.empty[Double]) {
      Lists.middleElements(List.empty[Double])
    }
  }
}