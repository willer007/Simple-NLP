package simple.nlp.learning.clustering

import simple.nlp.operator.OperatorArray

import scala.collection.mutable.ArrayBuffer




//CONCERTAR ESTE ALGORITIMO
object LearningLloyds{
  private var clusters: Array[Cluster] = Array.empty
  private var numOfDimension:Int = 0
  private var numOfClusters:Int  =0

  def initModel(numOfClusters: Int, numOfDimension:Int): Unit = {
    this.numOfClusters = numOfClusters
    this.numOfDimension = numOfDimension
    for (i <- 1 to numOfClusters) {
      clusters = Cluster.createCluster(numOfDimension) +: clusters
    }
  }

  def distanceClusterFromData(cluster:Cluster, data:Array[Float]) : Float =
    OperatorArray.euclideanDistance(cluster.centroid,data)

  //ASSIGN DATA TO CLUSTER WITH MINIMUN DISTANCE
  def assignDataToCluster(data:Array[Float]): Unit = {
    var clustersOrdenedByDistance = clusters
      .map(cluster => (cluster, distanceClusterFromData(cluster, data)))
      .sortBy(t => t._2)

    Cluster.addData(clustersOrdenedByDistance(0)._1,data)
  }

  def cleanClusterData(cluster:Cluster):Cluster = {
    return Cluster(cluster.centroid, new ArrayBuffer[Array[Float]])
  }

  //CACULETE THE MEAN OF ALL POINTS IN THE CLUSTERS
  def calculeClusterMean(cluster: Cluster):Array[Float] = {
    if (cluster.data.isEmpty)
      return Cluster.createCluster(numOfDimension).centroid

    var clusterDataMean: Array[Float] =
      cluster.data.reduce( (d1,d2) => OperatorArray.sumArrayCoordinates(d1,d2))
      .map(k => k / cluster.data.length)

    return clusterDataMean
  }



  def optimize(inputs:Array[Array[Float]], iterations:Int): Array[Cluster] = {
    inputs.foreach( d => assignDataToCluster(d))

    for( _ <- 1 to iterations) {
      clusters = clusters
        .map(c => Cluster.setCentroid(c, calculeClusterMean(c)))
        .map(c => cleanClusterData(c))

      inputs.foreach(d => assignDataToCluster(d))
    }
    return clusters
  }

}
