package com.github.danielnd14.ga.representation;

public final class GeneticAlgorithm {

	public static Solution simule(final AbstractPopulation population, final boolean printFitness, final StopCriterion stopCriterion) {
		population.sortMembers();
		while (stopCriterion.continues(population)) {
			population.crossOver();
			population.doMutation();
			population.merge();

			if (printFitness) {
				System.out.println(population.getAverageFitnees() + "\t" + population.getBestSolution().fitness());
			}
		}

		return population.getBestSolution();
	}

	public static Solution simule(final AbstractPopulation population, final boolean printFitness) {
		return simule(population, printFitness, new StopCriterion() {
			int generation = 0;

			@Override
			public boolean continues(final AbstractPopulation population) {
				generation++;
				return population.MAX_GENERATIONS != generation;
			}
		});
	}
}
