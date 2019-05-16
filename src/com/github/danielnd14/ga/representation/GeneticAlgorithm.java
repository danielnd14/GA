package com.github.danielnd14.ga.representation;

public final class GeneticAlgorithm {

	public static Solution simule(final AbstractPopulation population, final StopCriterion stopCriterion) {
		population.sortMembers();
		while (stopCriterion.continues(population)) {
			population.crossOver();
			population.doMutation();
			population.merge();
		}

		return population.getBestSolution();
	}

	public static Solution simule(final AbstractPopulation population) {
		return simule(population, new StopCriterion() {
			int generation = 0;

			@Override
			public boolean continues(final AbstractPopulation population) {
				generation++;
				return population.MAX_GENERATIONS != generation;
			}
		});
	}
}
