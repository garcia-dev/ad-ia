package extraction;

import representations.Variable;

import java.util.Map;
import java.util.Set;

public class FrequentItemsetMiner {

	private BooleanDatabase booleanDatabase;

	public FrequentItemsetMiner(BooleanDatabase booleanDatabase) {
		this.booleanDatabase = booleanDatabase;
	}

	public Map<Set<Variable>, Float> frequentItemsets(float minimalSupport) {
		Map<Set<Variable>, Float> result;
		return null;
	}

}