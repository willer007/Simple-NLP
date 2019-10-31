package simple.nlp.encoder.wordembedding

object testEncoderLSI {
/*
  def main(args: Array[String]): Unit = {


    val FILE_DATASET_NAME = "dataset_total_v3_gen"
    val DATASET = "C:\\Users\\willer.quintanilha\\Desktop\\Reports\\DATASET\\" + FILE_DATASET_NAME + ".simple.csv"

    //LOAD DATASET
    var dataset = SimpleNLP.Dataset.load(DATASET)

    //PROCESSS ALL TOKENS
    var datasetTokens = dataset.map(d => d.content(0)).map(s => s.split("\\|").filter(_.matches("^[A-Za-z]*$")).filter(_.size > 4)).slice(0, 2000)
    var sortedDatasetTokens = datasetTokens.flatten.sortBy(t => t)
    var sortedDatasetTokensSet = datasetTokens.flatten.sortBy(t => t).toSet




    //FIT TOKENS ON ENCODER
    SimpleNLP.EncoderBinary.loadTokens(sortedDatasetTokensSet)

    //PREPARE DATA TO LSI MODEL TROUGTH SPARK
    var datasetPreProcessed = datasetTokens.map(s => Vectors.dense(SimpleNLP.EncoderBinary.encodeBinary(s).map(v => v.toDouble)))
    var datesetPreProcessedWithSentences = Tuple2(datasetPreProcessed, datasetTokens.slice(0,2000))

    val fileDotProductSimilarity = "C:\\Users\\willer.quintanilha\\Desktop\\Reports\\DATASET\\dataset_dot_sim.simple.csv"
    val dotProductSimilarityWriter = new FileWriter(new File(fileDotProductSimilarity))

    //CALCULE NEW QUERY
    var queryArray = Array.fill(SimpleNLP.EncoderBinary.tokensPos.size){0d}
    queryArray(SimpleNLP.EncoderBinary.tokensPos.get("desligamento").get) = 1
    queryArray(SimpleNLP.EncoderBinary.tokensPos.get("programado").get) = 1



    dotProductSimilarityWriter.write("SENTENCE;SIM\n")
    for (sentence <- datasetPreProcessed) {
      dotProductSimilarityWriter.write(EncoderBinary.decode(sentence.toArray.map(_.toInt)).reduce((a,b)=> a + " | " + b ) + ";" +EncoderLSI.dotProduct(sentence.toArray,queryArray) + "\n")
    }

    //GET SVD DATA FROM LSI ENCODER
    var dataSvdProcessed = EncoderLSI.encode(datesetPreProcessedWithSentences)

    //USE SVD DATA TO ENCODE BY LSI
    var dataSvdAux = (dataSvdProcessed.V zip dataSvdProcessed.sentences).map(t=> DataLSI(t._1 , t._2))


    //USE RANK 2 APROXIMATION OF V TO REPRESENT DOCUMENTS
    //var queryCsvFile = "C:\\Users\\willer.quintanilha\\Desktop\\Reports\\DATASET\\query_tokens.simple.csv"
    //val queryWriter = new FileWriter(new File(queryCsvFile))
    //SimpleNLP.EncoderBinary.tokensPos.foreach((i) => queryWriter.write(i._1 + ";" + i._2 + "\n"))



    //var qNew = EncoderLSI.calcQuery(q,dataSvdProcessed.s,dataSvdProcessed.U)

    //SORT BY SIM RANK
    //CALCULE DISTANCE OF DOCUMENTS TO QUERY WITH COSINE SIMILARITIES BY ESCALAR PRODUCT
    //var ordenedDocumentsBySimilarity = EncoderLSI.similarityRank(dataSvdAux,qNew)




    //WRITE DATA IN CSV
//    var file_name = "C:\\Users\\willer.quintanilha\\Desktop\\Reports\\DATASET\\dados_processados_lsi_fatura.simple.csv"
//    val writer = new FileWriter(new File(file_name))
//    writer.write("U;V;W;X;Y;Z;SENTENCES\n")
//    ordenedDocumentsBySimilarity.foreach(t => writer.write( t.position(0) + ";" +
//                                                            t.position(1) + ";" +
//                                                            t.position(2) + ";" +
//                                                            t.position(3) + ";" +
//                                                            t.position(4) + ";" +
//                                                            t.position(5) + ";" +
//                                                            t.sentence.reduce((a,b)=> a + " | " + b )+"\n"))
//    writer.close()




  }
*/
}



