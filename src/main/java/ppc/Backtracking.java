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

	private HashMap<Variable, String> car = null;
	private int index = 0;

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

	public Map<Variable, String> solution(Map<Variable, String> car) {
		if (index < unusedVariables.size() && index >= 0) {
			String nextValue = getValue(unusedVariables.get(index)); // compute the next value return "" if there is no more value
			if (nextValue.equals("")) {
				car.remove(unusedVariables.get(index));
				this.index = this.index - 1;
				return solution(car); // no other value in the domain so go back
			} else {
				car.put(unusedVariables.get(index), nextValue);
				if (doTest(car)) {
					filterDomain(car, variableDomain);
					this.index = this.index + 1;
					return solution(car); // the test is currently successful so go ahead to add an other variable
				} else {
					return solution(car); // there is other value in the domain so try to test another one
				}
			}

		} else {
			if (index < 0) {
				return null;
			} else {
				if (alreadyMade(car)) {
					car.remove(unusedVariables.get(index - 1));
					this.index = this.index - 1;
					return solution(car);    //no other value in the domain so go back
				} else {
					this.precCar.add(new HashMap<>(car));
					return car;
				}
			}

		}
	}

	public Map<Variable, String> solution() {
		return solution(new HashMap<>());
	}

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

	private boolean doTest(Map<Variable, String> car) {
		for (Constraint c : this.constraints)
			if (car.keySet().containsAll(c.getScope()) && !c.isSatisfiedBy(car))
				return false;

		return true;
	}

	/**
	 * Returns <code>true</code> if the car has already been made.
	 *
	 * @param car the car to be tested
	 * @return <code>true</code> if the car has already been made ;
	 * <code>false</code> otherwise
	 */
	private boolean alreadyMade(Map<Variable, String> car) {
		return precCar.contains(car);
	}

	private boolean filterDomain(Map<Variable, String> car, Map<Variable, Set<String>> domainVariable) {
		ArrayList<Variable> toReorganize = new ArrayList<>();
		boolean hasFiltered = true;
		while (hasFiltered) {
			hasFiltered = false;
			for (Constraint c : this.constraints) {
				hasFiltered |= c.filter(car, domainVariable);
				if (hasFiltered) {
					for (Variable var : c.getScope()) {
						if (domainVariable.get(var).size() == 0) {
							return false;
						}
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
						}
					}
				}
			}
		}

		return true;
	}

}
