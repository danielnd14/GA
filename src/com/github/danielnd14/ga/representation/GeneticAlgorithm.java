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
}
