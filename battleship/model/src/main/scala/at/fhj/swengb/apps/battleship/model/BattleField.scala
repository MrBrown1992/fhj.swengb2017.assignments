package at.fhj.swengb.apps.battleship.model

import scala.util.Random

object BattleField {

  def placeRandomly(bf: BattleField): BattleField = {

    def loop(vesselsToPlace: Set[Vessel], anotherBattleField: BattleField): BattleField = {
      if (vesselsToPlace.isEmpty) anotherBattleField
      else {
        val v = vesselsToPlace.head
        loop(vesselsToPlace.tail, anotherBattleField.addAtRandomPosition(v))
      }

    }

    loop(bf.fleet.vessels, bf.copy(fleet = bf.fleet.copy(vessels = Set())))
  }

}

/**
  * Denotes the size of our region of interest
  */
case class BattleField(width: Int, height: Int, fleet: Fleet) {

  /**
    * All positions in this battlefield
    */
  val allPos: Set[BattlePos] = (for {x <- 0 until width
                                     y <- 0 until height} yield BattlePos(x, y)).toSet
  val availablePos: Set[BattlePos] = allPos -- fleet.occupiedPositions

  /**
    * Adds vessel at a random, free position in the battlefield. if no position could be found,
    * returns the current battlefield without vessel added.
    *
    * @param v vessel to add
    * @return
    */
  def addAtRandomPosition(v: Vessel): BattleField = {

    def loop(pos: Set[BattlePos], actualBattleField: BattleField, found: Boolean): BattleField = {
      if (found) {
        println(s"Placed vessel of type ${v.getClass.getSimpleName} on battlefield ...")
        actualBattleField
      } else if (pos.isEmpty) {
        println(s"Giving up on vessel of type ${v.getClass.getSimpleName}. No place left.")
        actualBattleField
      } else {
        val p = pos.toSeq(Random.nextInt(pos.size))
        val vessel = v.copy(startPos = p)
        if (vessel.occupiedPos.subsetOf(availablePos)) {
          loop(pos - p, actualBattleField.copy(fleet = actualBattleField.fleet.copy(vessels = actualBattleField.fleet.vessels + vessel)), true)
        } else {
          loop(pos - p, actualBattleField, false)
        }
      }
    }

    loop(availablePos, this, false)

  }

  def randomFleet(): Fleet = {
    Fleet(Set[Vessel]())
  }


}