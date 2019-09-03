package com.flxkbr.rosetta

import collection.mutable.ArrayBuffer

object OneHundredDoors {

  def getFinalCorridor(): Array[Door] = {
    val corridor: ArrayBuffer[Door] = ArrayBuffer.fill(100)(ClosedDoor)

    for (pass <- Range.inclusive(1, 100)) {
      for (i <- corridor.indices if (i + 1) % pass == 0) {
        corridor(i) = toggleDoor(corridor(i))
      }
    }

    corridor.toArray
  }

  def toggleDoor(door: Door): Door = door match {
    case OpenDoor   => ClosedDoor
    case ClosedDoor => OpenDoor
  }
}

sealed abstract trait Door
case object OpenDoor extends Door
case object ClosedDoor extends Door
