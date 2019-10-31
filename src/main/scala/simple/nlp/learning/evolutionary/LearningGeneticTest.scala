package simple.nlp.learning.evolutionary

object LearningGeneticTest {

  def main(args: Array[String]): Unit = {
    LearningGenetic.initModel(9,10)
    var teste = LearningGenetic.optimize(1,Array(1,5,6,5,7,4,8,1,4,3),200);

    print("teste")

  }

}
