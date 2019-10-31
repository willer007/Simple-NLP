package simple.nlp

import simple.nlp.encoder.binary.EncoderBinary
import simple.nlp.encoder.wordembedding.EncoderLSI
import simple.nlp.normalizer.tokens.NormalizerTokens
import simple.nlp.operator.OperatorLexical

object SimpleNLP {

  //OPERATORS
  val NormalizerTokens = normalizer.tokens.NormalizerTokens
  val Tokenizator = tokenization.Tokenization
  val LexicalOperators = operator.OperatorLexical

  //ENCODERS
  val EncoderBinary = encoder.binary.EncoderBinary
  val EncoderLSI = encoder.wordembedding.EncoderLSI

  //LEARNING
  val LearningGenetic = learning.evolutionary.LearningGenetic
  val LearningLloyds = learning.clustering.LearningLloyds

  //DATASET
  val Dataset =  dataset.Dataset
}
