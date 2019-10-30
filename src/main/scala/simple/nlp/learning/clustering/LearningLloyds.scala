package simple.nlp.learning.clustering

import scala.collection.mutable.ArrayBuffer
import scala.util.Random



object LearningLloyds{
  var size: Int = 0
  var clusters: Array[Cluster] = Array.empty

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

  def euclideanDistance(array1: Array[Double], array2: Array[Double]): Double =
    (array1 zip array2).map(v => math.pow(v._1 - v._2, 2)).sum


  def findClusterWithMinDistance(arrayTokens: Array[Double]): Cluster = {
    var minDistance: Double = 999999999999f
    var minCluster: Cluster = Cluster(Array.empty, ArrayBuffer.empty)
    for (c <- clusters) {
      var d = euclideanDistance(c.centroid, arrayTokens)
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

  def optimize(points: Array[Array[Double]]): Array[Cluster] = {

    points.foreach(t => Cluster.addObservartion(findClusterWithMinDistance(t), Observation(t.map(a => a.toDouble))))

    clusters = clusters
      .map(c => Cluster.setCentroid(c, optimizeCluster(c)))


    return clusters
  }

}
