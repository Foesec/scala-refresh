package com.flxkbr.interview

object Lists {

  import scala.collection.mutable
  def middleElements[A](list: List[A]): List[A] = {
    if (list.isEmpty) return List.empty[A]
    var a = list
    var b = list
    while(!b.tail.isEmpty) {
      if (b.tail.tail.isEmpty)
        return List(a.head, a.tail.head)
      a = a.tail
      b = b.tail.tail
    }
    List(a.head)
  }
}