package simple.nlp.similarity

import simple.nlp.operator.OperatorVector


object SimilarityCossine {

  def similarityCossine(v1:Array[Double], v2:Array[Double]): Double =
    (v1 zip v2).map(t => t._1*t._2).sum / (OperatorVector.arrayModulus(v1) * OperatorVector.arrayModulus(v2))


}
