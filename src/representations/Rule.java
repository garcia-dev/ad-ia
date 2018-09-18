package representations;

import java.util.Map;
import java.util.Set;

/**
 * @version 2018-09-18
 * @author Romain Garcia
 */

class Rule implements Constraint {
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
