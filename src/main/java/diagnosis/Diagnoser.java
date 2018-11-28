package diagnosis;

import ppc.Backtracking;
import representations.Constraint;
import representations.Variable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Diagnoser {

	private Map<Variable, String> variables;
	private Set<Constraint> constraints;


	/**
	 * Constructs a new {@code Diagnoser}
	 *
	 * @param variables   the variables to diagnose
	 * @param constraints the constraints to diagnose
	 */
	public Diagnoser(Map<Variable, String> variables, Set<Constraint> constraints) {
		this.variables = variables;
		this.constraints = constraints;
	}

	/**
	 * Adds a variable and its value to this {@code Diagnoser}.
	 *
	 * @param variable the variable to be added
	 * @param value    the value to be added
	 */
	public void add(Variable variable, String value) {
		variables.put(variable, value);
	}

	/**
	 * Removes a variable from this {@code Diagnoser}.
	 *
	 * @param variable the variable to be removed
	 */
	public void remove(Variable variable) {
		variables.remove(variable);
	}

	/**
	 * Returns the choices which the domains were reduced to according to their
	 * assignments.
	 *
	 * @param choices the choices which the domains are to be reduced to
	 * @return the choices which the domains were reduced to
	 */
	private Map<Variable, String> reduceDomains(Map<Variable, String> choices) {
		for (Map.Entry<Variable, String> assignedVariable : choices.entrySet()) {
			Variable variable = assignedVariable.getKey();
			String value = assignedVariable.getValue();

			Set<String> toRemove = new HashSet<>();

			// Parse the domain of the current assignedVariable
			for (String domainValue : variable.getDomain()) {
				if (!domainValue.equals(value)) {
					toRemove.add(value);
				}
			}

			variable.getDomain().removeAll(toRemove);
		}

		return choices;
	}

	/**
	 * Returns {@code true} if the couple (variable, value) is an explanation
	 * for explanationVariables.
	 *
	 * @param explanationVariables the variables to be diagnosed
	 * @param variable             the variable to be tested
	 * @param value                the value of the variable to be tested
	 * @return {@code true} if the couple (variable, value) is an explanation
	 * for explanationVariables
	 */
	public boolean isExplanation(Map<Variable, String> explanationVariables, Variable variable, String value) {
		Backtracking backtracking = new Backtracking(variables.keySet(), constraints);


		Map<Variable, String> vars = new HashMap<>(explanationVariables);
		vars.put(variable, value);
		vars = reduceDomains(vars);

		System.out.println("CSP Solution: " + backtracking.solution());

		return variables.entrySet().containsAll(vars.entrySet())
				&& backtracking.solution().isEmpty();
	}

	/**
	 * Returns an explanation for the couple (variable, value).
	 *
	 * @param variable the variable to be tested
	 * @param value    the value of the variable to be tested
	 * @return an explanation for the couple (variable, value)
	 */
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

	/**
	 * Returns these variables.
	 *
	 * @return these variables
	 */
	public Map<Variable, String> getVariables() {
		return variables;
	}
}
