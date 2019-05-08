package com.github.danielnd14.ga.representation;

import com.github.danielnd14.ga.operations.MergeOperation;
import com.github.danielnd14.ga.operations.SelectionOperation;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractPopulation {
	protected static final Random r = new Random();

	public List<Solution> getOffSpring() {
		return offSpring;
	}

	protected final List<Solution> members;
	protected final List<Solution> offSpring;
	private final MergeOperation mergeStrategy;
	protected final SelectionOperation selectionStrategy;
	public final int POPULATION_SIZE;
	public final int ELITISM;
	final int MAX_GENERATIONS;
	public final double CROSSOVER_RATE;
	protected final double MUTATION_RATE;

	public AbstractPopulation(final MergeOperation mergeStrategy,
							  final SelectionOperation selectionStrategy,
							  final int populationSize,
							  final int elitism,
							  final int maxGenerations,
							  final double crossoverRate,
							  final double mutationRate) {
		this.mergeStrategy = mergeStrategy;
		this.selectionStrategy = selectionStrategy;
		this.POPULATION_SIZE = populationSize;
		this.ELITISM = elitism;
		this.MAX_GENERATIONS = maxGenerations;
		this.CROSSOVER_RATE = crossoverRate;
		this.MUTATION_RATE = mutationRate;
		this.members = new Vector<>();
		this.offSpring = new Vector<>();
		initPopulation();

	}


	private void initPopulation() {
		Set<Solution> temp = new HashSet<>();
		while (temp.size() < this.POPULATION_SIZE) {
			temp.add(getRandomSolution());
		}
		this.members.addAll(temp);
	}

	public void sortMembers() {
		this.members.sort(Solution::compareTo);
	}

	protected abstract void crossOver();

	protected abstract void doMutation();

	void merge() {
		mergeStrategy.merge(this);
	}

	protected abstract Solution getRandomSolution();

	protected abstract boolean isValid(final Solution solution);

	Solution getBestSolution() {
		//this.members.removeIf(solution -> !this.isValid(solution));
		return this.members.parallelStream().max((Solution::compareTo)).orElse(null);
	}

	public Solution getWorstSolution() {
		return this.members.parallelStream().min((Solution::compareTo)).orElse(null);
	}

	public List<Solution> getNBestSolutions(final int n) {
		final var size = members.size();
		return members.parallelStream()
				.sorted()
				.collect(Collectors.toList())
				.subList(size - n - 1, size - 1);
	}

	public List<Solution> getNWorstSolutions(final int n) {
		return members.parallelStream()
				.sorted()
				.collect(Collectors.toList())
				.subList(0, n);
	}

	public Solution getRandomMember() {
		return members.get(r.nextInt(members.size()));
	}

	public double getSumOfFitness() {
		return this.members.parallelStream().mapToDouble(Solution::fitness).sum();
	}

	public List<Solution> getMembers() {
		return members;
	}

	public abstract void addMember(Solution solution);
}