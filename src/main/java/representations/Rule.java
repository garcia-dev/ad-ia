package representations;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	public boolean isSatisfiedBy(Map<Variable, String> assignment) {
		boolean incompatibilityConstraint = new IncompatibilityConstraint(premise).isSatisfiedBy(assignment);
		boolean disjunction = new Disjunction(conclusion).isSatisfiedBy(assignment);

		return incompatibilityConstraint || disjunction;
	}

	@Override
	public boolean filter(Map<Variable, String> assignment, Map<Variable, Set<String>> variableDomain) {
		return new IncompatibilityConstraint(premise).filter(assignment, variableDomain)
				&& new Disjunction(conclusion).filter(assignment, variableDomain);
	}
}
