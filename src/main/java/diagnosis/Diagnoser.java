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
		this.variables.put(variable, value);
	}

	public void remove(Variable variable) {
		this.variables.remove(variable);
	}

	public boolean isExplanation(Map<Variable, String> variables, Variable variable, String value) {
		Backtracking backtracking = new Backtracking(variables.keySet(), this.constraints);

		Map<Variable, String> vars = new HashMap<>(variables);
		vars.put(variable, value);

		return this.variables.entrySet().containsAll(variables.entrySet())
				&& !(backtracking.solution(vars).size() > 0);
	}

	public Map<Variable, String> explanation(Variable variable, String value) {
		Map<Variable, String> choicesToExplore = this.variables;
		Map<Variable, String> explanation = new HashMap<>(this.variables);

		for (Map.Entry<Variable, String> entry : choicesToExplore.entrySet()) {
			Variable var = entry.getKey();
			String val = entry.getValue();

			Map<Variable, String> explanationBuffer = new HashMap<>(explanation);
			explanationBuffer.remove(var);

			if (isExplanation(explanationBuffer, var, val)) {
				explanation = explanationBuffer;
			}
		}

		return explanation;
	}

}
