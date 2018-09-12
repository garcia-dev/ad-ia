package representations;

import java.util.Map;
import java.util.Set;

/**
 * @version 2018-09-11
 * @author Romain Garcia
 */

public class AllEqualConstraint implements Constraint {
	private Map<Variable, String> variables;

	public AllEqualConstraint(Map<Variable, String> variables) {
		this.variables = variables;
	}

	@Override
	public Set<Variable> getScope() {
		return variables.keySet();
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> map) {
		return false;
	}
}