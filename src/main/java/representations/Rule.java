package representations;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rule implements Constraint {
	private Map<Variable, String> premise;
	private Map<Variable, String> conclusion;

	/**
	 * Constructs a new {@code Rule} with a given premise and conclusion.
	 *
	 * @param premise    the premise of the {@code Rule}
	 * @param conclusion the conclusion of the {@code Rule}
	 */
	public Rule(Map<Variable, String> premise, Map<Variable, String> conclusion) {
		this.premise = premise;
		this.conclusion = conclusion;
	}

	/**
	 * Returns the set of {@code Variable} involved in this {@code Rule}.
	 *
	 * @return the set of {@code Variable} involved in this {@code Rule}
	 */
	@Override
	public Set<Variable> getScope() {
		return Stream.concat(premise.keySet().stream(), conclusion.keySet().stream()).collect(Collectors.toSet());
	}

	/**
	 * Returns {@code true} if this {@code Rule} is satisfied by a given {@code
	 * Variable} assignment.
	 *
	 * @param assignment the {@code Variable} assignment to be tested
	 * @return {@code true} if this {@code Rule} is satisfied by the given
	 * assignment ; {@code false} otherwise
	 */
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
