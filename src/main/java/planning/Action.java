package planning;

import representations.Rule;
import representations.Variable;

import java.util.List;
import java.util.Map;

public class Action {

	private Map<Variable, String> premise;
	private Map<Variable, String> conclusion;

	public Action(Map<Variable, String> premise, Map<Variable, String> conclusion) {
		this.premise = premise;
		this.conclusion = conclusion;
	}

	public boolean isApplicable(State state) {
		return false;
	}

}
