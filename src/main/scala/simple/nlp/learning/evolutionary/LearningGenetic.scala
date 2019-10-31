package simple.nlp.learning.evolutionary

import simple.nlp.operator.OperatorArray

import scala.util.Random

object LearningGenetic {
  var generationSize = 9
  var chromosomeSize = 0

  var generation: Array[Array[Float]] = Array.empty
  var weights: Array[Float] = Array.empty

  def initModel() = {
    generation = createGen()
    weights = createChromosome()
  }

  def optimize(objective: Int, input: Array[Float], iterations: Int): Array[Float] = {

    //CONCERTAR ESTA PARTE
    for (i <- 0 to iterations) {


     val chromossomeSortedByObjective = generation
        .map(chromosome => ( chromosome, OperatorArray.linearCombination(chromosome, input)))
          .sortBy(t => math.abs(objective - t._2))


      weights = chromossomeSortedByObjective(0)._1

      var best: Array[Array[Float]] = Array.empty
      best = chromossomeSortedByObjective.slice(0, 3).map(g => g._1)
      best = best :+ mutation(best(0))
      best = best :+ mutation(best(1))
      best = best :+ mutation(best(2))
      best = best :+ crossover(best(0), best(1))
      best = best :+ crossover(best(1), best(2))
      best = best :+ createChromosome()


      generation = best
    //  println("GERACAO: \n" + generation(0).slice(0, 10).map(a => a.toString).reduce((a, b) => a + " \n" + b))
    //  println("PESOS: \n" + weights.slice(0, 10).map(a => a.toString).reduce((a, b) => a + " \n " + b))
      println("PESO ITERACAO " + i + ": " + math.abs(objective - OperatorArray.linearCombination(input,weights).floatValue()))
      println("\n")

    }


    print("PESO FINAL: " + OperatorArray.linearCombination(input ,weights).floatValue())
    return weights
  }

  def objectiveFunction(sumValue: Float, obj: Float): Float = math.abs(sumValue - obj)


  //CROSSOVER PELA METADE DO CROMOSSOMO ESTA OK
  def crossover(cromossome1: Array[Float], cromossome2: Array[Float]) =
    cromossome1.slice(0, cromossome1.length / 2) ++ cromossome2.slice(cromossome1.length / 2, cromossome1.length)


  //MUTACAO DE 10% ESTA OK
  def mutation(cromossome: Array[Float]): Array[Float] = {
    var chromossomeAux:Array[Float] =  cromossome.map( a => a)
    for (_ <- 0 to cromossome.length / 10) {
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

}
