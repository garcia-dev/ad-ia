package planning;

import representations.Variable;

import java.util.HashMap;
import java.util.Map;

public class State {
	private HashMap<Variable, String> variableMap;

	public State(HashMap<Variable, String> variableMap) {
		this.variableMap = variableMap;
	}

	public HashMap<Variable, String> getVariableMap() {
		return variableMap;
	}

	public boolean satistfies(State state) {
		for (Map.Entry<Variable, String> entry : variableMap.entrySet()) {
			Variable key = entry.getKey();
			if (!state.getVariableMap().containsKey(key) || !(variableMap.get(key).equals(state.getVariableMap().get(key))))
				return false;
		}

		return true;
	}
}
