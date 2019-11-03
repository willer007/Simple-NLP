package simple.nlp.encoder.wordembedding


import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.mllib.linalg.{Matrix, SingularValueDecomposition, Vector, Vectors}
import org.apache.spark.{SparkConf, SparkContext}
import simple.nlp.operator.OperatorArray

import scala.collection.mutable.ArrayBuffer

object EncoderLSI {

  private var dataEncoded:SVD = SVD.empty()


  def encode(inputs:Array[Vector] , numOfDimensions:Int): Array[Array[Double]] = {

    val sparkConf = new SparkConf()
      .setAppName("SimpleNLP")
      .setMaster("local[2]")
      .set("spark.executor.memory", "6g")


    val sparkContext = new SparkContext(sparkConf)
    val inputsParallel = sparkContext.parallelize(inputs)

    val rowMatrixInputs: RowMatrix = new RowMatrix(inputsParallel)

    val svd: SingularValueDecomposition[RowMatrix, Matrix] = rowMatrixInputs.computeSVD(numOfDimensions, computeU = true)
    val V: RowMatrix = svd.U // ORTONORMAL VECTOR rotate
    val s: Vector = svd.s //    SIGMA SINGULAR VECTOR strech
    val U: Matrix = svd.V //    ORTONORMAL VECTOR rotate


    //RETURN SVD
    dataEncoded = SVD( V.rows.collect().map(v => v.toArray),s,U)

    //STOP SPARK CONTEXT
    sparkContext.stop()
    sparkContext.clearCallSite()
    sparkContext.clearJobGroup()
    dataEncoded.V

  }

  def encode(inputs:Array[Array[Int]] , numOfDimensions:Int): Array[Array[Double]] = {

    val sparkConf = new SparkConf()
      .setAppName("SimpleNLP")
      .setMaster("local[2]")
      .set("spark.executor.memory", "6g")

    var aux = inputs.map(_.map(_.toDouble))
    var auxV = aux.map(v => Vectors.dense(v))

    aux = null

    val sparkContext = new SparkContext(sparkConf)
    val inputsParallel = sparkContext.parallelize(auxV)

    val rowMatrixInputs: RowMatrix = new RowMatrix(inputsParallel)

    val svd: SingularValueDecomposition[RowMatrix, Matrix] = rowMatrixInputs.computeSVD(numOfDimensions, computeU = true)
    val V: RowMatrix = svd.U // ORTONORMAL VECTOR rotate
    val s: Vector = svd.s //    SIGMA SINGULAR VECTOR strech
    val U: Matrix = svd.V //    ORTONORMAL VECTOR rotate


    //RETURN SVD
    dataEncoded = SVD( V.rows.collect().map(v => v.toArray),s,U)

    //STOP SPARK CONTEXT
    sparkContext.stop()
    sparkContext.clearCallSite()
    sparkContext.clearJobGroup()
    dataEncoded.V

  }


  def encodeQuery(q:Array[Double]):Array[Double] = {

    var queryCalculated: ArrayBuffer[Array[Double]] = ArrayBuffer.empty

    var productUQ = dataEncoded.U.transpose.multiply(Vectors.dense(q)).toArray

    var sInverse = OperatorArray.inverse(dataEncoded.s.toArray)

    return OperatorArray.multiplyArrayCoordinates(productUQ,sInverse)

  }

  def encodeQuery(q:Array[Int]):Array[Double] = {

    var aux = q.map(_.toDouble)

    var queryCalculated: ArrayBuffer[Array[Double]] = ArrayBuffer.empty

    var productUQ = dataEncoded.U.transpose.multiply(Vectors.dense(aux)).toArray

    var sInverse = OperatorArray.inverse(dataEncoded.s.toArray)

    return OperatorArray.multiplyArrayCoordinates(productUQ,sInverse)

  }



}



