package com.github.danielnd14;

import com.github.danielnd14.ga.helpers.StatisticHelper;
import com.github.danielnd14.ga.representation.GeneticAlgorithm;
import com.github.danielnd14.ga.representation.Solution;
import com.github.danielnd14.releasesproblem.ReleasesPopulationBuilder;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ReleaseProblemMain {
	public static void main(String[] args) {
		//Statistc test
		var start = Instant.now();

		var solutionsList = new ArrayList<Solution>();
		IntStream.range(0, 100).parallel().forEach(value -> {
			var pop1 = new ReleasesPopulationBuilder()
					.withBinaryTournamentSelection()
					.withRankMergeStratey()
					.withMaxGenerations(250)
					.withPopulationSize(250)
					.build();
			solutionsList.add(GeneticAlgorithm.simule(pop1, false));
		});

		prettyStatsPrint(solutionsList, "Simulation1");
		solutionsList.clear();

		IntStream.range(0, 100).parallel().forEach(value -> {
			var pop1 = new ReleasesPopulationBuilder()
					.withBinaryTournamentSelection()
					.withDescMergeStratey()
					.withElitismNumber(2)
					.withMaxGenerations(250)
					.withPopulationSize(250)
					.build();
			solutionsList.add(GeneticAlgorithm.simule(pop1, false));
		});

		prettyStatsPrint(solutionsList, "Simulation2");
		solutionsList.clear();

		IntStream.range(0, 100).parallel().forEach(value -> {
			var pop1 = new ReleasesPopulationBuilder()
					.withRouleteSelection()
					.withDescMergeStratey()
					.withElitismNumber(2)
					.withMaxGenerations(250)
					.withPopulationSize(250)
					.build();
			solutionsList.add(GeneticAlgorithm.simule(pop1, false));
		});

		prettyStatsPrint(solutionsList, "Simulation3");
		solutionsList.clear();

		System.out.println("\n--------------------------------------------------------------------------");
		System.out.println(Duration.between(start, Instant.now()).toMillis() + " milliseconds");

	}

	private static void prettyStatsPrint(final List<Solution> solutionsList, final String title) {
		final var average = StatisticHelper.getAverage(solutionsList);
		final var max = StatisticHelper.getMax(solutionsList);
		final var min = StatisticHelper.getMin(solutionsList);
		final var standardDeviation = StatisticHelper.getStandardDeviation(solutionsList);
		final var best = solutionsList.parallelStream().max(Solution::compareTo).orElseThrow();
		System.out.println("--------------------------------" + title + "--------------------------------");
		System.out.println("avg:\t\t" + average + "\ndeviation:\t" + standardDeviation + "\nmax:\t\t" + max + "\nmin:\t\t" + min);
		System.out.println(best + "\n");
	}
}