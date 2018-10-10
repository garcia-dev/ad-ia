package planning;

import representations.Variable;

import java.util.Set;

public class State {
	private Set<Variable> variableSet;

	public State(Set<Variable> variableSet) {
		this.variableSet = variableSet;
	}
}
