package nlp.encoder.wordembedding

import simple.nlp.encoder.wordembedding.EncoderLSI
import org.apache.spark.mllib.linalg.{Vector, Vectors}

object EncoderLSITest {

  def main(args: Array[String]): Unit = {

    var inputs:Array[Vector] = Array.empty


    inputs = inputs :+ Vectors.dense(1,0,1,0,1,1,1,1,1,0,0)
    inputs = inputs :+ Vectors.dense(1,1,0,1,0,0,1,1,0,2,1)
    inputs = inputs :+ Vectors.dense(1,1,0,0,0,1,1,1,1,0,1)

    var test = EncoderLSI.encode(inputs,2)

    var query:Array[Double] = Array(0,0,0,0,0,1,0,0,0,1,1)
    var testQuery = EncoderLSI.encodeQuery(query)


  }

}

