package com.flxkbr.interview

object Strings {

  def perm(fixed: String = "", word: String): List[String] = word match {
    case "" => List(fixed)
    case _ =>
      (0 until word.length).toList.flatMap(i =>
          perm(fixed + word(i), word.substring(0, i) + word.substring(i + 1)))
  }
}
