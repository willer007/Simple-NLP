package simple.nlp.operator

object OperatorArray {
  def arrayModulus(v: Array[Double]): Double = math.sqrt(v.map(a => math.pow(a, 2)).sum)

  def linearCombination(v1: Array[Float], v2: Array[Float]): Float =
    (v1 zip v2 ).map(t => t._1.toFloat * t._2.toFloat).sum
}