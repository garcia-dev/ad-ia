package ppc;

import representations.Constraint;
import representations.Variable;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class BackTracking {
	private Set<Constraint> allConstraint;
	private Set<Variable> allVariable;

	private ArrayList<Variable> notUsed;

	private Map<Variable, Set<String>> variableDomain;
	private List<HashMap<Variable, String>> precCar;

	private HashMap<Variable, String> car = null;
	private int index = 0;

	private LinkedHashMap<Variable, Integer> variableIntegerLinkedHashMap = null;

	public BackTracking(Set<Constraint> allConstraint, Set<Variable> allVariable) {
		this.allConstraint = allConstraint;
		this.allVariable = allVariable;

		this.notUsed = new ArrayList<>(allVariable);
		this.variableDomain = new HashMap<>();

		this.precCar = new ArrayList<>();

		for (Variable var : this.allVariable) {
			this.variableDomain.put(var, new HashSet<>(var.getDomain()));
		}
	}

	private void createConstraintVariableMap() {
		HashMap<Variable, Integer> variableIntegerHashMap = new HashMap<>();

		allVariable.forEach(variable -> variableIntegerHashMap.put(variable, 0));

		allConstraint.forEach(constraint -> constraint.getScope().forEach(variable -> {
			int count = variableIntegerHashMap.get(variable);
			variableIntegerHashMap.put(variable, count + 1);
		}));

		variableIntegerLinkedHashMap = variableIntegerHashMap.entrySet().stream().sorted(comparingByValue()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
	}

	public Variable getLessConstraintVariable() {
		if (variableIntegerLinkedHashMap == null)
			createConstraintVariableMap();

		return variableIntegerLinkedHashMap.entrySet().iterator().next().getKey();
	}

	public Variable getMostConstraintVariable() {
		if (variableIntegerLinkedHashMap == null)
			createConstraintVariableMap();

		ArrayList<Variable> variables = new ArrayList<>(variableIntegerLinkedHashMap.keySet());

		return variables.get(variables.size() - 1);
	}

	private HashMap<Variable, String> getNextSolution(HashMap<Variable, String> car) {
		if (index < notUsed.size() && index >= 0) {
			String nextValue = getValue(notUsed.get(index));  //compute the next value return "" if there is no more value
			if (nextValue.equals("")) {
				car.remove(notUsed.get(index));
				this.index = this.index - 1;
				return getNextSolution(car);    //no other value in the domain so go back
			} else {
				car.put(notUsed.get(index), nextValue);
				if (doTest(car)) {
					filterDomain(car, variableDomain);
					this.index = this.index + 1;

					return getNextSolution(car); // the test is currently succesful so go ahead to add an other variable
				} else {
					return getNextSolution(car); //there is other value in the domain so try to test another one
				}
			}

		} else {
			if (index < 0) {
				return null;
			} else {
				if (alreadyGive(car)) {
					car.remove(notUsed.get(index - 1));
					this.index = this.index - 1;
					return getNextSolution(car);    //no other value in the domain so go back
				} else {
					this.precCar.add(new HashMap<>(car));
					return car;
				}
			}

		}
	}

	public HashMap<Variable, String> solution() {
		if (this.car == null) {
			this.car = getNextSolution(new HashMap<>());
			return this.car;
		} else {
			this.car = getNextSolution(this.car);
			return this.car;
		}
	}

	private String getValue(Variable variable) {
		Set<String> possibleValue = variableDomain.get(variable);
		if (!possibleValue.iterator().hasNext()) {
			for (int i = this.index; i < this.notUsed.size(); i++) {
				Variable var = this.notUsed.get(i);
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
		for (Constraint c : this.allConstraint)
			if (car.keySet().containsAll(c.getScope()) && !c.isSatisfiedBy(car))
				return false;

		return true;
	}

	private boolean alreadyGive(HashMap<Variable, String> car) {// test if the car make in solution has already been make ^^
		return precCar.contains(car);
	}

	private boolean filterDomain(Map<Variable, String> car, Map<Variable, Set<String>> domainVariable) {
		boolean hasFiltered = true;
		while (hasFiltered) {
			hasFiltered = false;
			for (Constraint c : this.allConstraint) {
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
								notUsed.remove(var);
								notUsed.add(1, var);
								index++;
								System.out.println(notUsed);
							}
						}
					}
				}
			}
		}
		return true;
	}
}
