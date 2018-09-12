package representations;

import java.util.Map;
import java.util.Set;

/**
 * @version 2018-09-11
 * @author Romain Garcia
 */

public interface Constraint {
	Set<Variable> getScope();
	boolean isSatisfiedBy(Map<Variable, String> map);
}
