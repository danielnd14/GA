package com.github.danielnd14.ga.operations;

import com.github.danielnd14.ga.representation.AbstractPopulation;
import com.github.danielnd14.ga.representation.Solution;

@FunctionalInterface
public interface SelectionOperation {
	Solution select(final AbstractPopulation population);
}