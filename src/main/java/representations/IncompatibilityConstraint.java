package main.java.representations;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-10-02
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


	@Override
	public boolean filter(Map<Variable, String> car, Map<Variable, Set<String>> variableDomain) {
		int compt = 0;
		Iterator<Variable> iteCar = car.keySet().iterator();
		Iterator<Variable> iteSco = getScope().iterator();
		while (iteCar.hasNext() && iteSco.hasNext()) {
			if (iteCar.next() == iteSco.next()) {
				compt++;
			}
		}
		if (compt == getScope().size() - 1) {
			Variable value2Changed = null;
			for (Variable var : getScope())
				if (car.containsKey(var)) {
					if (!car.get(var).equals(this.variables.get(var)))
						return false;
				} else
					value2Changed = var;
				
			if (value2Changed != null) {
				Set<String> domain = new HashSet<>(value2Changed.getDomain());
				domain.remove(this.variables.get(value2Changed));
				variableDomain.put(value2Changed, domain);
				return true;
			}
		}
		return false;
	}
}
