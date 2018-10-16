package ppc;

import representations.Variable;
import representations.Constraint;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;


public class GenerateAndTest {

	private Set<Constraint> constraints;
	private ArrayList<Variable> variables;

	public GenerateAndTest(Set<Constraint> constraints, Set<Variable> variables) {
		this.constraints = constraints;
		this.variables = new ArrayList<>(variables);

		HashMap<Variable, String> car = new HashMap<>();

		for (Variable variable : variables) {
			ArrayList<String> domain = new ArrayList<>(variable.getDomain());
			car.put(variable, domain.get(0));
		}

		boolean test = doTest(car);
		int j = 1;
		while (test) {
			for (int i = 0; i < j; i++) {
				ArrayList<String> domain = new ArrayList<>(this.variables.get(i).getDomain());
				if ((i % 3) == 0) {
					car.put(this.variables.get(i), domain.get(2));
				} else {
					car.put(this.variables.get(i), domain.get((i % 3) - 1));
				}

			}
			test = doTest(car);
			j++;
		}
		if (test) {
			System.out.println(car);
		}
	}

	private boolean doTest(HashMap<Variable, String> car) {
		for (Constraint constraint : this.constraints) {
			if (!constraint.isSatisfiedBy(car)) {
				return false;
			}
		}
		return true;
	}

}
