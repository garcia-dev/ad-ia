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
		for (Map.Entry<Variable, String> entry : variables.entrySet()) {
			Variable key = entry.getKey();
			String value = entry.getValue();

			if (!value.equals(allocation.get(key)))
				return false;
		}

		return true;
	}
	
}
