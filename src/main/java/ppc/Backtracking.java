package ppc;

import representations.Constraint;
import representations.Variable;

import java.util.*;

public class Backtracking {

	private Set<Constraint> constraints;
	private Set<Variable> variables;

	private List<Variable> unusedVariables;

	private Map<Variable, Set<String>> variableDomain;
	private List<HashMap<Variable, String>> precCar;

	private HashMap<Variable, String> assignment = null;
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
	private HashMap<Variable, String> solution(HashMap<Variable, String> assignment) {
		if (index < unusedVariables.size() && index >= 0) {
			String nextValue = getValue(unusedVariables.get(index)); // compute the next value return "" if there is no more value
			if (nextValue.equals("")) {
				index = index - 1;
				return solution(assignment); // no other value in the domain so go back
			} else {
				assignment.put(unusedVariables.get(index), nextValue);
				if (doTest(assignment)) {
					if (filterDomain(assignment, variableDomain)) {
						index++; // the test is currently successful like the filtering so go ahead to add an other variable
					} else {
						index--; // the filtering isn't good so go back
					}
					return solution(assignment);
				} else {
					return solution(assignment); // there is other value in the domain so try to test another one
				}
			}

		} else {
			if (index < 0) {
				return null; // there is no other solution so we return null
			}
			if (alreadyGave(assignment)) {
				index = index - 1;
				return solution(assignment); // assignment has been already made so we go back to try to make another one
			}

			precCar.add(new HashMap<>(assignment)); //the creation of the assignment is currently sucesfull so we add it to the list of assignment made by the ppc and we return it
			return assignment;

		}
	}


	/**
	 * Returns the next assignment which is a solution of the backtrack
	 *
	 * @return the next solution or {@code null} if there is no other solution.
	 */
	public HashMap<Variable, String> solution() {
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
	 * Returns {@code true} if the given assignment respects the given
	 * constraint.
	 *
	 * @param assignment the assignment to be tested
	 * @return {@code true} if the assignment respects the given constraint ;
	 * {@code false} otherwise
	 */
	private boolean doTest(Map<Variable, String> assignment) {
		for (Constraint c : this.constraints) {
			if (assignment.keySet().containsAll(c.getScope()) && !c.isSatisfiedBy(assignment)) {
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
	private boolean alreadyGave(HashMap<Variable, String> assignment) {
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
	 *
	 * @param car            the assignment in construction
	 * @param variableDomain the domain of each variable to be set on the
	 *                       assignment
	 * @return {@code true} if the filtering has been sucessful ; {@code false}
	 * otherwise
	 */
	private boolean filterDomain(Map<Variable, String> car, Map<Variable, Set<String>> variableDomain) {
		ArrayList<Variable> toReorganize = new ArrayList<>();
		boolean hasFiltered = true;
		while (hasFiltered) {
			hasFiltered = false;
			for (Constraint c : this.constraints) {
				hasFiltered |= c.filter(car, variableDomain);
				if (hasFiltered) {
					for (representations.Variable var : c.getScope()) {
						if (variableDomain.get(var).size() == 0) {
							return false;
						}

						/*
						 * The code below doesn't work as expected because the whole
						 * system of the backtracking is based on an index
						 * instead of set and only set. So when we put a
						 * specific value to a variable, we need to reorganize
						 * the index and the list ; this second part of
						 * reorganization doesn't work as expected and it didn't
						 * give a full solution but just a partial set of a
						 * solution.
						 */
						//if (variableDomain.get(var).size() == 1) {
						//	assignment.put(var, getValue(var));
						//	if (!doTest(assignment)) {
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
