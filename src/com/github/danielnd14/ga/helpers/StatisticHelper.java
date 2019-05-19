package com.github.danielnd14.ga.helpers;

import com.github.danielnd14.ga.representation.Solution;

import java.util.List;

public final class StatisticHelper {
	public static double getVariance(final List<Solution> solutions) {
		var avg = getAverage(solutions);
		return solutions.parallelStream()
				.mapToDouble(value -> Math.pow((value.fitness() - avg), 2))
				.sum() / (solutions.size() - 1);
	}

	public static double getAverage(final List<Solution> solutions) {
		return solutions.parallelStream().mapToDouble(Solution::fitness).average().orElseThrow();
	}

	public static double getStandardDeviation(final List<Solution> solutions) {
		return Math.sqrt(getVariance(solutions));
	}

	public static double getMax(final List<Solution> solutions) {
		return solutions.parallelStream().max(Solution::compareTo).orElseThrow().fitness();
	}

	public static double getMin(final List<Solution> solutions) {
		return solutions.parallelStream().min(Solution::compareTo).orElseThrow().fitness();
	}
}
