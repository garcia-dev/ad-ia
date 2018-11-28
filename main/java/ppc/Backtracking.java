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

	private HashMap<representations.Variable, String> car = null;
	private int index = 0;

	/**
	 * this is the constructor of the class backtracking, it take the set of variable and constraint for build a solution,a car
	 *
	 * @param constraints a set of constraints who need to be respect to have a good solution
	 * @param variables a set with all the variable who need to be used.
	 */
	public Backtracking(Set<Constraint> constraints, Set<Variable> variables) {
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
	 *this function is the heart of the backtrack it is him who make the solution
	 *
	 * @param car the car to be build (empty when we make the first solution and full when we want another solution
	 * @return a car completely build or null if there is no others solution
	 */
	private HashMap<Variable, String> solution(HashMap<Variable, String> car) {
		if (index < unusedVariables.size() && index >= 0) {
			String nextValue = getValue(unusedVariables.get(index)); // compute the next value return "" if there is no more value
			if (nextValue.equals("")) {
				this.index = this.index - 1;
				return solution(car); // no other value in the domain so go back
			} else {
				car.put(unusedVariables.get(index), nextValue);
				if (doTest(car)) {
					filterDomain(car, variableDomain);
                    this.index++; //the test is currently successful like the filtering so go ahead to add an other variable
					return solution(car);
				} else {
					return solution(car); // there is other value in the domain so try to test another one
				}
			}

		} else {
			if (index < 0)
				return null; //there is no other solution so we return null

			if (alreadyGive(car)) {
				this.index = this.index - 1;
				return solution(car);    //car has been already made so we go back to try to make another one
				}
				this.precCar.add(new HashMap<>(car)); //the creation of the car is currently sucesfull so we add it to the list of car made by the ppc and we return it
				return car;

		}
	}



	/**
	 * Give the next car who is a solution of the backtrack
	 *
	 * @return the next solution or <code>null</code> if there is no other solution.
	 */
	public HashMap<Variable, String> solution() {
		if(car==null) {
			car=solution(new HashMap<>());
			return car;
		}
		else{
			car=solution(this.car);
			return car;
		}
	}

	/**
	 * This function give the next value of a variable to be used in the car
	 *
	 * @param variable the variable from wich we want the next value of his domain
	 * @return return an empty string "" if there is no other value in the domain or the next value to be used
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
	 * This function test if the given car respect the given constraint.
	 * @param car represent the car in construction to be tested.
	 * @return <code>true</code> if the car in construction respect the given constraint, <code>false</code> otherwise.
	 */
	private boolean doTest(Map<representations.Variable, String> car) {
		for (Constraint c : this.constraints)
			if (car.keySet().containsAll(c.getScope()) && !c.isSatisfiedBy(car))
				return false;

		return true;
	}

	/**
	 * Returns <code>true</code> if the car has already been made.
	 *
	 * @param car the car to be tested
	 * @return <code>true</code> if the car has already been made ; false otherwise
	 */
	private boolean alreadyGive(HashMap<Variable, String> car) {
		return this.precCar.contains(car);
	}

	/**
	 * This function filter the domain of each variable.
	 * For example if we got the constraint that all parts of the car must be of the same color,
	 * if one parts have been paints in red this function will reduce the domain off all others variable
	 * to one, wich contains the value "red". This will reduce the cost of the backtrack because he didn't need
	 * to make recurtion into all of the value for each variable to find a good solution
	 * there with the basic execution it take only 3 recurtion to find the first solution
	 * (1 for the color,1 for the sono and another 1 for the openning roof).
	 *
	 * @param car the car in construction
	 * @param domainVariable this variable contains the domain of each variable to be set on the car
	 * @return a boolean if filtering have been sucessful or not (ie: for all variable there domain contains at list element)
	 */
	private boolean filterDomain(Map<Variable, String> car, Map<Variable, Set<String>> domainVariable) {
		ArrayList<Variable> toReorganize = new ArrayList<>();
		boolean hasFiltered = true;
		while (hasFiltered) {
			hasFiltered = false;
			for (Constraint c : this.constraints) {
				hasFiltered |= c.filter(car, domainVariable);
				if (hasFiltered) {
					for (representations.Variable var : c.getScope()) {
						if (domainVariable.get(var).size() == 0) {
							return false;
						}
						/*
						this doesn't work as expected because the whole system of bactrack is based on index instead of set and only
						set. So when we put a specific value to a variable we need to reorganize the index and the list
						this second part of reorganizing doesn't work as expected and it didn't give full solution
						and just a partial set of solution.

						if (domainVariable.get(var).size() == 1) {
							car.put(var, getValue(var));
							if (!doTest(car)) {
								domainVariable.put(var, new HashSet<>(var.getDomain()));
								car.remove(var);
								return false;
							} else {
								unusedVariables.remove(var);
								unusedVariables.add(1, var);
								index++;
								System.out.println(unusedVariables);
							}
						}*/
					}
				}
			}
		}
		return true;
	}

}
