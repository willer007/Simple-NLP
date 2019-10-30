package simple.nlp.encoder.wordembedding
import org.apache.spark.mllib.linalg.{Matrix, Vector}

case class SVD(V:Array[Array[Double]], s:Vector, U:Matrix, arrayOfSentencesBinaryEncoded: Array[Array[String]])
