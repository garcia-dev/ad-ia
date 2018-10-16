package planning;

import java.util.List;
import java.util.Set;

public class PlanningProblem {

	private State initialState;
	private Set<State> finalStates;
	private List<Action> actionsList;

	public PlanningProblem(State initialState, Set<State> finalStates, List<Action> actionsList) {
		this.initialState = initialState;
		this.finalStates = finalStates;
		this.actionsList = actionsList;
	}

	private boolean satisfies(State state) {
		return finalStates.contains(state);
	}

	private State apply(Action action, State state) {
		return null;
	}

}
