package simple.nlp.learning.evolutionary

import simple.nlp.operator.OperatorArray

import scala.util.Random

object LearningGenetic {
  private var generationSize = 0
  private var chromosomeSize = 0
  private var mutation = 10;


  def initModel(generationSize : Int , chromosomeSize:Int ) = {
    this.generationSize = generationSize
    this.chromosomeSize = chromosomeSize
  }


  def optimize(objective: Int, input: Array[Float], iterations: Int): Array[Float] = {

    var generation: Array[Array[Float]] = createGen()
    var weights: Array[Float] = createChromosome()

    for (i <- 0 to iterations) {


     val chromossomeSortedByObjective = generation
       .map(chromosome => ( chromosome, OperatorArray.linearCombination(chromosome, input)))
       .sortBy(t => math.abs(objective - t._2))
       .map(t => t._1)


      weights = chromossomeSortedByObjective(0)

      var best: Array[Array[Float]] = Array.empty
      best = chromossomeSortedByObjective.slice(0, 3)
      best = best :+ mutation(best(0))
      best = best :+ mutation(best(1))
      best = best :+ mutation(best(2))
      best = best :+ crossover(best(0), best(1))
      best = best :+ crossover(best(1), best(2))
      best = best :+ createChromosome()
      generation = best
      println("ERROR ITERATION " + i + ": " + math.abs(objective - OperatorArray.linearCombination(input,weights).floatValue()))
    }

    print("WEIGHTS * INPUT =  " + OperatorArray.linearCombination(input ,weights).floatValue())
    return weights
  }

  def objectiveFunction(sumValue: Float, obj: Float): Float = math.abs(sumValue - obj)


  //CROSSOVER PELA METADE DO CROMOSSOMO ESTA OK
  def crossover(cromossome1: Array[Float], cromossome2: Array[Float]) =
    cromossome1.slice(0, cromossome1.length / 2) ++ cromossome2.slice(cromossome1.length / 2, cromossome1.length)


  //MUTACAO DE 10% ESTA OK
  def mutation(cromossome: Array[Float]): Array[Float] = {
    var chromossomeAux:Array[Float] =  cromossome.map( a => a)
    for (_ <- 0 to cromossome.length / mutation) {
      chromossomeAux(Random.nextInt(chromosomeSize)) = Random.nextFloat()
    }
    chromossomeAux
  }


  //FUNCAO ESTA OK
  def createGen(): Array[Array[Float]] = Array.fill(generationSize) {
    createChromosome()
  }

  //FUNCAO ESTA OK
  def createChromosome(): Array[Float] = Array.fill(chromosomeSize) {
    Random.nextFloat()
  }

  def setMutation(mutationChance:Int) = this.mutation = mutationChance

  def getMutation() = this.mutation

}
