package simple.nlp.encoder.wordembedding

import java.io.{File, FileWriter}

import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.mllib.linalg.{Matrix, SingularValueDecomposition, Vector}
import org.apache.spark.{SparkConf, SparkContext}
import simple.nlp.SimpleNLP
import simple.nlp.encoder.binary.EncoderBinary

import scala.collection.mutable.ArrayBuffer

object EncoderLSI {

  def encode(arrayOfDataBinaryEncoded:Array[Vector],
             arrayOfSentencesBinaryEncoded:Array[Array[String]]): SVD = {

    System.setProperty("hadoop.home.dir", "C:\\hadoop\\")

    val sparkConf = new SparkConf()
      .setAppName("SimpleNLP")
      .setMaster("local[2]")
      .set("spark.executor.memory", "6g")


    val sc = new SparkContext(sparkConf)

    var rows = sc.parallelize(arrayOfDataBinaryEncoded)

    var mat: RowMatrix = new RowMatrix(rows)

    var svd: SingularValueDecomposition[RowMatrix, Matrix] = mat.computeSVD(6, computeU = true)
    var V: RowMatrix = svd.U // ORTONORMAL VECTOR rotate
    val s: Vector = svd.s //    SIGMA SINGULAR VECTOR strech
    val U: Matrix = svd.V //    ORTONORMAL VECTOR rotate


    //RETURN SVD
    val response:SVD = SVD( V.rows.collect().map(v => v.toArray),s,U, arrayOfSentencesBinaryEncoded)

    //STOP SPARK CONTEXT
    sc.stop()
    sc.clearCallSite()
    sc.clearJobGroup()
    response

  }

  


  //CALCULATE NEW QUERY WITH IS A DIMENSIONALITY REDUCTION OF QUERY PASSED BY PARAMETER
  def calcQuery(q:Array[Double], svd:SVD) = {
    var queryCalculated: ArrayBuffer[Array[Double]] = ArrayBuffer.empty

    //OPERADORES VETORIAIS
    val arrayMutiplication = (u:Array[Double],v:Array[Double])  =>  (u zip v).map(t => t._1 * t._2)
    val arrayMutiplicationByEscalar = (u:Vector,v:Double)  =>  u.toArray.map(t => t * v)

    //MULTIPLICACAO Caux = U * S
    var cAux:ArrayBuffer[Array[Double]] = ArrayBuffer.empty
    for (index <- svd.s.toArray.indices){
      cAux += arrayMutiplicationByEscalar.apply(svd.U.colIter.next(),1/svd.s(index))
    }

    //MULTIPLICACAO Qnew = Qold * Caux
     cAux.map( c => arrayMutiplication(c,q).sum).toArray

  }

  def similarityRank(lsiEncodedData:Array[DataLSI], q: Array[Double]):Array[DataLSI] =
    lsiEncodedData.sortWith((a,b) => productEscalar(q,a.position) < productEscalar(q,b.position))

  def productEscalar(q:Array[Double],d:Array[Double]): Double = (q zip d).map(t => t._1*t._2).sum / (arrayModulus(q) * arrayModulus(d))

  def arrayModulus(v:Array[Double]): Double = math.sqrt(v.map( a => math.pow(a,2)).sum)

  def dotProduct(v:Array[Double],u:Array[Double]):Double = productEscalar(v,u)/(arrayModulus(v) * arrayModulus(u))

}



