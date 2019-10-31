package simple.nlp.learning.clustering

import simple.nlp.operator.OperatorArray
import scala.collection.mutable.ArrayBuffer
import scala.util.Random



object LearningLloyds{
  private var size: Int = 0
  private var clusters: Array[Cluster] = Array.empty

  def initModel(numOfClusters: Int, sizeOfClusters:Int): Unit = {
    for (i <- 1 to numOfClusters) {
      clusters = Cluster.createCluster(sizeOfClusters) +: clusters
    }
  }

  def initModel(arrayOfClusters:Array[Array[Double]]):Unit = {
    for ( centroid <- arrayOfClusters) {
      clusters = Cluster.createCluster(centroid) +: clusters
    }
  }


  def findClusterWithMinDistance(arrayTokens: Array[Double]): Cluster = {
    var minDistance: Double = 999999999999f
    var minCluster: Cluster = Cluster(Array.empty, ArrayBuffer.empty)
    for (c <- clusters) {
      var d = OperatorArray.euclideanDistance(c.centroid, arrayTokens)
      if (d < minDistance) {
        minDistance = d
        minCluster = c
      }
    }
    minCluster
  }

  def sumPosition(array1: Array[Double], array2: Array[Double]) = (array1 zip array2).map(v => v._1 + v._2)

  def optimizeCluster(cluster: Cluster) = if (cluster.observations.nonEmpty)
      cluster.observations.map(a => a.position).reduce((a, b) => sumPosition(a, b)).map(a => a / cluster.observations.length)
    else
      Array.fill(size) {Random.nextDouble()}

  def optimize(): Array[Cluster] = {

    clusters = clusters.map(c => Cluster.setCentroid(c, optimizeCluster(c)))


    return clusters
  }

}
