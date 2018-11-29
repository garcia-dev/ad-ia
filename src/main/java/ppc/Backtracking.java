package ppc;

import representations.Constraint;
import representations.Variable;

import java.util.*;

public class Backtracking {

	private Set<Constraint> constraints;
	private Set<Variable> variables;

	private List<Variable> unusedVariables;

	private Map<Variable, Set<String>> variableDomain;
	private List<Map<Variable, String>> precCar;

	private Map<Variable, String> assignment = null;
	private int index = 0;

	/**
	 * Constructs a new {@code Backtracking}, taking the set of variable and
	 * constraint to build a solution (an assignment)
	 *
	 * @param constraints the set of constraints to be satisfied to build a
	 *                    solution
	 * @param variables   the set of variables to be used
	 */
	public Backtracking(Set<Variable> variables, Set<Constraint> constraints) {
		this.constraints = constraints;
		this.variables = variables;

		this.unusedVariables = new ArrayList<>(variables);
		this.variableDomain = new HashMap<>();

		this.precCar = new ArrayList<>();

		for (Variable variable : this.variables) {
			this.variableDomain.put(variable, new HashSet<>(variable.getDomain()));
		}
	}

	/**
	 * Returns a backtrack solution.
	 *
	 * @param assignment the assignment to be built (empty when we make the
	 *                   first solution and full when we want another solution)
	 * @return an assignment completely built or null if there is no others
	 * solution
	 */
	public Map<Variable, String> solution(Map<Variable, String> assignment) {
		if (index < unusedVariables.size() && index >= 0) {

			// Compute the next value, returns "" if there is no more value
			String nextValue = getValue(unusedVariables.get(index));

			if (nextValue.equals("")) {
				index = index - 1;

				// There is no more value in the domain so go back
				return solution(assignment);
			} else {
				assignment.put(unusedVariables.get(index), nextValue);
				if (verifiesConstraints(assignment)) {
					filterDomain(assignment, variableDomain);
					index++;
					return solution(assignment);
				} else {

					// There are other values in the domain so try to test another one
					return solution(assignment);
				}
			}

		} else {
			if (index < 0)
				return null;
			else {
				if (alreadyMade(assignment)) {
					assignment.remove(unusedVariables.get(index - 1));
					this.index = this.index - 1;
					return solution(assignment);    //no other value in the domain so go back
				} else {
					this.precCar.add(new HashMap<>(assignment));
					return assignment;
				}
			}
		}
	}


	/**
	 * Returns the next assignment which is a solution of the backtrack
	 *
	 * @return the next solution or {@code null} if there is no other solution.
	 */
	public Map<Variable, String> solution() {
		if (assignment == null) {
			assignment = solution(new HashMap<>());
			return assignment;
		} else {
			assignment = solution(this.assignment);
			return assignment;
		}
	}

	/**
	 * Returns the next value of a variable to be used in the assignment
	 *
	 * @param variable the variable from which we want the next value of his
	 *                 domain
	 * @return an empty string if there is no other value in the domain ; the
	 * next value to be used otherwise
	 */
	private String getValue(Variable variable) {
		Set<String> possibleValue = variableDomain.get(variable);
		if (!possibleValue.iterator().hasNext()) {
			for (int i = this.index; i < this.unusedVariables.size(); i++) {
				Variable var = this.unusedVariables.get(i);
				variableDomain.put(var, new HashSet<>(var.getDomain()));
			}
			return "";
		} else {
			Iterator<String> i = possibleValue.iterator();
			String value = i.next();
			i.remove();
			this.variableDomain.put(variable, possibleValue);
			return value;
		}
	}

	/**
	 * Returns {@code true} if the given assignment satisfies all the
	 * constraints it's involved in.
	 *
	 * @param assignment the assignment to be tested
	 * @return {@code true} if the given assignment satisfies all the
	 * constraints it's involved in ; {@code false} otherwise
	 */
	private boolean verifiesConstraints(Map<Variable, String> assignment) {
		for (Constraint constraint : constraints) {
			if (assignment.keySet().containsAll(constraint.getScope())
					&& !constraint.isSatisfiedBy(assignment)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns {@code true} if the assignment has already been made.
	 *
	 * @param assignment the assignment to be tested
	 * @return {@code true} if the assignment has already been made ; false
	 * otherwise
	 */
	private boolean alreadyMade(Map<Variable, String> assignment) {
		return this.precCar.contains(assignment);
	}

	/**
	 * Filters the domain of each variable.
	 * <p>
	 * For example, if we got a constraint telling that all parts of the
	 * assignment must be of the same color, if one parts has been painted in
	 * red this function will reduce the domain of all other variables to one,
	 * which contains the value "red". This will reduce the cost of the
	 * backtrack because it didn't need to make recursion into all of the values
	 * for each variable to find a good solution there with the basic execution
	 * it takes only 3 recursions to find the first solution (1 for the color, 1
	 * for the sono and another 1 for the opening roof).
	 * </p>
	 *
	 * @param assignment     the assignment in construction
	 * @param variableDomain the domain of each variable to be set on the
	 *                       assignment
	 * @return {@code true} if the filtering has been sucessful ; {@code false}
	 * otherwise
	 */
	private boolean filterDomain(Map<Variable, String> assignment, Map<Variable, Set<String>> variableDomain) {
		ArrayList<Variable> toReorganize = new ArrayList<>();
		boolean hasFiltered = true;
		while (hasFiltered) {
			hasFiltered = false;
			for (Constraint constraint : constraints) {
				hasFiltered |= constraint.filter(assignment, variableDomain);
				if (hasFiltered) {
					for (representations.Variable var : constraint.getScope()) {
						if (variableDomain.get(var).size() == 0) {
							return false;
						}

						/*
						 * The code below doesn't work as expected because the
						 * whole system of the backtracking is based on an index
						 * instead of a set and only a set. So when we put a
						 * specific value to a variable, we need to reorganize
						 * the index and the list ; this second part of
						 * reorganization doesn't work as expected and it didn't
						 * give a full solution but just a partial set of
						 * solutions.
						 */
						//if (variableDomain.get(var).size() == 1) {
						//	assignment.put(var, getValue(var));
						//	if (!verifiesConstraints(assignment)) {
						//		variableDomain.put(var, new HashSet<>(var.getDomain()));
						//		assignment.remove(var);
						//		return false;
						//	} else {
						//		unusedVariables.remove(var);
						//		unusedVariables.add(1, var);
						//		index++;
						//		System.out.println(unusedVariables);
						//	}
						//}
					}
				}
			}
		}
		return true;
	}

}
