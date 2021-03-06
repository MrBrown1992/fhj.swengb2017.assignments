package at.fhj.swengb.apps.battleship.model

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
;

/**
  * Represents one part of a vessel or one part of the ocean.
  */

case class BattleFxCell(pos: BattlePos
                        , width: Double
                        , height: Double
                        , log: String => Unit
                        , someVessel: Option[Vessel] = None
                        , fn: (Vessel, BattlePos) => Unit
                        , clickedPos: BattlePos => Unit
                       ) extends Rectangle(width, height) {

  def init(): Unit = setFill(Color.BLUE)

  setOnMouseClicked(e => {
    clickMouse()
  })

  def clickMouse(): Unit = {
    clickedPos(pos)
    someVessel match {
      case None =>
        log(s"Missed. Just hit water.")
        setFill(Color.BLUE)
      case Some(v) =>
        fn(v, pos)
        setFill(Color.GREY)
    }
  }

}