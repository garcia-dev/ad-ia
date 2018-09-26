package ppc;

import representations.Constraint;
import representations.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BackTracking {
	private Set<Constraint> allConstraint;
	private Set<Variable> allVariable;
	private List<Variable> notUsed;
	private HashMap<Variable, List<String>> variableDomain;

	public BackTracking(Set<Constraint> allConstraint, Set<Variable> allVariable) {
		this.allConstraint = allConstraint;
		this.allVariable = allVariable;

		this.notUsed = new ArrayList<>(allVariable);
		this.variableDomain = new HashMap<>();

		for (Variable var : this.allVariable)
			this.variableDomain.put(var, new ArrayList<>(var.getDomain()));

	}

	public HashMap<Variable, String> solution(HashMap<Variable, String> car, int index) {
		if (index < notUsed.size() && index >= 0) {
			String nextValue = getValue(notUsed.get(index));  //compute the next value return "" if there is no more value

			if (nextValue.equals("")) {
				car.remove(notUsed.get(index));

				return solution(car, index - 1);    //no other value in the domain so go back
			} else {
				car.put(notUsed.get(index), nextValue);

				if (doTest(car))
					return solution(car, index + 1); // the test is currently succesful so go ahead to add an other variable
				else
					return solution(car, index); //there is other value in the domain so try to test another one
			}

		} else
			return car;
	}

	private String getValue(Variable variable) {
		List<String> possibleValue = this.variableDomain.get(variable);

		if (possibleValue.isEmpty())
			return "";
		else {
			String value = possibleValue.get(0);
			possibleValue.remove(0);
			this.variableDomain.put(variable, possibleValue);
			return value;
		}
	}

	public boolean doTest(HashMap<Variable, String> car) {
		List<Variable> variables = new ArrayList<>(car.keySet());

		for (Constraint c : this.allConstraint)
			if (car.keySet().containsAll(c.getScope()))
				if (!c.isSatisfiedBy(car))
					return false;

		return true;
	}
}
