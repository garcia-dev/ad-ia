package representations;

import java.util.Map;
import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class IncompatibilityConstraint implements Constraint {
	private Map<Variable, String> variables;

	public IncompatibilityConstraint(Map<Variable, String> variables) {
		this.variables = variables;
	}

	@Override
	public Set<Variable> getScope() {
		return variables.keySet();
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		boolean test = true;

		for (Variable var : variables.keySet())
			test &= variables.get(var).equals(allocation.get(var));

		return !test;
	}
}
