package simple.nlp.operator

object OperatorArray {
  def arrayModulus(v: Array[Double]): Double = math.sqrt(v.map(a => math.pow(a, 2)).sum)

  def linearCombination(v1: Array[Float], v2: Array[Float]): Float =
    (v1 zip v2 ).map(t => t._1.toFloat * t._2.toFloat).sum

  def euclideanDistance(v1: Array[Double], v2: Array[Double]): Double =
    (v1 zip v2).map(v => math.pow(v._1 - v._2, 2)).sum

  def sumArrayCoordinates(array1: Array[Double], array2: Array[Double]):Array[Double] =
    (array1 zip array2).map(v => v._1 + v._2)


}