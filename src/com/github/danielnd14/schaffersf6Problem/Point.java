package com.github.danielnd14.schaffersf6Problem;

import com.github.danielnd14.ga.representation.AbstractSolution;
import com.github.danielnd14.ga.representation.Chromosome;

public class Point extends AbstractSolution {

	private final Chromosome<Integer[]> chromosome;

	public Point(Chromosome<Integer[]> chromosome) {
		this.chromosome = chromosome;
	}

	@Override
	protected double calculateFitness() {
		//fixme implementar o calculo de aptidao
		var value = chromosome.getValue();
		return 0;
	}

	@Override
	public Chromosome<Integer[]> chromosome() {
		return chromosome;
	}

	@Override
	public void setPenalty(double percent) {

	}
}