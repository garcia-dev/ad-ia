package planning;

import java.util.ArrayList;

public class Action {
	private ArrayList<State> stateArrayList;

	public Action(ArrayList<State> stateArrayList) {
		this.stateArrayList = stateArrayList;
	}

	private boolean isApplicable(State state) {
		return false;
	}
}
