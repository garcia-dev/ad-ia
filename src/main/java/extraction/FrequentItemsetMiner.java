package main.java.extraction;

import main.java.representations.Variable;

import java.util.Map;
import java.util.Set;

class FrequentItemsetMiner {
	private BooleanDatabase booleanDatabase;

	public FrequentItemsetMiner(BooleanDatabase booleanDatabase) {
		this.booleanDatabase = booleanDatabase;
	}

	public Map<Set<Variable>, Float> frequentItemsets(Float minimalSupport) {
		return null;
	}
}
