package representations;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
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
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		Set<String> values = new HashSet<>(allocation.values());
		return values.size() == 1;
	}

}