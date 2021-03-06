package com.github.danielnd14.schaffersf6problem;

import com.github.danielnd14.ga.builder.impl.AbstractPopulationBuilder;
import com.github.danielnd14.ga.representation.AbstractPopulation;

public final class SchafferF6PopulationBuilder extends AbstractPopulationBuilder {

	@Override
	public AbstractPopulation build() {
		if (this.mergeStrategy == null || this.selectStrategy == null) {
			throw new RuntimeException("MergeStrategy or SelectStrategy is null");
		}
		return new SchafferF6Population(mergeStrategy, selectStrategy, populationSize, elitismNumber, maxGenerations, crosOverRate, mutationRate);
	}
}