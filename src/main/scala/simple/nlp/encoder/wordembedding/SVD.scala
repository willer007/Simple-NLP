package simple.nlp.encoder.wordembedding
import org.apache.spark.mllib.linalg.{DenseMatrix, Matrix, Vector, Vectors}

case class SVD(V:Array[Array[Double]], s:Vector, U:Matrix)

object SVD {
  def empty() = SVD(Array.empty,Vectors.dense(0),  DenseMatrix.zeros(0,0))
}