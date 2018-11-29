package representations;

import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class Variable {
	/**
	 * Name of this {@code Variable}
	 */
	private String name;

	/**
	 * Domain of this {@code Variable}
	 */
	private Set<String> domain;

	/**
	 * Constructs a new {@code Variable} from a name and a domain.
	 *
	 * @param name the name of the {@code Variable}
	 * @param domain the domain of the {@code Variable}
	 */
	public Variable(String name, Set<String> domain) {
		this.name = name;
		this.domain = domain;
	}

	/**
	 * Returns the name of this {@code Variable}.
	 *
	 * @return the name of this {@code Variable}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the domain of this {@code Variable}.
	 *
	 * @return the domain of this {@code Variable}
	 */
	public Set<String> getDomain() {
		return domain;
	}

	/**
	 * Sets this domain.
	 *
	 * @param domain the domain to be set
	 */
	public void setDomain(Set<String> domain) {
		this.domain = domain;
	}

	/**
	 * Returns the string representation of this {@code Variable}.
	 *
	 * @return the string representation of this {@code Variable}
	 */
	@Override
	public String toString() {
		return name;
	}
}
