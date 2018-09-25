package representations;

import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class Variable {

	private String name;
	private Set<String> scope;

	public Variable(String name, Set<String> scope) {
		this.name = name;
		this.scope = scope;
	}

	public String getName() {
		return name;
	}

	public Set<String> getScope() {
		return scope;
	}

}
