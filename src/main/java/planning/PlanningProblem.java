package planning;

import java.util.*;

/**
 * PlanningProblem's class
 * <p>
 * The class PlanningProblem is used to search an Action plan to go from the initialState to the goalState.
 * </p>
 *
 * @author DORANGE Marin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-28
 * @see State
 * @see Action
 */
public class PlanningProblem {
	final State initialState;
	final List<Action> actionsList;
	final State goalState;

	/**
	 * PlanningProblem's constructor initializing it with an initialState, a goalState and a list of Action that can
	 * be used.
	 *
	 * @param initialState the initialState
	 * @param goalState    the goalState
	 * @param actionsList  the list of Action that can be used
	 */
	public PlanningProblem(State initialState, State goalState, List<Action> actionsList) {
		this.initialState = initialState;
		this.goalState = goalState;
		this.actionsList = actionsList;
	}

	/**
	 * Method searching an Action plan to go from the initialState to the goalState using the Depth First method.
	 *
	 * @param state  the State from which we are beginning the search
	 * @param plan   the Action plan that will be returned
	 * @param closed the State already searched through
	 * @return an Action plan to go from the initialState to the goalState
	 */
	public Stack<Action> depthFirstSearch(State state, Stack<Action> plan, List<State> closed) {
		if (state.satisfies(goalState))
			return plan;
		else {
			State next = new State();

			for (Action action : actionsList)
				if (action.isApplicable(state))
					next = action.apply(next);
				else if (!closed.contains(next)) {
					plan.push(action);
					closed.add(next);

					Stack<Action> subPlan = depthFirstSearch(next, plan, closed);

					if (!(subPlan.isEmpty()))
						return subPlan;
					else
						plan.pop();
				}
		}

		return new Stack<>();
	}

	/**
	 * Method searching an Action plan to go from the initialState to the goalState using the Breath First method.
	 *
	 * @return an Action plan to go from the initialState to the goalState
	 */
	public Stack<Action> breathFirstSearch() {
		Map<State, State> father = new HashMap<>();
		Map<State, Action> plan = new HashMap<>();

		Set<State> closed = new HashSet<>();
		LinkedList<State> open = new LinkedList<>();

		open.add(initialState);

		while (!open.isEmpty()) {
			State state = open.poll();
			closed.add(state);

			for (Action action : actionsList)
				if (action.isApplicable(state)) {
					State next = action.apply(state);
					if (!closed.contains(next) && !open.contains(next)) {
						father.put(next, state);
						plan.put(next, action);

						if (next.satisfies(goalState))
							return calcPlan(father, plan, goalState);
						else
							open.offer(next);
					}
				}
		}

		return new Stack<>();
	}

	/**
	 * Method calculating an Action plan to go from the initialState to the given targetState.
	 * <p>
	 * This method is used in the following algorithms :
	 * - Breath First Search
	 * - Dijkstra
	 * - A Star
	 * </p>
	 * <p>
	 * It is mainly created to avoid repeating code like : getBfsPlan and huge part of getDijkstraPlan.
	 * </p>
	 *
	 * @param father      a Map linking State with each other
	 * @param plan        a Map linking a State with an Action to apply to reach it from the State n-1
	 * @param targetState the goalState that we want to reach from our initialState
	 * @return an Action plan to get from the initialState to the given targetState
	 */
	Stack<Action> calcPlan(Map<State, State> father, Map<State, Action> plan, State targetState) {
		Stack<Action> tempPlan = new Stack<>();

		while (!targetState.isEmpty()) {
			tempPlan.push(plan.get(targetState));
			targetState = father.get(targetState);
		}

		tempPlan.sort(Collections.reverseOrder());

		return tempPlan;
	}
}
