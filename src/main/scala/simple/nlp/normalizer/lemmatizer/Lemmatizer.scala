package simple.nlp.normalizer.lemmatizer

object Lemmatizer {
  var portueseLemmas:Array[Lemma] = Array.empty

  def loadLemmas(file:String): Unit ={
    var source = scala.io.Source.fromFile(file,"UTF-8")
    val stringPorgueseLemmas = try source.mkString finally source.close()
    portueseLemmas = stringPorgueseLemmas.split("\n").map( l => Lemma(l.split(";")(0), l.split(";")(1)))
  }

  def extractLemma (token:String):String ={
    portueseLemmas.filter(l => l.token == token).collectFirst({case a:Lemma => a.lemma}).get
  }
}
