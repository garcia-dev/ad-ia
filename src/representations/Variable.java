package representations;

import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class Variable {

	private String name;
	private Set<String> domain;

	public Variable(String name, Set<String> domain) {
		this.name = name;
		this.domain = domain;
	}

	public String getName() {
		return name;
	}

	public Set<String> getDomain() {
		return domain;
	}

	@Override
	public String toString(){
		return this.name;

	}
}
