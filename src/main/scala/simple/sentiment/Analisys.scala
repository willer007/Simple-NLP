package simple.sentiment

import simple.csv.SimpleCSV
import simple.nlp.SimpleNLP

object Analisys {

  def main(args: Array[String]): Unit = {

   //NORMALIZAÇÃO E CARREGAMENTO DO DATASET
    var dataset = SimpleCSV.readCSV("C:\\Users\\Willer\\Desktop\\tcc_dataset\\tcc_dataset_tweets_100k.csv")
      .replaceAll("[,.!?\"]","")
      .replaceAll("@\\w+","")
      .replaceAll("http.+\\s","")
      .toLowerCase()

    //SEPARAÇÃO DO DATASET EM MULTIPLAS LINHAS
    var datasetRows = dataset.split("\r\n").slice(0,1000)

    //CARREGAMENTO DE TODOS OS TOKENS PERTENCENTES AO DATASET
    var allTokens: Set[String] = SimpleNLP.Tokenizator.tokenizeByWhitespace(datasetRows.reduce((a,b) => a + " " + b)).toSet
    SimpleNLP.EncoderBinary.loadTokens(allTokens)

    //TOKENIZACAO
    var datasetTokenized = datasetRows.map(row => SimpleNLP.Tokenizator.tokenizeByWhitespace(row))
    //ENCODING
    var datasetEncoded = datasetTokenized.map(row => SimpleNLP.EncoderBinary.encodeWithoutCount(row))
    //EXPRESSAO FELIZ
    var happyExpression =  SimpleNLP.EncoderBinary.encodeWithoutCount(Array("happy"))
    //EXPRESSAO TRISTE
    var sadExpression =  SimpleNLP.EncoderBinary.encodeWithoutCount(Array("sad"))
    //ENCODE SEGUNDO MODELO LSI
    var encodedDataset   =datasetRows zip SimpleNLP.EncoderLSI.encode(datasetEncoded,30)
    var happyLSI = SimpleNLP.EncoderLSI.encodeQuery(happyExpression)
    var sadLSI = SimpleNLP.EncoderLSI.encodeQuery(sadExpression)
    //EXPRESSAO FELIZ MODELO LSI
    var happy = encodedDataset.
      sortBy(data => SimpleNLP.SimilarityCossine.similarityCossine(data._2,happyLSI))
    var happyText:String = happy.map(d => d._1).reduce((a,b) =>a + "\n" +b)
    SimpleCSV.writeCSV("C:\\Users\\Willer\\Desktop\\tcc_dataset\\happy.txt",happyText)
    //EXPRESSAO TRISTE MODELO LSI
    var sad = encodedDataset.
      sortBy(data => SimpleNLP.SimilarityCossine.similarityCossine(data._2,sadLSI))
    var sadText:String = sad.map(d => d._1).reduce((a,b) =>a + "\n" +b)
    SimpleCSV.writeCSV("C:\\Users\\Willer\\Desktop\\tcc_dataset\\sad.txt",sadText)
  }

}
