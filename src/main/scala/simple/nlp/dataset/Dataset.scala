package simple.nlp.dataset


object Dataset {
  var dataset: Array[Data] = Array.empty

  def load(file: String): Array[Data] = {
    val source = scala.io.Source.fromFile(file)
    var fileTemp = try source.mkString finally source.close()
    fileTemp.split("\r\n").map(l => Data(l.split(";")))
  }

}



