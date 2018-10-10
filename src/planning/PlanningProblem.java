package planning;

import java.util.ArrayList;
import java.util.Set;

public class PlanningProblem {
	private State initialState;
	private Set<State> finalStateSet;
	private ArrayList<Action> actionArrayList;

	public PlanningProblem(State initialState, Set<State> finalStateSet, ArrayList<Action> actionArrayList) {
		this.initialState = initialState;
		this.finalStateSet = finalStateSet;
		this.actionArrayList = actionArrayList;
	}

	private boolean satisfies(State state) {
		return false;
	}
}
