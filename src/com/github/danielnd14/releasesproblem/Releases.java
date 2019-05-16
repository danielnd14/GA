package com.github.danielnd14.releasesproblem;

import com.github.danielnd14.ga.representation.Chromosome;
import com.github.danielnd14.ga.representation.Solution;

import java.util.HashSet;
import java.util.Set;

public class Releases implements Solution {
	final static int sizeOfChromosome = 10;
	final static Requirement[] REQUIREMENTS = new Requirement[sizeOfChromosome];

	static {
		REQUIREMENTS[0] = mkRequirement(1);
		REQUIREMENTS[1] = mkRequirement(2);
		REQUIREMENTS[2] = mkRequirement(3);
		REQUIREMENTS[3] = mkRequirement(4);
		REQUIREMENTS[4] = mkRequirement(5);
		REQUIREMENTS[5] = mkRequirement(6);
		REQUIREMENTS[6] = mkRequirement(7);
		REQUIREMENTS[7] = mkRequirement(8);
		REQUIREMENTS[8] = mkRequirement(9);
		REQUIREMENTS[9] = mkRequirement(10);
	}

	final Set<Requirement> sprint1 = new HashSet<>();
	final Set<Requirement> sprint2 = new HashSet<>();
	final Set<Requirement> sprint3 = new HashSet<>();
	private final Set<Requirement> sprint0 = new HashSet<>();
	private Chromosome<Integer[]> chromosome;
	private double penalty = 1;
	private Double fitnessCache = null;

	Releases(final Integer[] representation) {
		if (representation.length != sizeOfChromosome) {
			throw new RuntimeException("tamanho da representação inválido");
		}
		chromosome = () -> representation;
		organizaSprint();
	}

	private static double calculeFitness(final Chromosome<Integer[]> chromosome) {
		var value = chromosome.getValue();
		var fit = 0.0;
		for (int i = 0; i < value.length; i++) {

			if (value[i] != 0) {
				fit = fit + (REQUIREMENTS[i].getImportancia() / (value[i] / (double) value.length)) - REQUIREMENTS[i].getRisk() * value[i];
			}
		}

		return fit;
	}

	private static Requirement mkRequirement(final int id) {
		switch (id) {
			case 1:
				return new Requirement(id, 60, 3, (3 * 10 + 4 * 10 + 2 * 5) / 9);
			case 2:
				return new Requirement(id, 40, 6, (3 * 8 + 4 * 10 + 2 * 6) / 9);
			case 3:
				return new Requirement(id, 40, 2, (3 * 6 + 4 * 4 + 2 * 8) / 9);
			case 4:
				return new Requirement(id, 30, 6, (3 * 5 + 4 * 9 + 2) / 9);
			case 5:
				return new Requirement(id, 20, 4, (3 * 7 + 4 * 7 + 2 * 5) / 9);
			case 6:
				return new Requirement(id, 20, 8, (3 * 8 + 4 * 6 + 2 * 2) / 9);
			case 7:
				return new Requirement(id, 25, 9, (3 * 6 + 4 * 6 + 2 * 4) / 9);
			case 8:
				return new Requirement(id, 70, 7, (3 * 9 + 4 * 8 + 2 * 3) / 9);
			case 9:
				return new Requirement(id, 50, 6, (3 * 6 + 4 * 7 + 2 * 7) / 9);
			case 10:
				return new Requirement(id, 20, 6, (3 * 10 + 4 * 10 + 2 * 7) / 9);
			default:
				return new Requirement(id, 0, 0, 0);//invalid requirement
		}
	}

	@Override
	public double fitness() {
		if (fitnessCache == null) {
			fitnessCache = calculeFitness(chromosome);
		}
		return fitnessCache;
	}

	@Override
	public double forceNewFitness() {
		fitnessCache = calculeFitness(chromosome) * penalty;
		return fitnessCache;
	}

	@Override
	public Chromosome<Integer[]> chromosome() {
		return chromosome;
	}

	@Override
	public void setPenalty(double percent) {
		this.penalty = percent;
	}

	@Override
	public String toString() {
		double price1 = sprint1.parallelStream().mapToDouble(Requirement::getCost).sum();
		double price2 = sprint2.parallelStream().mapToDouble(Requirement::getCost).sum();
		double price3 = sprint3.parallelStream().mapToDouble(Requirement::getCost).sum();
		var fit = fitness();
		return "Releases {\n\n" +
				"\tfit:" + fit + "\n" +
				"\tsprint1=" + sprint1 +
				"\n\tsprint2=" + sprint2 +
				"\n\tsprint3=" + sprint3 +
				"\n\tnotImplemented=" + sprint0 +
				"\n\n\tPrice {\n" +
				"\t\tpriceSprint1=" + price1 +
				"\n\t\tpriceSprint2=" + price2 +
				"\n\t\tpriceSprint3=" + price3 +
				"\n\t}\n}";
	}

	void organizaSprint() {

		sprint1.clear();
		sprint2.clear();
		sprint3.clear();
		sprint0.clear();

		final var value = chromosome.getValue();

		for (int i = 0; i < value.length; i++) {
			if (value[i] == 1) {
				sprint1.add(mkRequirement(i + 1));
				continue;
			}

			if (value[i] == 2) {
				sprint2.add(mkRequirement(i + 1));
				continue;
			}

			if (value[i] == 3) {
				sprint3.add(mkRequirement(i + 1));
				continue;
			}
			sprint0.add(mkRequirement(i + 1));
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
