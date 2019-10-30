package simple.nlp.tokenizator

import java.text.Normalizer

object Tokenizator {

  def tokenizeWithNomalizer (text:String, stopwords:Array[String]) = {
    text.split("\\s")
      .map(s =>Normalizer.normalize(s.toLowerCase(), Normalizer.Form.NFD)
        .replaceAll("[^A-Za-z0-9\\s]", "")
        .trim())
      .filter(s => !s.isEmpty)
      .filter(s => !stopwords.contains(s))
  }

  def tokenizeByWhitespace (text:String) = {
    text.split("\\s")
      .map(s => s.trim())
      .filter(s => !s.isEmpty)
  }


  def tokenizeCustom(text:String, regexTokenizerExpression:String) = {
    text.split(regexTokenizerExpression)
      .filter(s => !s.isEmpty)
  }
}
