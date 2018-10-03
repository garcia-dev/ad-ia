package representations;

import java.util.Map;
import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public interface Constraint {
	Set<Variable> getScope();

	boolean isSatisfiedBy(Map<Variable, String> allocation);

	boolean filter(Map<Variable, String> allocation, Map<Variable, Set<String>> variableDomain);
}
