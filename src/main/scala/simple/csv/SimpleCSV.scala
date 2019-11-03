package simple.csv

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

case class Row(columns:Array[String])

object SimpleCSV {

  /*def readCSV(path:String): Array[Row] ={
    val source = scala.io.Source.fromFile(path)
    var fileTemp = try source.mkString finally source.close()
    fileTemp.split("\r\n").map(l => Row(l.split(";")))
  }*/

  def readCSV(path:String): String ={
    val source = scala.io.Source.fromFile(path)
    var fileTemp = try source.mkString finally source.close()
    fileTemp
  }

  def writeCSV(path:String, text:String): Unit ={
    Files.write(Paths.get(path),
      text.getBytes(StandardCharsets.UTF_8))
  }
}
