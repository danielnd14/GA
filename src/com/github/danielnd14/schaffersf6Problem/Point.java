package com.github.danielnd14.schaffersf6Problem;

import com.github.danielnd14.ga.representation.AbstractSolution;
import com.github.danielnd14.ga.representation.Chromosome;

import java.util.Arrays;

public class Point extends AbstractSolution {

	static int sizeOfChromosome = 44;

	private final Chromosome<Integer[]> chromosome;

	Point(Chromosome<Integer[]> chromosome) {
		this.chromosome = chromosome;
	}

	@Override
	protected double calculateFitness() {
		double x = 0, y = 0;
		final var bin = chromosome.getValue();
		final var dim = bin.length / 2;
		final var binX = Arrays.copyOfRange(bin, 0, dim);
		final var binY = Arrays.copyOfRange(bin, dim, bin.length);

		for (int i = 0; i < dim; i++) {
			x += binX[i] * Math.pow(2, dim - i - 1);
			y += binY[i] * Math.pow(2, dim - i - 1);
		}

		x = -100 + x * (200) / (Math.pow(2, dim) - 1);
		y = -100 + y * (200) / (Math.pow(2, dim) - 1);

		final var xsqrdysqrd = (x * x) + (y * y);
		final var numerator = Math.pow(Math.sin(Math.sqrt(xsqrdysqrd)), 2) - 0.5;
		final var denominator = Math.pow((1 + 0.001 * xsqrdysqrd), 2);

		return 0.5 + (numerator / denominator);
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