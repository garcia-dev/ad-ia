package representations;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllEqualConstraint implements Constraint {

	private Set<Variable> variables;

	/**
	 * Constructs a new {@code AllEqualConstraint} with a given set of {@code
	 * Variable}.
	 *
	 * @param variables the set of {@code Variable}
	 */
	public AllEqualConstraint(Set<Variable> variables) {
		this.variables = variables;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Variable> getScope() {
		return variables;
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> assignment) {
		String value = "";

		for (Variable variable : variables) {
			if (value.equals("")) {
				value = assignment.get(variable);
				continue;
			}

			if (!assignment.get(variable).equals(value)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean filter(Map<Variable, String> car, Map<Variable, Set<String>> variableDomain) {
		Variable varPrev = null;
		boolean hasFiltered = false;

		for (Variable var : this.variables)
			if (car.containsKey(var)) {
				varPrev = var;
				break;
			}

		if (varPrev != null) {
			Set<String> valueDomain = new HashSet<>();
			valueDomain.add(car.get(varPrev));

			for (Variable var : this.variables)
				if (!car.containsKey(var) && !(variableDomain.get(var).size() == 1)) {
					variableDomain.put(var, new HashSet<>(valueDomain));
					hasFiltered = true;
				} else if (!variableDomain.get(var).equals(valueDomain) && !car.containsKey(var))
					variableDomain.put(var, new HashSet<>());
		}

		return hasFiltered;
	}
}