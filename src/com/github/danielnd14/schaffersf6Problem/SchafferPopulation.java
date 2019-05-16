package com.github.danielnd14.schaffersf6Problem;

import com.github.danielnd14.ga.operations.MergeOperation;
import com.github.danielnd14.ga.operations.SelectionOperation;
import com.github.danielnd14.ga.representation.AbstractPopulation;
import com.github.danielnd14.ga.representation.Solution;

public final class SchafferPopulation extends AbstractPopulation {
	//fixme implementar essa representaçao
	public SchafferPopulation(MergeOperation mergeStrategy, SelectionOperation selectionStrategy, int populationSize, int elitism, int maxGenerations, double crossoverRate, double mutationRate) {
		super(mergeStrategy, selectionStrategy, populationSize, elitism, maxGenerations, crossoverRate, mutationRate);
	}

	@Override
	protected void crossOver() {

	}

	@Override
	protected void doMutation() {

	}

	@Override
	protected Solution getRandomSolution() {
		//fixme implementar a geraçao aleatoria de individuos
		return null;
	}

	@Override
	protected boolean isValid(Solution solution) {
		return false;
	}

	@Override
	public void addMember(Solution solution) {

	}

	@Override
	protected void repair(Solution solution) {

	}
}
