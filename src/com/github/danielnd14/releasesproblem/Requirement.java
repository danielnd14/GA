package com.github.danielnd14.releasesproblem;

public class Requirement {
	private final int id;
	private final double cost;
	private final double risk;
	private Double importancia;

	Requirement(final int id,
				final double cost,
				final double risk,
				final double avgClient) {
		this.id = id;
		this.cost = cost;
		this.risk = risk;
		this.importancia = avgClient;
	}

	double getCost() {
		return cost;
	}

	double getRisk() {
		return risk;
	}

	double getImportancia() {
		return importancia;
	}

	@Override
	public String toString() {
		return "Requirement{" + id + "}";
	}
}