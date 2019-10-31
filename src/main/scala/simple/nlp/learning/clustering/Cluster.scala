package simple.nlp.learning.clustering

import scala.collection.mutable.ArrayBuffer
import scala.util.Random


case class Cluster(centroid: Array[Float], data: ArrayBuffer[Array[Float]])

object Cluster {
  def addData(cluster: Cluster, data: Array[Float]): Unit = cluster.data += data
  def setCentroid(cluster: Cluster, centroid: Array[Float]) = Cluster(centroid, cluster.data)
  def createCluster(size:Int): Cluster = Cluster(Array.fill(size) {Random.nextFloat()}, ArrayBuffer.empty)
  def createCluster(centroid: Array[Float]):Cluster = Cluster(centroid,ArrayBuffer.empty)

}