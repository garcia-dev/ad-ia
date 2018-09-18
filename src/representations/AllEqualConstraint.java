package representations;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class AllEqualConstraint implements Constraint {
<<<<<<< HEAD
	Set<Variable> variables;

	public AllEqualConstraint(Set<Variable> variables) {
		this.variables = variables;
	}
=======
	Set<Variable> variables;

	public AllEqualConstraint(Set<Variable> variables) {
		this.variables = variables;
	}
>>>>>>> c518a1c29e8f60a57586b4d587367510885fd52a

	@Override
	public Set<Variable> getScope() {
		return variables;
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		String value = null;

		for (Variable key : variables) {
			if (value == null)
				value = allocation.get(key);

			if (!allocation.get(key).equals(value))
				return false;
		}

		return true;
	}

}