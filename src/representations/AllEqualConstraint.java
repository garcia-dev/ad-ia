package representations;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @version 2018-09-11
 * @author Romain Garcia
 */

public class AllEqualConstraint implements Constraint {
	Set<Variable> variables;

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
			if (value == null)
				value = allocation.get(key);

			if (!allocation.get(key).equals(value))
				return false;
		}

		return true;
	}
}