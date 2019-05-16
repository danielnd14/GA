package com.github.danielnd14;

import com.github.danielnd14.ga.representation.AbstractPopulation;
import com.github.danielnd14.ga.representation.GeneticAlgorithm;
import com.github.danielnd14.ga.representation.StopCriterion;
import com.github.danielnd14.releasesproblem.ReleasesPopulationBuilder;

import java.time.Duration;
import java.time.Instant;

public class ReleaseProblemMain {
	public static void main(String[] args) {
		var start = Instant.now();
		//First example
		AbstractPopulation pop1 = new ReleasesPopulationBuilder()
				.withBinaryTournamentSelection()
				.withRankMergeStratey()
				.withPopulationSize(300)
				.build();

		var sl1 = GeneticAlgorithm.simule(pop1);

		System.out.println(sl1);
		System.out.println(Duration.between(start, Instant.now()).toMillis() + " milliseconds");
		System.out.println("\n---------------------------------------------------------------");

		//Second example
		start = Instant.now();

		AbstractPopulation pop2 = new ReleasesPopulationBuilder()
				.withBinaryTournamentSelection()
				.withDescMergeStratey()
				.withElitismNumber(2)
				.withPopulationSize(300)
				.build();

		var sl2 = GeneticAlgorithm.simule(pop2, new StopCriterion() {

			double avg = 0;

			@Override
			public boolean continues(final AbstractPopulation population) {
				final var averageFitnees = population.getAverageFitnees();
				boolean ret = averageFitnees != avg;
				avg = averageFitnees;
				return ret;
			}
		});


		System.out.println(sl2);
		System.out.println(Duration.between(start, Instant.now()).toMillis() + " milliseconds");

		System.out.println("________________________________________best of both________________________________________\n");

		if (Math.max(sl1.fitness(), sl2.fitness()) == sl1.fitness()) {
			System.out.println(sl1);
		} else {
			System.out.println(sl2);
		}
	}
}