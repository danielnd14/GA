package com.github.danielnd14.ga.operations.impl.Merge;

import com.github.danielnd14.ga.operations.MergeOperation;
import com.github.danielnd14.ga.representation.AbstractPopulation;

import java.util.Comparator;

public final class DescMerge implements MergeOperation {
	@Override
	public void merge(final AbstractPopulation population) {
		final var offSpring = population.getOffSpring();
		final var members = population.getMembers();
		members.subList(0, population.POPULATION_SIZE - (population.ELITISM)).clear();
		offSpring.sort(Comparator.reverseOrder());
		for (int i = 0; i < population.POPULATION_SIZE - population.ELITISM; i++) {
			population.addMember(offSpring.get(i));
		}
		offSpring.clear();
		population.sortMembers();
	}
}
