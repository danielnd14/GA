package com.github.danielnd14.releasesproblem;

public class Requirement {
	private final int id;
	private final double cost;
	private final double risk;
	private final double avgClient;
	private Double importancia;

	Requirement(final int id,
				final double cost,
				final double risk,
				final double avgClient) {
		this.id = id;
		this.cost = cost;
		this.risk = risk;
		this.avgClient = avgClient;
	}

	double getCost() {
		return cost;
	}

	double getImportancia() {
		if (importancia == null) {
			importancia = cost * (risk + avgClient);
		}
		return importancia;
	}

	@Override
	public String toString() {
		return "Requirement{" + id + "}";
	}
}