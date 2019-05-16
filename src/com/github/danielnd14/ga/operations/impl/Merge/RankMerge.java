package com.github.danielnd14.ga.operations.impl.Merge;

import com.github.danielnd14.ga.operations.MergeOperation;
import com.github.danielnd14.ga.representation.AbstractPopulation;

import java.util.Comparator;

public final class RankMerge implements MergeOperation {
	@Override
	public void merge(final AbstractPopulation population) {
		final var offSpring = population.getOffSpring();
		offSpring.sort(Comparator.reverseOrder());
		offSpring.subList(0, population.POPULATION_SIZE).forEach(population::addMember);
		offSpring.clear();
		population.sortMembers();
		final var members = population.getMembers();
		members.subList(0, population.POPULATION_SIZE).clear();
	}
}
