package at.fhj.swengb.apps.calculator

import java.nio.file.{Files, Path, Paths}
<<<<<<< HEAD
import scala.collection.JavaConverters._
import org.scalatest.WordSpecLike

class TimesheetSpec extends WordSpecLike {


  private val filePath: Path = Paths.get("calculator/timesheet-calculator.adoc")

  private val originalContent =
    """== Time expenditure: Calculator assignment
      |
      |[cols="1,1,4", options="header"]
      |.Time expenditure
      ||===
      || Date
      || Hours
      || Description
      |
      || 29.11.17
      || 1
      || Review of this and that
      |
      || 30.11.17
      || 5
      || implemented css
      |
      || 11.07.17
      || 2
      || fixed bugs
      |
      ||===""".stripMargin

  "Timesheet Spec" should {
    "timesheet-calculator" should {
      "file exists" in {
        assert(Files.exists(filePath))
      }
      "not be the same like content" in {
        val fileContent: Seq[String] = Files.readAllLines(filePath).asScala
        assert(fileContent.mkString("\n") != originalContent)
      }
    }
  }









=======
import java.util

import org.scalatest.WordSpecLike
import scala.collection.JavaConverters._


class TimesheetSpec extends WordSpecLike {


  val p: Path = Paths.get("timesheet-calculator.adoc")

  "A timesheet" should {
    "exists" in {
      assert(Files.exists(p))
    }
    "contains entries" in {

      val expected: String =
        """== Time expenditure: Calculator assignment
          |
          |[cols="1,1,4", options="header"]
          |.Time expenditure
          ||===
          || Date
          || Hours
          || Description
          |
          || 29.11.17
          || 1
          || Review of this and that
          |
          || 30.11.17
          || 5
          || implemented css
          |
          || 11.07.17
          || 2
          || fixed bugs
          |
          ||===
          |""".stripMargin

      // goal is to read a file
      val allLinesInAFile: Seq[String] = Files.readAllLines(p).asScala

      val foldedString =
        allLinesInAFile.foldLeft("")((acc, e) => acc + (e + "\n"))


      assert(foldedString != expected)
    }

  }
>>>>>>> upstream/master

}
