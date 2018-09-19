package representations;

import java.util.Map;
import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class IncompatibilityConstraint implements Constraint {

	private Set<Variable> scope;

	public IncompatibilityConstraint(Set<Variable> scope) {
		this.scope = scope;
	}

	@Override
	public Set<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		String value = "";

		for (Variable variable : scope) {
			if (value.equals("")) {
				value = allocation.get(variable);
				continue;
			}

			if (allocation.get(variable).equals(value))
				return false;
		}

		return true;
	}
	
}
