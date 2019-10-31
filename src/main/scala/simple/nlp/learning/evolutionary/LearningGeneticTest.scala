package simple.nlp.learning.evolutionary

object LearningGeneticTest {

  def main(args: Array[String]): Unit = {
    LearningGenetic.generationSize = 9
    LearningGenetic.cromossomeSize = 10
    LearningGenetic.initModel()
    var teste = LearningGenetic.optimize(1,Array(1,5,6,5,7,4,8,1,4,3),200);

    print("teste")

  }

}
