package representations;

import java.util.Map;
import java.util.Set;

public class Disjunction implements Constraint {

	private Map<Variable, String> variables;

	public Disjunction(Map<Variable, String> variables) {
		this.variables = variables;
	}

	@Override
	public Set<Variable> getScope() {
		return variables.keySet();
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> assignment) {
		for (Map.Entry<Variable, String> variable : variables.entrySet()) {
			Variable name = variable.getKey();
			String value = variable.getValue();
			if (value.equals(assignment.get(name))) {
				return true;
			}
		}
		return false;
	}

}
