package simple.nlp.tokenization

import java.text.Normalizer

object Tokenization {

  def tokenizeWithNormalizer (text:String, stopwords:Array[String]): Array[String] = {
    text.split("\\s")
      .map(s =>Normalizer.normalize(s.toLowerCase(), Normalizer.Form.NFD)
        .replaceAll("[^A-Za-z0-9\\s]", "")
        .trim())
      .filter(s => !s.isEmpty)
      .filter(s => !stopwords.contains(s))
  }

  def tokenizeByWhitespace (text:String): Array[String] = {
    text.split("\\s")
      .map(s => s.trim())
      .filter(s => !s.isEmpty)
  }


  def tokenizeCustom(text:String, regexTokenizerExpression:String): Array[String] = {
    text.split(regexTokenizerExpression)
      .filter(s => !s.isEmpty)
  }
}


