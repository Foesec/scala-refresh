package com.flxkbr.various

import org.scalatest._
import org.scalatest.FlatSpec

class ControlStructureSpec extends FlatSpec with Matchers {

  behavior of "give structure"

  it should "not throw an exception" in {
    noException should be thrownBy {
      val empty = collection.mutable.ArrayBuffer.empty[Int]
      give {
        empty.isEmpty
      } butFirst {
        empty += 2
      }
    }
  }

  it should "compute give result before butFirst block is executed" in {
    import scala.collection.mutable
    val buf = mutable.ArrayBuffer(1, 2)
    assertResult(3){
      give {
        buf.append(3)
        buf.length
      } butFirst {
        buf.append(4)
      }
    }
    buf should have length 4
  }
}