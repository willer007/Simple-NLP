package simple.nlp.learning.clustering

import scala.collection.mutable.ArrayBuffer
import scala.util.Random


case class Cluster(centroid: Array[Double], observations: ArrayBuffer[Observation])

object Cluster {
  def addObservartion(cluster: Cluster, observation: Observation): Unit = cluster.observations += observation
  def setCentroid(cluster: Cluster, centroid: Array[Double]) = Cluster(centroid, cluster.observations)
  def createCluster(size:Int): Cluster = Cluster(Array.fill(size) {Random.nextDouble()}, ArrayBuffer.empty)
  def createCluster(centroid: Array[Double]):Cluster = Cluster(centroid,ArrayBuffer.empty)

}