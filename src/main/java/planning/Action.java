package planning;

import representations.Variable;

import java.util.HashMap;
import java.util.Map;

public class Action {

	private Map<Variable, String> preconditions;
	private Map<Variable, String> effects;

	public Action(Map<Variable, String> preconditions, Map<Variable, String> effects) {
		this.preconditions = preconditions;
		this.effects = effects;
	}

	public boolean isApplicable(State state) {
		return state.satistfies(new State(preconditions));
	}

	public Map<Variable, String> getEffects() {
		return effects;
	}
}
