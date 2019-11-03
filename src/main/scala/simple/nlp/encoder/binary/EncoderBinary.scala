package simple.nlp.encoder.binary

object EncoderBinary {
  private var tokenEncoder: Map[String, Int] = Map.empty
  private var tokenDecoder: Map[Int, String] = Map.empty
  private var numberOfTokens = 0

  def loadTokens(setTokens: Set[String]) = {
    numberOfTokens = 0
    for (t <- setTokens) {
      tokenEncoder += (t -> numberOfTokens)
      tokenDecoder += (numberOfTokens -> t)
      numberOfTokens += 1
    }
  }

  def encode(arrayToken: Array[String]): Array[Int] = {
    var tokensEncoded = Array.ofDim[Int](numberOfTokens)
    arrayToken.foreach(token => {
      val tokenPosition = tokenEncoder(token)
      var tokenOccurrence = (tokensEncoded(tokenPosition))
      tokenOccurrence = (tokenOccurrence + 1)
      tokensEncoded(tokenPosition) = tokenOccurrence
    })
    tokensEncoded
  }

  def encodeWithoutCount(arrayToken: Array[String]): Array[Int] = {
    var tokensEncoded = Array.ofDim[Int](numberOfTokens)
    arrayToken.foreach(token => {
      tokensEncoded(tokenEncoder(token)) = 1
    })
    tokensEncoded
  }

  def decode(arrayToken: Array[Int]): Array[String] = {
    var stringDecoded: Array[String] = Array.empty
    for (tokenPosition <- arrayToken.indices) {
      if (arrayToken(tokenPosition) >= 1) { stringDecoded = tokenDecoder(tokenPosition) +: stringDecoded }
    }
    stringDecoded
  }
}
