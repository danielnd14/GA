package com.github.danielnd14.ga.representation;

@FunctionalInterface
public interface StopCriterion {


	/**
	 * @param population this interface is used on the Genetic Algorithm by default. With this you can create
	 *                   a custom stop criterion for your representation's problem
	 * @return false when you want to stop the algorithm
	 */
	boolean continues(final AbstractPopulation population);
}