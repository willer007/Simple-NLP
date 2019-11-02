package simple.nlp.operator

import org.apache.spark.mllib.linalg.Vector

object OperatorArray {
  def arrayModulus(v: Array[Double]): Double = math.sqrt(v.map(a => math.pow(a, 2)).sum)

  def linearCombination(v1: Array[Float], v2: Array[Float]): Float =
    (v1 zip v2 ).map(t => t._1.toFloat * t._2.toFloat).sum

  def euclideanDistance(v1: Array[Float], v2: Array[Float]): Float =
    (v1 zip v2).map(v => math.pow(v._1 - v._2, 2)).sum.toFloat

  def sumArrayCoordinates(array1: Array[Float], array2: Array[Float]):Array[Float] =
    (array1 zip array2).map(v => v._1 + v._2)

  def multiplyArrayCoordinates(u:Array[Double], v:Array[Double]):Array[Double]  =
    (u zip v).map(t => t._1 * t._2)

  def multiplyArrayCoordinatesByEscalar (u:Vector,v:Double):Array[Double]  =
    u.toArray.map(t => t * v)

  def inverse (u:Array[Double]):Array[Double]  =
    u.map(t => 1/t)


}