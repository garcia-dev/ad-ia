package representations;

import java.util.Map;
import java.util.Set;

/**
<<<<<<< HEAD
=======
 * @version 2018-09-19
>>>>>>> origin
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class IncompatibilityConstraint extends Rule {

    private Map<Variable, String> variables;

	@Override
	public Set<Variable> getScope() {
		return null;
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		for (Map.Entry<Variable, String> entry : variables.entrySet()) {
			Variable key = entry.getKey();
			String value = entry.getValue();

			if (!value.equals(allocation.get(key)))
				return false;
		}

		return true;
	}
}
