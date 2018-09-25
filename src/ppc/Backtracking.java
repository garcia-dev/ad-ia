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

	public HashMap<Variable, String> solution(HashMap<Variable, String> car) {
		if (!unusedVariables.isEmpty()) {
			if (car != null) {

				/* Assign the next value (use unusedVariables) */
				car.put(unusedVariables.get(0), getValue(car.get(unusedVariables.get(0)), unusedVariables.get(0)));
				if (doTest(car)) {
					this.unusedVariables.remove(0);
					return solution(car);
				} else {
					return null;
				}
			} else {

				/* Go back and use all variables (use variables) */
				this.unusedVariables = new ArrayList(variables);
				car = new HashMap();
				car.put(unusedVariables.get(0), getValue(car.get(unusedVariables.get(0)), unusedVariables.get(0)));
				if (doTest(car)) {
					this.unusedVariables.remove(0);
					return solution(car);
				} else {
					return null;
				}
			}
		} else {
			return car;
		}

	}

	private String getValue(String current, Variable variable) {
		List<String> domain = new ArrayList(variable.getScope());
		if (domain.indexOf(current) < domain.size()) {
			return domain.get(0);
		} else {
			return domain.get(domain.indexOf(current) + 1);
		}
	}

	private boolean doTest(HashMap<Variable, String> car) {
		List<Variable> variables = new ArrayList(car.keySet());
		for (Constraint c : this.constraints) {
			if (car != null) {
				System.out.println(car);
				System.out.println(c.getScope());
				if (car.keySet().containsAll(c.getScope())) {
					if (!c.isSatisfiedBy(car)) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
