package representations;

import java.util.Map;
import java.util.Set;

/**
<<<<<<< HEAD
=======
 * @version 2018-09-18
>>>>>>> origin
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class Rule implements Constraint {
	private Map<Variable, String> premise;
	private Map<Variable, String> conclusion;

	Rule(Map<Variable, String> premise, Map<Variable, String> conclusion) {
		this.premise = premise;
		this.conclusion = conclusion;
	}

	@Override
	public Set<Variable> getScope() {
		return null;
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		IncompatibilityConstraint incompatibilityConstraint = new IncompatibilityConstraint(premise);
		incompatibilityConstraint.isSatisfiedBy(allocation);

		Disjunction disjunction = new Disjunction(conclusion);
		disjunction.isSatisfiedBy(allocation);

		return true;
	}
}
