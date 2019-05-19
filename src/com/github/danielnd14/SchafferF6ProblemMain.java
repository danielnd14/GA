package com.github.danielnd14;

import com.github.danielnd14.ga.representation.GeneticAlgorithm;
import com.github.danielnd14.schaffersf6problem.SchafferF6PopulationBuilder;

import java.time.Duration;
import java.time.Instant;

public class SchafferF6ProblemMain {
	public static void main(String[] args) {
		var start = Instant.now();
		var pop = new SchafferF6PopulationBuilder()
				.withBinaryTournamentSelection()
				.withDescMergeStratey()
				.withCrosOverRate(0.9)
				.withElitismNumber(2)
				.withMutationRate(0.05)
				.build();

		var sol = GeneticAlgorithm.simule(pop, false);
		System.out.println("\n" + sol);
		System.out.println("Duration: " + Duration.between(start, Instant.now()).toMillis() + " milliseconds");
	}
}