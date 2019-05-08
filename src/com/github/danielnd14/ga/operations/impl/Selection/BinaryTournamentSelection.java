package com.github.danielnd14.ga.operations.impl.Selection;

import com.github.danielnd14.ga.operations.SelectionOperation;
import com.github.danielnd14.ga.representation.AbstractPopulation;
import com.github.danielnd14.ga.representation.Solution;

public class BinaryTournamentSelection implements SelectionOperation {
	@Override
	public Solution select(final AbstractPopulation population) {
		var first = population.getRandomMember();
		var second = population.getRandomMember();
		return first.fitness() < second.fitness() ? second : first;
	}
}
