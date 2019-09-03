package com.flxkbr.interview

import com.flxkbr.various._
import scala.collection.BitSet

object Arrays {
  

  def findSingleMissingOrDuplicate(arr: Array[Int], n: Int): Int = {
    val fullSum = (n * (n+1)) / 2
    val aSum = arr.reduce((a, b) => a + b)
    aSum - fullSum
  }

  def findMultipleMissing(arr: Array[Int], n: Int): Set[Int] = {
    val bitSet = BitSet(arr: _*)
    (1 to n).filterNot(bitSet).toSet
  }

  def arrayPairSum(arr: Array[Int], sum: Int): Set[(Int, Int)] = {
    var seen = Set.empty[Int]
    arr.flatMap(i => {
      give {
        if (seen(sum - 1)) Some((i, sum - 1))
        else None
      } butFirst {
        seen += i
      }
    }).toSet
  }

  def largestContinuousSum(arr: Array[Int]): Int = {
    var largestSum = 0
    var currentSum = 0
    arr.foreach(i => {
      if (currentSum + i < i) currentSum = i
      else currentSum += i
      if (currentSum > largestSum) largestSum = currentSum
    })
    largestSum
  }
}