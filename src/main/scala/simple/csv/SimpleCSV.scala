package simple.csv

case class Row(columns:Array[String])

object SimpleCSV {

  def readCSV(path:String): Array[Row] ={
    val source = scala.io.Source.fromFile(path)
    var fileTemp = try source.mkString finally source.close()
    fileTemp.split("\r\n").map(l => Row(l.split(";")))
  }

  def writeCSV(){}
}
