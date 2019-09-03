package com.flxkbr.rosetta
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.collection.mutable
import com.flxkbr.BaseTest

class OneHundredDoorsSpec extends BaseTest {

  "The OneHundredDoors object" should "produce the correct sequence of doors" in {

    val solution: mutable.ArrayBuffer[Door] = mutable.ArrayBuffer.fill(100)(ClosedDoor)

    for (i <- Range.inclusive(1, 10)) {
      solution((i * i)-1) = OpenDoor
    }

    val result = OneHundredDoors.getFinalCorridor()

    result should have length 100
    result should contain theSameElementsInOrderAs solution
  }
}