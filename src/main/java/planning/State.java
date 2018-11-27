package planning;

import representations.Variable;

import java.util.HashMap;
import java.util.Map;

public class State {

	private Map<Variable, String> assignments;

	public State(Map<Variable, String> assignments) {
		this.assignments = assignments;
	}

	public Map<Variable, String> getAssignments() {
		return assignments;
	}

	public boolean satistfies(State state) {
		for (Map.Entry<Variable, String> assignment : assignments.entrySet()) {
			Variable key = assignment.getKey();
			if (!state.getAssignments().containsKey(key)
					|| !(assignments.get(key).equals(state.getAssignments().get(key)))) {
				return false;
			}
		}

		return true;
	}

	public State apply(Action action) {
		Map<Variable, String> stateAssignments = new HashMap<>(getAssignments());

		for (Map.Entry<Variable, String> effect : action.getEffects().entrySet()) {
			Variable key = effect.getKey();
			stateAssignments.put(key, action.getEffects().get(key));
		}

		return new State(stateAssignments);
	}



}
