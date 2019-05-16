package com.github.danielnd14;

import com.github.danielnd14.ga.representation.GeneticAlgorithm;
import com.github.danielnd14.schaffersf6Problem.SchafferPopulationBuilder;

public class SchafferProblemMain {
	public static void main(String[] args) {
		var pop = new SchafferPopulationBuilder()
				.withBinaryTournamentSelection()
				.withRankMergeStratey()
				.withMaxGenerations(200)
				.build();

		var sol = GeneticAlgorithm.simule(pop);
	}
}
