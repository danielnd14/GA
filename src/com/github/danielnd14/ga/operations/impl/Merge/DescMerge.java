package com.github.danielnd14.ga.operations.impl.Merge;

import com.github.danielnd14.ga.operations.MergeOperation;
import com.github.danielnd14.ga.representation.AbstractPopulation;
import com.github.danielnd14.ga.representation.Solution;

public final class DescMerge implements MergeOperation {
	@Override
	public void merge(final AbstractPopulation population) {
		final var offSpring = population.getOffSpring();
		final var members = population.getMembers();
		members.subList(0, population.POPULATION_SIZE - (population.ELITISM)).clear();

		offSpring.sort(Solution::compareTo);
		for (int i = population.ELITISM; i < population.POPULATION_SIZE; i++) {
			population.addMember(offSpring.get(i));
		}
		population.sortMembers();
	}
}
