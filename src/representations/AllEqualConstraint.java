package representations;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class AllEqualConstraint implements Constraint {
	private Set<Variable> variables;

	public AllEqualConstraint(Set<Variable> variables) {
		this.variables = variables;
	}

	@Override
	public Set<Variable> getScope() {
		return variables;
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		String value = null;

		for (Variable key : variables) {
			if (value == null) {
				value = allocation.get(key);
				continue;
			}

			if (!allocation.get(key).equals(value))
				return false;
		}

		return true;
	}

	@Override
	public boolean filter(Map<Variable, String> car, Map<Variable, Set<String>> variableDomain) {
		Variable varPrev = null;
		boolean hasFiltered = false;
		for (Variable var : this.variables) {
			if (car.containsKey(var)) {
				varPrev = var;
				break;
			}
		}
		if (varPrev != null) {
			HashSet<String> valueDomain = new HashSet<>();
			valueDomain.add(car.get(varPrev));
			for (Variable var : this.variables) {
				if (!car.containsKey(var) && !(variableDomain.get(var).size() == 1)) {
					variableDomain.put(var, valueDomain);
					hasFiltered = true;
				}
			}
		}
		return hasFiltered;
	}
}
