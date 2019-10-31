package simple.nlp.operator

object OperatorVector {
  def arrayModulus(v: Array[Double]): Double = math.sqrt(v.map(a => math.pow(a, 2)).sum)
}