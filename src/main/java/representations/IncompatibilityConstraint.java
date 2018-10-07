package representations;

import java.util.Map;
import java.util.Set;

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
	public boolean isSatisfiedBy(Map<Variable, String> assignment) {
		boolean compatible = true;

		for (Map.Entry<Variable, String> variable : variables.entrySet()) {
			Variable name = variable.getKey();
			String value = variable.getValue();
			compatible &= value.equals(assignment.get(name));
		}

		return !compatible;
	}

}
