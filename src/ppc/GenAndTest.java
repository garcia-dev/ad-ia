package ppc;

import representations.Variable;
import representations.Constraint;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;


public class GenAndTest {

	private Set<Constraint> constraints;
	private ArrayList<Variable> variables;

	public GenAndTest(Set<Constraint> constraints, Set<Variable> variables) {
		this.constraints = constraints;
		this.variables = new ArrayList<>(variables);
		HashMap<Variable, String> car = new HashMap<>();

		for (Variable variable : variables) {
			ArrayList<String> scope = new ArrayList<>(variable.getScope());
			car.put(variable, scope.get(0));
		}
		boolean test = doTest(car);
		int j = 1;
		while (test) {
			for (int i = 0; i < j; i++) {
				ArrayList<String> scope = new ArrayList<>(this.variables.get(i).getScope());
				if ((i % 3) == 0) {
					car.put(this.variables.get(i), scope.get(2));
				} else {
					car.put(this.variables.get(i), scope.get((i % 3) - 1));
				}

			}
			test = doTest(car);
			j++;
		}
		if (test) {
			System.out.println(car);
		}
	}

	public boolean doTest(HashMap<Variable, String> car) {
		for (Constraint c : this.constraints) {
			if (!c.isSatisfiedBy(car)) {
				return false;
			}
		}
		return true;
	}

}
