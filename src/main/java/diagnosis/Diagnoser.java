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
	 * Constructs a new {@code Diagnoser}.
	 *
	 * @param variables   the variables useful for the diagnosis
	 * @param constraints the constraints useful for the diagnosis
	 */
	public Diagnoser(Map<Variable, String> variables, Set<Constraint> constraints) {
		this.variables = variables;
		this.constraints = constraints;
	}

	/**
	 * Adds a variable and its value (assignment) to the variables of this
	 * {@code Diagnoser}.
	 *
	 * @param variable the variable to be added
	 * @param value    the value of the variable to be added
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
	 * Returns the choices which the domains were reduced to, according to their
	 * assignments.
	 *
	 * @param choices the choices which the domains are to be reduced to
	 * @return the choices which the domains were reduced to
	 */
	private Map<Variable, String> reduceDomains(Map<Variable, String> choices) {
		for (Map.Entry<Variable, String> choice : choices.entrySet()) {
			Variable variable = choice.getKey();
			String value = choice.getValue();

			Set<String> valuesToRemove = new HashSet<>();

			// Parse the domain of the current choice
			for (String domainValue : variable.getDomain()) {
				if (!domainValue.equals(value)) {
					valuesToRemove.add(value);
				}
			}

			variable.getDomain().removeAll(valuesToRemove);
		}

		return choices;
	}

	/**
	 * Returns {@code true} if the couple (variable, value) is an explanation
	 * for the given choices.
	 *
	 * @param choices  the variables to be diagnosed
	 * @param variable the variable to be tested
	 * @param value    the value of the variable to be tested
	 * @return {@code true} if the couple (variable, value) is an explanation
	 * for the given choices
	 */
	public boolean isExplanation(Map<Variable, String> choices, Variable variable, String value) {
		Backtracking backtracking = new Backtracking(variables.keySet(), constraints);


		Map<Variable, String> vars = new HashMap<>(choices);
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

		for (Map.Entry<Variable, String> choice : choicesToExplore.entrySet()) {
			Variable exploredVariable = choice.getKey();

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
