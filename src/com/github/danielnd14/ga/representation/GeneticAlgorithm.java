package com.github.danielnd14.ga.representation;

public final class GeneticAlgorithm {
	private final AbstractPopulation population;

	public GeneticAlgorithm(AbstractPopulation population) {
		this.population = population;
	}

	public Solution simule() {
		final var maxGenerations = population.MAX_GENERATIONS;
		var generationCount = 0;
		population.sortMembers();
		while (generationCount < maxGenerations) {
			population.crossOver();
			population.doMutation();
			population.merge();
			generationCount++;
		}
		return population.getBestSolution();
	}
}
