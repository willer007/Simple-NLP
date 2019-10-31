package nlp.learning.evolutionary

import simple.nlp.learning.evolutionary.LearningGenetic

object LearningGeneticTest {

  def main(args: Array[String]): Unit = {
    LearningGenetic.initModel(9,10)

    //MUTATION IN 10%
    LearningGenetic.setMutationChance(10)

    //MANTAIN BEST 3 INDIVIDUALS IN GENERATION
    LearningGenetic.setElitismSize(3)

    //EXECUTE 2 CROSSOVER WITH THE ELITE
    LearningGenetic.setCrossoverSize(2)


    val input:Array[Float] = Array(1,5,6,5,7,4,8,1,4,3)
    var testSingleInput = LearningGenetic.optimize(1,input,200);


    val mutipleInput:Array[Array[Float]] = Array.fill(10) (input)
    var testMutipleInputs = LearningGenetic.optimize(1,mutipleInput,200);

  }

}



