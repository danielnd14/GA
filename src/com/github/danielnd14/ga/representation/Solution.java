package com.github.danielnd14.ga.representation;


public interface Solution extends Comparable<Solution>, Cloneable {
	/**
	 * Método para cálculo de aptidão da representação da solução. Recomenda se criar um cache do valor para
	 * evitar que o cálculo seja feito toda vez em que o método seja invocado.
	 */
	double fitness();


	/**
	 * Método que deve efetuar o mesmo calculo do fitness, porém sem considerar o cache, ideal para mutações.
	 * OBS: deve sobrescrever seu cache com o novo valor para que o mesmo esteja atualizado no método fitnees
	 */
	double forceNewFitness();

	Chromosome chromosome();

	void setPenalty(double percent);

	default int compareTo(final Solution o) {
		return Double.compare(this.fitness(), o.fitness());
	}
}