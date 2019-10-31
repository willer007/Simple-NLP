package simple.nlp.learning.evolutionary

import simple.nlp.operator.OperatorArray

import scala.util.Random

object LearningGenetic {
  var generationSize = 9
  var cromossomeSize = 0

  var generation: Array[Array[Float]] = Array.empty
  var best: Array[Array[Float]] = Array.empty
  var weights: Array[Float] = Array.empty

  def initModel() = {
    generation = createGen()
    best = createGen()
    weights = createChromossome()

  }

  def optimize(objective: Int, tokens: Array[Float], iterations: Int): Array[Array[Float]] = {

    //CONCERTAR ESTA PARTE
    for (i <- 0 to iterations) {
      best = Array.empty
      var auxMin: Array[Float] = Array.empty
      for (chromosome <- generation) {
        auxMin = auxMin :+ math.abs(OperatorArray.linearCombination(chromosome, tokens))
      }

      var auxSorted = (generation zip auxMin).sortBy(t => math.abs(objective * 10 - t._2))

      weights = auxSorted(0)._1

      //0 ate 3 inclusivo
      best = auxSorted.slice(0, 3).map(g => g._1)
      best = best :+ crossover(best(0), best(1))
      best = best :+ mutation(best(0))
      best = best :+ mutation(best(1))
      best = best :+ mutation(best(2))
      best = best :+ createChromossome()


      generation = best
      println("GERACAO: \n" + generation(0).slice(0, 10).map(a => a.toString).reduce((a, b) => a + " \n" + b))
      println("PESOS: \n" + weights.slice(0, 10).map(a => a.toString).reduce((a, b) => a + " \n " + b))
      println("PESO ITERACAO " + i + ": " + math.abs(objective - OperatorArray.linearCombination(tokens,weights).floatValue()))
      println("\n")

    }


    print("PESO FINAL: " + OperatorArray.linearCombination(tokens ,weights).floatValue())
    return best
  }

  def objectiveFunction(sumValue: Float, obj: Float): Float = math.abs(sumValue - obj)





  //CROSSOVER PELA METADE DO CROMOSSOMO ESTA OK
  def crossover(cromossome1: Array[Float], cromossome2: Array[Float]) =
    cromossome1.slice(0, cromossome1.length / 2) ++ cromossome2.slice(cromossome1.length / 2, cromossome1.length)


  //MUTCAO DE 10% ESTA OK
  def mutation(cromossome: Array[Float]): Array[Float] = {
    for (_ <- 0 to cromossome.length / 10) {
      cromossome(Random.nextInt(cromossomeSize)) = Random.nextFloat()
    }
    cromossome
  }


  //FUNCAO ESTA OK
  def createGen(): Array[Array[Float]] = Array.fill(generationSize) {
    createChromossome()
  }


  //FUNCAO ESTA OK
  def createChromossome(): Array[Float] = Array.fill(cromossomeSize) {
    Random.nextFloat()
  }

}
