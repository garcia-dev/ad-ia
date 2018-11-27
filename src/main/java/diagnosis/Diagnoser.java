package diagnosis;

import ppc.Backtracking;
import representations.Constraint;
import representations.Variable;

import java.util.HashMap;
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

	public void remove(Variable variable) {
		variables.remove(variable);
	}

	public boolean isExplanation(Map<Variable, String> explanationVariables, Variable variable, String value) {
		Backtracking backtracking = new Backtracking(variables.keySet(), constraints);

		Map<Variable, String> vars = new HashMap<>(explanationVariables);
		vars.put(variable, value);

		System.out.println("CSP Solution: " + backtracking.solution());

		return variables.entrySet().containsAll(vars.entrySet())
				&& backtracking.solution().isEmpty();
	}

	public Map<Variable, String> explanation(Variable variable, String value) {
		Map<Variable, String> choicesToExplore = variables;
		Map<Variable, String> explanation = new HashMap<>(variables);

		for (Map.Entry<Variable, String> entry : choicesToExplore.entrySet()) {
			Variable exploredVariable = entry.getKey();

			Map<Variable, String> explanationBuffer = new HashMap<>(explanation);
			explanationBuffer.remove(exploredVariable);


			if (isExplanation(explanationBuffer, variable, value)) {
				explanation = explanationBuffer;
			}
		}

		return explanation;
	}

	public Map<Variable, String> getVariables() {
		return variables;
	}
}
