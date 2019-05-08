package com.github.danielnd14.releasesproblem;

import com.github.danielnd14.ga.builder.PopulationBuilder;
import com.github.danielnd14.ga.operations.MergeOperation;
import com.github.danielnd14.ga.operations.SelectionOperation;
import com.github.danielnd14.ga.operations.impl.Merge.DescMerge;
import com.github.danielnd14.ga.operations.impl.Merge.RankMerge;
import com.github.danielnd14.ga.operations.impl.Selection.BinaryTournamentSelection;
import com.github.danielnd14.ga.operations.impl.Selection.RouleteSelection;
import com.github.danielnd14.ga.representation.AbstractPopulation;

public class ReleasesPopulationBuilder implements PopulationBuilder {
	private SelectionOperation selectStrategy;
	private MergeOperation mergeStrategy;
	private int maxGenerations = 200;
	private int sizeOfPopulation = 200;
	private double mutationRate = 0.01;
	private double crosOverRate = 0.9;
	private int elitismNumber = 0;

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
		this.sizeOfPopulation = size;
		return this;
	}

	@Override
	public PopulationBuilder withCrosOverRate(final double crosOverRate) {
		this.crosOverRate = crosOverRate;
		return this;
	}

	@Override
	public AbstractPopulation build() {
		if (this.selectStrategy == null || this.mergeStrategy == null) {
			throw new RuntimeException("Aparentemente não foi escolhido uma estrategia de seleção ou uma de merge\n");
		}
		return new ReleasesPopulation(mergeStrategy, selectStrategy, sizeOfPopulation, elitismNumber, maxGenerations, crosOverRate, mutationRate);
	}
}
