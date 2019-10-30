package simple.nlp.normalizer.tokens

object NormalizerTokens {


  def applyTfIDF(tokensArray:Array[String]): Map[String,Int] =
    tokensArray.groupBy(identity).mapValues(_.size)


  def binarySearch(tokensArray:Array[String], item: String): Int = {
    var min = 0;
    var max = tokensArray.length;


    var a = (min + max)/2
    if(max %2 >= 1) a-1

    var b = max

   if(item(0) >= tokensArray(a)(0)){
      if(item.equals(tokensArray(a)))
        return a

   }else{
     b = a
     a = 0
   }
    binarySearch(tokensArray.slice(a,b),item)
  }





}
