package com.github.danielnd14.ga.helpers;

public final class NumberHelper {
	public static double toDouble(final Integer[] value) {
		double num = 0.;
		for (int i = 0; i < value.length; i++) {
			num += value[i] * Math.pow(2, value.length - i - 1);
		}
		return num;
	}
}
