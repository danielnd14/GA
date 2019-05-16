package com.github.danielnd14.ga.operations.impl.Selection;

import com.github.danielnd14.ga.operations.SelectionOperation;
import com.github.danielnd14.ga.representation.AbstractPopulation;
import com.github.danielnd14.ga.representation.Solution;

import java.util.Random;

public final class RouleteSelection implements SelectionOperation {
	private static final Random r = new Random();

	@Override
	public Solution select(final AbstractPopulation population) {
		var sumFit = population.getSumOfFitness();
		var threshold = r.nextDouble() * sumFit;
		var members = population.getMembers();
		var sumA = 0.0;

		for (final Solution s : members) {
			sumA += s.fitness();
			if (sumA >= threshold) {
				return s;
			}
		}
		return members.get(0);
	}
}
