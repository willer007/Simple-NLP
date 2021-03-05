package nlp.learning.clustering

import plotly.{Plotly, Scatter}
import plotly.element.ScatterMode
import plotly.layout.Layout
import simple.nlp.learning.clustering.LearningLloyds

object LearningLloydsTest {

  def main(args: Array[String]): Unit = {

    val numOfDimension = 2
    val numOfClusters = 2
    val numOfInputData = 10
    val numOfInterations = 100

    LearningLloyds.initModel(numOfClusters, numOfDimension)

    var inputs: Array[Array[Float]] = Array.empty
    for (num <- 1 to numOfInputData) {
      inputs = inputs :+ Array.fill[Float](numOfDimension)(num.toFloat)
    }


    var clustersProcessed = LearningLloyds.optimize(inputs,numOfInterations)


    var xPoints =  Seq.empty[Float]
    var yPoints  = Seq.empty[Float]

    var xCentroid=  Seq.empty[Float]
    var yCentroid  = Seq.empty[Float]

    for (clusterIndex  <- clustersProcessed.indices){
      xCentroid = xCentroid :+ clustersProcessed(clusterIndex).centroid(0)
      yCentroid = yCentroid :+ clustersProcessed(clusterIndex).centroid(1)

      for (dataIndex  <-  clustersProcessed(clusterIndex).data.indices){
        xPoints = xPoints :+ clustersProcessed(clusterIndex).data(dataIndex)(0)
        yPoints = yPoints :+ clustersProcessed(clusterIndex).data(dataIndex)(1)
      }
    }

    val trace1 = Scatter(
      xPoints,
      yPoints,
      mode = ScatterMode(ScatterMode.Markers),
      name = "Dados de entrada"
    )

    val trace2 = Scatter(
      xCentroid,
      yCentroid,
      mode = ScatterMode(ScatterMode.Markers),
      name = "Centroides"
    )



    val data = Seq(trace1, trace2)

    val layout = Layout(
      title = "Clusterizacao pelo algoritmo de Lloyds"
    )

    Plotly.plot("div-id.html", data, layout,openInBrowser = false)

  }
}



