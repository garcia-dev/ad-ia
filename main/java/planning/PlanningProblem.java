package planning;

import java.util.List;
import java.util.Set;

class PlanningProblem {
	private State initialState;
	private Set<State> finalStates;
	private List<Action> actionsList;

	public PlanningProblem(State initialState, Set<State> finalStates, List<Action> actionsList) {
		this.initialState = initialState;
		this.finalStates = finalStates;
		this.actionsList = actionsList;
	}
}
