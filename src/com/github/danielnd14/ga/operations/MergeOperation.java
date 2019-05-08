package com.github.danielnd14.ga.operations;

import com.github.danielnd14.ga.representation.AbstractPopulation;

@FunctionalInterface
public interface MergeOperation {
	void merge(final AbstractPopulation population);
}
