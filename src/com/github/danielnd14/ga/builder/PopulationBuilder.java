package com.github.danielnd14.ga.builder;

import com.github.danielnd14.ga.operations.MergeOperation;
import com.github.danielnd14.ga.operations.SelectionOperation;
import com.github.danielnd14.ga.representation.AbstractPopulation;

/**
 * Sugestão usar uma implementação do builder para criar alguma instancia de população, porém nada impede o
 * desenvolvedor de criar a população direto, sem o builder.
 */
public interface PopulationBuilder {

	PopulationBuilder withRouleteSelection();

	PopulationBuilder withBinaryTournamentSelection();

	PopulationBuilder withCustomSelection(final SelectionOperation strategy);

	PopulationBuilder withRankMergeStratey();

	PopulationBuilder withDescMergeStratey();

	PopulationBuilder withCustomMergeStrategy(final MergeOperation mergeStrategy);

	PopulationBuilder withMaxGenerations(final int maxGenerations);

	PopulationBuilder withMutationRate(final double mutationRate);

	PopulationBuilder withElitismNumber(final int number);

	PopulationBuilder withPopulationSize(final int size);

	PopulationBuilder withCrosOverRate(final double crosOverRate);

	AbstractPopulation build();

}
