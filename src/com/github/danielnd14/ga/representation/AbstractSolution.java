package com.github.danielnd14.ga.representation;

public abstract class AbstractSolution implements Solution {

	private Double fitness = null;
	private double penalty = 1;

	protected abstract double calculateFitness();

	@Override
	public Double fitness() {
		if (fitness == null) {
			fitness = calculateFitness();
		}
		return fitness * penalty;
	}

	@Override
	public double forceNewFitness() {
		fitness = calculateFitness();
		return fitness * penalty;
	}

	@Override
	public void setPenalty(double percent) {
		this.penalty = percent;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
