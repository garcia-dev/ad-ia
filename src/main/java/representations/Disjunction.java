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
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		for (Map.Entry<Variable, String> entry : variables.entrySet()) {
			Variable key = entry.getKey();
			String value = entry.getValue();
			if (value.equals(allocation.get(key))) {
				return true;
			}
		}
		return false;
	}

}
