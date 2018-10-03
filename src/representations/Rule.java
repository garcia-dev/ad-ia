package representations;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * @author Romain Garcia
 * @version 2018-09-26
 */

public class Rule implements Constraint {

	private Map<Variable, String> premise;
	private Map<Variable, String> conclusion;

	public Rule(Map<Variable, String> premise, Map<Variable, String> conclusion) {
		this.premise = premise;
		this.conclusion = conclusion;
	}

	@Override
	public Set<Variable> getScope() {
		return Stream.concat(premise.keySet().stream(), conclusion.keySet().stream()).collect(Collectors.toSet());
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		boolean incompatibilityConstraint = new IncompatibilityConstraint(premise).isSatisfiedBy(allocation);
		boolean disjunction = new Disjunction(conclusion).isSatisfiedBy(allocation);

		return incompatibilityConstraint || disjunction;
	}


	@Override
	public boolean filter(Map<Variable, String> allocation, Map<Variable, Set<String>> variableDomain) {
		return new IncompatibilityConstraint(premise).filter(allocation, variableDomain) && new Disjunction(conclusion).filter(allocation, variableDomain);
	}
}
