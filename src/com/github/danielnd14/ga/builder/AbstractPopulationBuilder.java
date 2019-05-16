package com.github.danielnd14.ga.builder;

import com.github.danielnd14.ga.operations.MergeOperation;
import com.github.danielnd14.ga.operations.SelectionOperation;
import com.github.danielnd14.ga.operations.impl.Merge.DescMerge;
import com.github.danielnd14.ga.operations.impl.Merge.RankMerge;
import com.github.danielnd14.ga.operations.impl.Selection.BinaryTournamentSelection;
import com.github.danielnd14.ga.operations.impl.Selection.RouleteSelection;

public abstract class AbstractPopulationBuilder implements PopulationBuilder {
	protected SelectionOperation selectStrategy;
	protected MergeOperation mergeStrategy;
	protected int maxGenerations = 200;
	protected int populationSize = 200;
	protected double mutationRate = 0.01;
	protected double crosOverRate = 0.9;
	protected int elitismNumber = 0;

	@Override
	public PopulationBuilder withRouleteSelection() {
		this.selectStrategy = new RouleteSelection();
		return this;
	}

	@Override
	public PopulationBuilder withBinaryTournamentSelection() {
		this.selectStrategy = new BinaryTournamentSelection();
		return this;
	}

	@Override
	public PopulationBuilder withCustomSelection(final SelectionOperation strategy) {
		this.selectStrategy = strategy;
		return this;
	}

	@Override
	public PopulationBuilder withRankMergeStratey() {
		this.mergeStrategy = new RankMerge();
		return this;
	}

	@Override
	public PopulationBuilder withDescMergeStratey() {
		this.mergeStrategy = new DescMerge();
		return this;
	}

	@Override
	public PopulationBuilder withCustomMergeStrategy(final MergeOperation mergeStrategy) {
		this.mergeStrategy = mergeStrategy;
		return this;
	}

	@Override
	public PopulationBuilder withMaxGenerations(final int maxGenerations) {
		this.maxGenerations = maxGenerations;
		return this;
	}

	@Override
	public PopulationBuilder withMutationRate(final double mutationRate) {
		this.mutationRate = mutationRate;
		return this;
	}

	@Override
	public PopulationBuilder withElitismNumber(final int number) {
		this.elitismNumber = number;
		return this;
	}

	@Override
	public PopulationBuilder withPopulationSize(final int size) {
		this.populationSize = size;
		return this;
	}

	@Override
	public PopulationBuilder withCrosOverRate(final double crosOverRate) {
		this.crosOverRate = crosOverRate;
		return this;
	}
}
