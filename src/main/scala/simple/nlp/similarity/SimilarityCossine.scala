package simple.nlp.similarity



object SimilarityCossine {

  def productEscalar(q:Array[Double],d:Array[Double]): Double =
    (q zip d).map(t => t._1*t._2).sum / (arrayModulus(q) * arrayModulus(d))

  def arrayModulus(v:Array[Double]): Double = math.sqrt(v.map( a => math.pow(a,2)).sum)

  def dotProduct(v:Array[Double],u:Array[Double]):Double = productEscalar(v,u)/(arrayModulus(v) * arrayModulus(u))


}
