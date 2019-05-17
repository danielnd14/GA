package com.github.danielnd14;

import com.github.danielnd14.ga.representation.GeneticAlgorithm;
import com.github.danielnd14.schaffersf6Problem.SchafferPopulationBuilder;

import java.time.Duration;
import java.time.Instant;

/**
 * Algoritmo não necessariamente pronto, é preciso revisar o codigo e testar
 * fixme revisar, testar e avaliar!!!
 */

public class SchafferProblemMain {
	public static void main(String[] args) {
		var start = Instant.now();
		var pop = new SchafferPopulationBuilder()
				.withBinaryTournamentSelection()
				.withRankMergeStratey()
				.withCrosOverRate(0.89)
				.withMutationRate(0.05)
				.withPopulationSize(300)
				.withMaxGenerations(300)
				.build();

		var sol = GeneticAlgorithm.simule(pop, false);
		System.out.println("\n" + sol);
		System.out.println("Duration: " + Duration.between(start, Instant.now()).toMillis() + " milliseconds");
	}
}
