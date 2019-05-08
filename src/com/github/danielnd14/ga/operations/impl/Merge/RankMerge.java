package com.github.danielnd14.ga.operations.impl.Merge;

import com.github.danielnd14.ga.operations.MergeOperation;
import com.github.danielnd14.ga.representation.AbstractPopulation;

public class RankMerge implements MergeOperation {
	@Override
	public void merge(final AbstractPopulation population) {
		final var offSpring = population.getOffSpring();
		offSpring.parallelStream().forEach(population::addMember);
		offSpring.clear();
		population.sortMembers();
		final var members = population.getMembers();
		members.subList(0, population.POPULATION_SIZE).clear();
	}
}
