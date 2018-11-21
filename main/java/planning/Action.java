package planning;

import representations.Variable;

import java.util.Map;

public class Action {
	private Map<Variable, String> preconditions;
	private Map<Variable, String> effects;

	public Action(Map<Variable, String> preconditions, Map<Variable, String> effects) {
		this.preconditions = preconditions;
		this.effects = effects;
	}

	public Map<Variable, String> getEffects() {
		return effects;
	}

	public boolean isApplicable(State state) {
		return state.satisfies(new State(preconditions));
	}
}
