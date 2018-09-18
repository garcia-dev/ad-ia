package representations;

import java.util.Map;

/**
 * @version 2018-09-11
 * @author Romain Garcia
 */

public class Disjunction extends Rule {
	private Map<Variable, String> variables;

	public Disjunction(Map<Variable, String> premise, Map<Variable, String> conclusion) {
		super(premise, conclusion);
	}
}
