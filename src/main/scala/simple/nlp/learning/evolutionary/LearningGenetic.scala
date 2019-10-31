package simple.nlp.learning.evolutionary

import simple.nlp.operator.OperatorArray

import scala.util.Random

object LearningGenetic {
  private var generationSize = 0
  private var chromosomeSize = 0
  private var elitismSize = 3;
  private var crossoverSize = 2;
  private var mutationChance = 10;


  def initModel(generationSize: Int, chromosomeSize: Int) = {
    this.generationSize = generationSize
    this.chromosomeSize = chromosomeSize
  }


  def processNextGeneration( chromossomeSortedByObjective:Array[(Array[Float],Float)] ):Array[Array[Float]] = {
    var nextGeneration: Array[Array[Float]] = Array.empty

    val elitismIndividuals =
      chromossomeSortedByObjective.slice(0, elitismSize).map(cromossome => cromossome._1)

    nextGeneration = nextGeneration ++ elitismIndividuals

    for (elitismIndividual <- elitismIndividuals) {
      nextGeneration = nextGeneration :+ mutation(elitismIndividual)
    }

    for (index <- 0 until crossoverSize){
      nextGeneration = nextGeneration :+ crossover(nextGeneration(index), nextGeneration(index + 1))
    }

    for (index <- 0 until  generationSize - nextGeneration.length ) {
      nextGeneration = nextGeneration :+ createChromosome()
    }

    return nextGeneration

  }

  def optimize(objective: Int, input: Array[Float], iterations: Int): Array[Float] = {

    var generation: Array[Array[Float]] = createGen()
    var weights: Array[Float] = createChromosome()
    var prediction = 0.0

    for (i <- 0 to iterations) {

      val chromossomeSortedByObjective = generation
        .map(chromosome => (chromosome, OperatorArray.linearCombination(chromosome, input)))
        .sortBy(t => math.abs(objective - t._2))


      weights = chromossomeSortedByObjective(0)._1
      prediction = chromossomeSortedByObjective(0)._2

      generation = processNextGeneration(chromossomeSortedByObjective)

      println("ERROR ITERATION " + i + ": " + math.abs(objective - prediction))
    }

    println("WEIGHTS * INPUT =  " + prediction)
    return weights
  }


  def optimize(objective: Int, input: Array[Array[Float]], iterations: Int): Array[Float] = {

    var generation: Array[Array[Float]] = createGen()
    var weights: Array[Float] = createChromosome()
    var prediction = 0.0

    for (i <- 0 to iterations) {

      val chromossomeSortedByObjective = generation
        .map(chromosome => (chromosome, input
          .map(i => OperatorArray.linearCombination(chromosome, i)).sum))
        .sortBy(t => math.abs(objective - t._2))

      weights = chromossomeSortedByObjective(0)._1
      prediction = chromossomeSortedByObjective(0)._2

      generation = processNextGeneration(chromossomeSortedByObjective)

      println("ERROR ITERATION " + i + ": " + math.abs(objective - prediction))
    }

    print("WEIGHTS * INPUT =  " + prediction)
    return weights
  }

  

  def crossover(cromossome1: Array[Float], cromossome2: Array[Float]) =
    cromossome1.slice(0, cromossome1.length / 2) ++ cromossome2.slice(cromossome1.length / 2, cromossome1.length)


  def mutation(cromossome: Array[Float]): Array[Float] = {
    var chromossomeAux: Array[Float] = cromossome.map(a => a)
    for (_ <- 0 to cromossome.length / mutationChance) {
      chromossomeAux(Random.nextInt(chromosomeSize)) = Random.nextFloat()
    }
    chromossomeAux
  }

  def createGen(): Array[Array[Float]] = Array.fill(generationSize) {
    createChromosome()
  }

  def createChromosome(): Array[Float] = Array.fill(chromosomeSize) {
    Random.nextFloat()
  }

  def setMutationChance(mutationChance: Int) = this.mutationChance = mutationChance

  def getMutationChance() = this.mutationChance

  def setElitismSize(elitismSize: Int) = this.elitismSize = elitismSize

  def getElitismSize(selection: Int): Int = this.elitismSize

  def setCrossoverSize(crossover:Int) = this.crossoverSize = crossoverSize

  def getCrossoverSize() = this.crossoverSize




}
