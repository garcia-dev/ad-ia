package main.java.representations;

import java.util.Set;

public class Variable {
	private final String name;
	private final Set<String> domain;

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
	public String toString() {
		return name;
	}
}
