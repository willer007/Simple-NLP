package simple.nlp.operator

object OperatorLexical{

  def loadStopWords(file:String) = {
    var source = scala.io.Source.fromFile(file,"UTF-8")
    val string_portuguese_stopwords = try source.mkString finally source.close()
    string_portuguese_stopwords
      .split("\n")
      .map(s => s.trim())
  }

  def extractNumberAfterToken(token:String, tokens:Array[String]): String ={
    var tokenIndex = tokens.indexOf(token)
    var tokensSliced = tokens.slice(tokenIndex+1,tokens.length)
    var number = ""
    for(t <- tokensSliced){
      if (isAllDigits(t))
        number += t
      else
        return number
    }
    number
  }

  def isAllDigits(x: String) = x forall Character.isDigit


  def extractSubstring(s:String,to:String, at:String):String =  {
    val a = s.indexOf(to)
    var b = s.indexOf(at)

    if( a == -1 || b == -1)
      return ""

    else if(a < b )
      return s.substring(a, b)

    else extractSubstring(s.substring(b+1),to, at)
  }

  def removeSubstring(s:String,to:String, at:String):String =  {
    var substring = extractSubstring(s,to,at)
    s.replace(substring,"");
  }
}
