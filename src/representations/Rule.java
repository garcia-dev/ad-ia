package representations;

import java.util.Map;
import java.util.Set;

/**
 * @version 2018-09-11
 * @author Romain Garcia
 */

class Rule implements Constraint {
	private Map<Variable, String> premise;
	private Map<Variable, String> conclusion;

	Rule(Map<Variable, String> premise, Map<Variable, String> conclusion) {
		this.premise = premise;
		this.conclusion = conclusion;
	}

	@Override
	public Set<Variable> getScope() {
		return null;
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		for (Map.Entry<Variable, String> entry : premise.entrySet()) {
			Variable key = entry.getKey();
			String value = entry.getValue();

			if (!allocation.get(key).equals(value)) return false;
		}

		for (Map.Entry<Variable, String> entry : conclusion.entrySet()) {
			Variable key = entry.getKey();
			String value = entry.getValue();

			if (!allocation.get(key).equals(value)) return false;
		}

		return true;
	}
}
