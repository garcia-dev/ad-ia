package representations;

import java.util.Map;
import java.util.Set;

public interface Constraint {

	/**
	 * Returns the set of {@code Variable} involved in this {@code Constraint}.
	 *
	 * @return the set of {@code Variable} involved in this {@code Constraint}
	 */
	Set<Variable> getScope();

	/**
	 * Returns {@code true} if this {@code Constraint} is satisfied by a given
	 * {@code Variable} assignment.
	 *
	 * @param assignment the {@code Variable} assignment to be tested
	 * @return {@code true} if this {@code Constraint} is satisfied by the given
	 * assignment ; {@code false} otherwise
	 */
	boolean isSatisfiedBy(Map<Variable, String> assignment);

	boolean filter(Map<Variable, String> allocation, Map<Variable, Set<String>> variableDomain);

}
