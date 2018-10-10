package ppc;

import representations.Variable;
import representations.Constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

public class Backtracking {

	private Set<Constraint> constraints;
	private Set<Variable> variables;
	private List<Variable> unusedVariables;

	public Backtracking(Set<Constraint> constraints, Set<Variable> variables) {
		this.constraints = constraints;
		this.variables = variables;
		this.unusedVariables = new ArrayList<>(variables);
	}

	private String getValue(String current, Variable variable) {
		List<String> domain = new ArrayList<>(variable.getDomain());
		if (domain.indexOf(current) < domain.size()) {
			return domain.get(0);
		} else {
			return domain.get(domain.indexOf(current) + 1);
		}
	}

	private boolean isValid(HashMap<Variable, String> car) {
		for (Constraint constraint : constraints) {
			System.out.println(car);
			System.out.println(constraint.getScope());
			if (car.keySet().containsAll(constraint.getScope())) {
				if (!constraint.isSatisfiedBy(car)) {
					System.out.println("Not valid!");
					return false;
				}
			}
		}

		System.out.println("Valid.");
		return true;
	}
}
