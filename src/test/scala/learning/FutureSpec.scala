package com.flxkbr.learning
import com.flxkbr.BaseTest
import org.scalatest.AsyncFlatSpec
import org.scalatest.Matchers
import scala.concurrent.Future

class FutureSpec extends AsyncFlatSpec with Matchers {

  "A future" should "be able to be mapped" in {
    val a = Future {
      Thread.sleep(500)
      1 + 2
    }
    a.map(n => n * 2).map(o => (o should equal (6)))
  }

  it should "be able to be used in for expressions" in {
    val f1 = Future { Thread.sleep(500); 30 + 3}
    val f2 = Future { Thread.sleep(500); 6 + 3}

    val f3 = for {
      x <- f1
      y <- f2
    } yield x + y

    f3.map(x => x shouldEqual 42)
  }
}