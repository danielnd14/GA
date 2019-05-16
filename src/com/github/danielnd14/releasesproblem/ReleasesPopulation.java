package com.github.danielnd14.releasesproblem;

import com.github.danielnd14.ga.operations.MergeOperation;
import com.github.danielnd14.ga.operations.SelectionOperation;
import com.github.danielnd14.ga.representation.AbstractPopulation;
import com.github.danielnd14.ga.representation.Solution;

import java.util.Arrays;

public final class ReleasesPopulation extends AbstractPopulation {
	private static double precoMaximo = 125.0;

	ReleasesPopulation(final MergeOperation mergeStrategy,
					   final SelectionOperation selectionStrategy,
					   final int populationSize,
					   final int elitism,
					   final int maxGenerations,
					   final double crossoverRate,
					   final double mutationRate) {
		super(mergeStrategy, selectionStrategy, populationSize, elitism, maxGenerations, crossoverRate, mutationRate);
	}

	private static Releases giveMeSon(final Integer[] partA, final Integer[] partB) {
		final var representation = new Integer[Releases.sizeOfChromosome];
		for (int i = 0; i < Releases.sizeOfChromosome; i++) {
			if (i < partA.length) {
				representation[i] = partA[i];
			} else {
				representation[i] = partB[i - partA.length];
			}
		}

		return new Releases(representation);
	}

	@Override
	protected void crossOver() {
		while (this.offSpring.size() < this.POPULATION_SIZE) {
			final var fatherA = (Releases) selectionStrategy.select(this);
			final var fatherB = (Releases) selectionStrategy.select(this);

			if (r.nextDouble() < this.CROSSOVER_RATE) {
				final var cutPoint = r.nextInt(Releases.sizeOfChromosome);
				final var genValuesFatherA = (Integer[]) fatherA.chromosome().getValue();
				final var genValuesFatherB = (Integer[]) fatherB.chromosome().getValue();
				final var part1FatherA = Arrays.copyOfRange(genValuesFatherA, 0, cutPoint);
				final var part2FatherA = Arrays.copyOfRange(genValuesFatherA, cutPoint, Releases.sizeOfChromosome);
				final var part1FatherB = Arrays.copyOfRange(genValuesFatherB, 0, cutPoint);
				final var part2FatherB = Arrays.copyOfRange(genValuesFatherB, cutPoint, Releases.sizeOfChromosome);
				final var son1 = giveMeSon(part1FatherA, part2FatherB);
				final var son2 = giveMeSon(part1FatherB, part2FatherA);

				if (isValid(son1)) {
					this.offSpring.add(son1);
				}

				if (isValid(son2)) {
					this.offSpring.add(son2);
				}
			} else {
				try {
					offSpring.add((Solution) fatherA.clone());
					offSpring.add((Solution) fatherB.clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doMutation() {
		this.offSpring.forEach(solution -> {
			if (r.nextFloat() <= this.MUTATION_RATE) {
				final var chromosome = ((Releases) solution).chromosome().getValue();
				chromosome[r.nextInt(chromosome.length)] = r.nextInt(3) + 1;
				if (!isValid(solution)) {
					repair(solution);
				}
				solution.forceNewFitness();
			}
		});
	}

	@Override
	protected Solution getRandomSolution() {

		Integer[] representation = new Integer[Releases.sizeOfChromosome];
		for (int i = 0; i < Releases.sizeOfChromosome; i++) {
			representation[i] = r.nextInt(4);
		}
		final var release = new Releases(representation);
		if (!isValid(release)) {
			return null;
		}
		return release;
	}

	@Override
	protected boolean isValid(final Solution solution) {
		final var sol = (Releases) solution;
		sol.organizaSprint();
		double priceSprint1 = sol.sprint1.stream().mapToDouble(Requirement::getCost).sum();
		double priceSprint2 = sol.sprint2.stream().mapToDouble(Requirement::getCost).sum();
		double priceSprint3 = sol.sprint3.stream().mapToDouble(Requirement::getCost).sum();
		return !(priceSprint1 > precoMaximo) && !(priceSprint2 > precoMaximo) && !(priceSprint3 > precoMaximo);
	}

	@Override
	public void addMember(final Solution solution) {

		final Object clone;
		try {
			clone = ((Releases) solution).clone();
			this.members.add((Releases) clone);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void repair(final Solution solution) {

		var releases = (Releases) solution;
		final var value = releases.chromosome().getValue();
		double sumR1 = 0;
		double sumR2 = 0;
		double sumR3 = 0;
		for (int i = 0; i < value.length; i++) {
			if (value[i] == 1) {
				sumR1 += Releases.REQUIREMENTS[i].getCost();
				if (sumR1 > precoMaximo) {
					value[i] = 0;
				}
				continue;
			}
			if (value[i] == 2) {
				sumR2 += Releases.REQUIREMENTS[i].getCost();
				if (sumR2 > precoMaximo) {
					value[i] = 0;
				}
				continue;
			}
			if (value[i] == 3) {
				sumR3 += Releases.REQUIREMENTS[i].getCost();
				if (sumR3 > precoMaximo) {
					value[i] = 0;
				}
			}
		}
		releases.organizaSprint();
	}
}
