package simple.nlp.encoder.binary

object EncoderBinary {
  var tokensPos:Map[String,Int] = Map.empty
  var tokenDecoder:Map[Int,String] = Map.empty
  var tokensLength = 0

  def loadTokens(setTokens: Set[String])={
    tokensLength = 0
    for(t <- setTokens){
      tokensPos += ( t -> tokensLength)
      tokenDecoder += ( tokensLength -> t)
      tokensLength += 1
    }
  }


  def encode(arrayToken:Array[String]):Array[Int] = {
    var tokensEncoded = Array.ofDim[Int](tokensLength)
    arrayToken.foreach(token => {
      var index = tokensPos.get(token).get
      var posValue = (tokensEncoded(index))
      posValue = (posValue + 1)
      tokensEncoded(index) = posValue
    })
    tokensEncoded
  }

  def encodeBinary(arrayToken:Array[String]):Array[Int] = {
    var tokensEncoded = Array.ofDim[Int](tokensLength)
    arrayToken.foreach(token => {
      var index = tokensPos.get(token).get
      var posValue = (tokensEncoded(index))
      tokensEncoded(index) = 1
    })
    tokensEncoded
  }


  def decode(arrayToken: Array[Int]):Array[String] ={
    var stringDecoded:Array[String]= Array.empty
    for (index <- arrayToken.indices){
      if(arrayToken(index) >= 1){
        stringDecoded = tokenDecoder.get(index).get +: stringDecoded
      }
    }
    stringDecoded
  }

}
