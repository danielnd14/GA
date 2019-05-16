package com.github.danielnd14.releasesproblem;

import com.github.danielnd14.ga.builder.AbstractPopulationBuilder;
import com.github.danielnd14.ga.representation.AbstractPopulation;

public class ReleasesPopulationBuilder extends AbstractPopulationBuilder {

	@Override
	public AbstractPopulation build() {
		if (this.selectStrategy == null || this.mergeStrategy == null) {
			throw new RuntimeException("Aparentemente não foi escolhido uma estrategia de seleção ou uma de merge\n");
		}
		return new ReleasesPopulation(mergeStrategy, selectStrategy, populationSize, elitismNumber, maxGenerations, crosOverRate, mutationRate);
	}
}
