package planning;

import representations.Variable;

import java.util.HashMap;
import java.util.Map;

public class State {

	private HashMap<Variable, String> assignments;

	public State(HashMap<Variable, String> assignments) {
		this.assignments = assignments;
	}

	public HashMap<Variable, String> getAssignments() {
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

}
