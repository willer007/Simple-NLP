package simple.nlp.learning.clustering

import scala.collection.mutable.ArrayBuffer
import scala.util.Random


case class Cluster(centroid: Array[Double], data: ArrayBuffer[Array[Float]])

object Cluster {
  def addData(cluster: Cluster, data: Array[Float]): Unit = cluster.data += data
  def setCentroid(cluster: Cluster, centroid: Array[Double]) = Cluster(centroid, cluster.data)
  def createCluster(size:Int): Cluster = Cluster(Array.fill(size) {Random.nextDouble()}, ArrayBuffer.empty)
  def createCluster(centroid: Array[Double]):Cluster = Cluster(centroid,ArrayBuffer.empty)

}