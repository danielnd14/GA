package com.github.danielnd14.schaffersf6problem;

import com.github.danielnd14.ga.operations.MergeOperation;
import com.github.danielnd14.ga.operations.SelectionOperation;
import com.github.danielnd14.ga.representation.AbstractPopulation;
import com.github.danielnd14.ga.representation.Solution;

import java.util.Arrays;

public final class SchafferF6Population extends AbstractPopulation {

	SchafferF6Population(MergeOperation mergeStrategy, SelectionOperation selectionStrategy, int populationSize, int elitism, int maxGenerations, double crossoverRate, double mutationRate) {
		super(mergeStrategy, selectionStrategy, populationSize, elitism, maxGenerations, crossoverRate, mutationRate);
	}

	private static SchafferF6Point giveMeSon(final Integer[] partA, final Integer[] partB) {
		final var representation = new Integer[SchafferF6Point.sizeOfChromosome];
		for (int i = 0; i < SchafferF6Point.sizeOfChromosome; i++) {
			if (i < partA.length) {
				representation[i] = partA[i];
			} else {
				representation[i] = partB[i - partA.length];
			}
		}

		return new SchafferF6Point(() -> representation);
	}

	@Override
	protected void crossOver() {
		while (this.offSpring.size() < this.POPULATION_SIZE) {
			final var fatherA = (SchafferF6Point) selectionStrategy.select(this);
			final var fatherB = (SchafferF6Point) selectionStrategy.select(this);

			if (r.nextDouble() <= this.CROSSOVER_RATE) {
				final var cutPoint = r.nextInt(SchafferF6Point.sizeOfChromosome);
				final var genValuesFatherA = (Integer[]) fatherA.chromosome().getValue();
				final var genValuesFatherB = (Integer[]) fatherB.chromosome().getValue();
				final var part1FatherA = Arrays.copyOfRange(genValuesFatherA, 0, cutPoint);
				final var part2FatherA = Arrays.copyOfRange(genValuesFatherA, cutPoint, SchafferF6Point.sizeOfChromosome);
				final var part1FatherB = Arrays.copyOfRange(genValuesFatherB, 0, cutPoint);
				final var part2FatherB = Arrays.copyOfRange(genValuesFatherB, cutPoint, SchafferF6Point.sizeOfChromosome);
				final var son1 = giveMeSon(part1FatherA, part2FatherB);
				final var son2 = giveMeSon(part1FatherB, part2FatherA);
				this.offSpring.add(son1);
				this.offSpring.add(son2);
			} else {
				try {
					offSpring.add((Solution) fatherA.clone());
					offSpring.add((Solution) fatherB.clone());
				} catch (CloneNotSupportedException ignored) {
				}
			}
		}
	}

	@Override
	protected void doMutation() {
		offSpring.parallelStream().forEach(solution -> {
			if (r.nextDouble() <= MUTATION_RATE) {
				final var value = ((SchafferF6Point) solution).chromosome().getValue();
				var index = r.nextInt(SchafferF6Point.sizeOfChromosome);
				value[index] = value[index] == 1 ? 0 : 1;
			}
			solution.forceNewFitness();
		});
	}

	@Override
	protected Solution getRandomSolution() {
		var representation = new Integer[SchafferF6Point.sizeOfChromosome];
		for (int i = 0; i < SchafferF6Point.sizeOfChromosome; i++) {
			representation[i] = r.nextInt(2);
		}
		return new SchafferF6Point(() -> representation);
	}

	@Override
	protected boolean isValid(Solution solution) {
		return true;
	}

	@Override
	public void addMember(Solution solution) {
		var point = ((SchafferF6Point) solution);
		try {
			this.members.add((Solution) point.clone());
		} catch (CloneNotSupportedException ignored) {
		}
	}

	@Override
	protected void repair(Solution solution) {

	}
}
