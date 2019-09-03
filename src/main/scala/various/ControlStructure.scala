package com.flxkbr.various

class give[A](block: => A) {
  def butFirst(block2: => Unit): A = {
    val res = block
    block2
    res
  }
}

object give {
  def apply[A](block: => A) = new give(block)
}