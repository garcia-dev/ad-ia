package diagnosis;

import ppc.Backtracking;
import representations.Constraint;
import representations.Variable;

import java.util.Map;
import java.util.Set;

public class Diagnoser {

	private Map<Variable, String> variables;
	private Set<Constraint> constraints;

	public Diagnoser(Map<Variable, String> variables, Set<Constraint> constraints) {
		this.variables = variables;
		this.constraints = constraints;
	}

	public void add(Variable variable, String value) {
		variables.put(variable, value);
	}

	public boolean isExplanation(Set<Variable> variables, Variable variable, String value) {
		Backtracking backtracking = new Backtracking(variables, constraints);
		return constraints.containsAll(variables)
				&& !(backtracking.solution(this.variables).size() > 0);
	}

}
