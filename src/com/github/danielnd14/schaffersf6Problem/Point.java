package com.github.danielnd14.schaffersf6Problem;

import com.github.danielnd14.ga.helpers.NumberHelper;
import com.github.danielnd14.ga.representation.AbstractSolution;
import com.github.danielnd14.ga.representation.Chromosome;

import java.util.Arrays;

public class Point extends AbstractSolution {

	static int sizeOfChromosome = 46;

	private final Chromosome<Integer[]> chromosome;

	Point(Chromosome<Integer[]> chromosome) {
		this.chromosome = chromosome;
	}

	@Override
	protected double calculateFitness() {
		final var bin = chromosome.getValue();
		final var dim = bin.length / 2;

		var x = NumberHelper.toDouble(Arrays.copyOfRange(bin, 0, dim));
		var y = NumberHelper.toDouble(Arrays.copyOfRange(bin, dim, bin.length));

		x = -100 + x * (200) / (Math.pow(2, dim) - 1);
		y = -100 + y * (200) / (Math.pow(2, dim) - 1);

		final var xsqrdysqrd = x * x + y * y;
		final var numerator = (Math.pow(Math.sin(Math.sqrt(xsqrdysqrd)), 2) - 0.5);
		final var denominator = Math.pow((1 + 0.001 * xsqrdysqrd), 2);

		return (float) (0.5 - numerator / denominator);
	}

	@Override
	public Chromosome<Integer[]> chromosome() {
		return chromosome;
	}

	@Override
	public String toString() {
		return fitness().toString();
	}
}