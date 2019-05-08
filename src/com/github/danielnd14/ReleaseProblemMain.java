package com.github.danielnd14;

import com.github.danielnd14.ga.representation.AbstractPopulation;
import com.github.danielnd14.ga.representation.GeneticAlgorithm;
import com.github.danielnd14.releasesproblem.ReleasesPopulationBuilder;

import java.time.Duration;
import java.time.Instant;

public class ReleaseProblemMain {
	public static void main(String[] args) {
		var start = Instant.now();

		AbstractPopulation a = new ReleasesPopulationBuilder()
				.withBinaryTournamentSelection()
				.withRankMergeStratey()
				.withPopulationSize(800)
				.withMutationRate(0)
				.build();

		System.out.println(new GeneticAlgorithm(a).simule());

		System.out.println(Duration.between(start, Instant.now()).toMillis() + " milliseconds");


	}
}
