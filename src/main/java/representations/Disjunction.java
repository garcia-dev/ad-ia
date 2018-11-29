package representations;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

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
		for (Map.Entry<Variable, String> entry : variables.entrySet()) {
			if (entry.getValue().equals(assignment.get(entry.getKey()))) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean filter(Map<Variable, String> car, Map<Variable, Set<String>> variableDomain) {
		int i = 0;
		Iterator<Variable> iteCar = car.keySet().iterator();

		for (Variable var : getScope()) {
			for (int j = 0; j < car.size(); j++)
				if (iteCar.next() == var)
					i++;

			iteCar = car.keySet().iterator();
		}

		if (i == getScope().size() - 1) {
			boolean has2filter = true;
			Variable value2Changed = null;

			for (Variable var : getScope())
				if (car.containsKey(var))
					has2filter = !car.get(var).equals(this.variables.get(var));
				else
					value2Changed = var;

			if (has2filter) {
				Set<String> x = new HashSet<>();
				x.add(this.variables.get(value2Changed));
				variableDomain.put(value2Changed, x);
				return true;
			}
		}
		return false;
	}
}
