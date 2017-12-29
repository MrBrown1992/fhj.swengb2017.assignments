package at.fhj.swengb.apps.battleship.jfx

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage


import scala.util.{Failure, Success, Try}


import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import java.io.File
import java.net.URL


object BattleShipFxApp {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[BattleShipFxApp], args: _*)
  }

}


class BattleShipFxApp extends Application {

  final URL resource = getClass().getResource("a.mp3")
  final Media media = new Media(resource.toString())
  final MediaPlayer mediaPlayer = new MediaPlayer(media)
  mediaPlayer.play()



  val triedRoot = Try(FXMLLoader.load[Parent](getClass.getResource("/at/fhj/swengb/apps/battleship/jfx/battleshipfx.fxml")))
  val css = "/at/fhj/swengb/apps/battleship/jfx/style.css"
  override def start(stage: Stage) = {
    triedRoot match {
      case Success(root) =>
        stage.setScene(new Scene(root))
        stage.show()
      case Failure(e) => e.printStackTrace()
    }
  }

}