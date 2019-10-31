package simple.nlp.learning.evolutionary

object LearningGeneticTest {

  def main(args: Array[String]): Unit = {
    LearningGenetic.initModel(9,10)

    //MUTATION IN 10%
    LearningGenetic.setMutationChance(10)

    //MANTAIN BEST 3 INDIVIDUALS IN GENERATION
    LearningGenetic.setElitismSize(3)

    //EXECUTE 2 CROSSOVER WITH THE ELIT
    LearningGenetic.setCrossoverSize(2)



    val input:Array[Float] = Array(1,5,6,5,7,4,8,1,4,3)
    var teste = LearningGenetic.optimize(1,input,200);


  }

}
