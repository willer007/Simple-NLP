package simple.nlp.learning.clustering

import simple.nlp.operator.OperatorArray
import scala.collection.mutable.ArrayBuffer
import scala.util.Random



object LearningLloyds{
  private var clusters: Array[Cluster] = Array.empty

  def initModel(numOfClusters: Int, sizeOfClusters:Int): Unit = {
    for (i <- 1 to numOfClusters) {
      clusters = Cluster.createCluster(sizeOfClusters) +: clusters
    }
  }

  def initModel(arrayOfClusters:Array[Array[Float]]):Unit = {
    for ( centroid <- arrayOfClusters) {
      clusters = Cluster.createCluster(centroid) +: clusters
    }
  }

  def distanceClusterFromData(cluster:Cluster, data:Array[Float]) : Float =
    OperatorArray.euclideanDistance(cluster.centroid,data)

  def assignDataToCluster(data:Array[Float]): Unit = {
    var clustersOrdenedByDistance = clusters
      .map(cluster => (cluster, distanceClusterFromData(cluster, data)))
      .sortBy(t => t._2)

    Cluster.addData(clustersOrdenedByDistance(0)._1,data)
  }

  def optimize(inputs:Array[Array[Float]]): Array[Cluster] = {

    /*clusters = clusters.map(c => Cluster.setCentroid(c, optimizeCluster(c)))


    return clusters*/
    return clusters
  }

}
