package planning;

import representations.Variable;

import java.util.*;

/**
 * PlanningProblemWithCost's class
 * <p>
 * The class PlanningProblemWithCost is used to search an Action plan to go from the initialState to the goalState with
 * an aspect of cost of Action.
 * </p>
 *
 * @author DORANGE Marin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-28
 * @see PlanningProblem
 * @see State
 * @see Action
 */
public class PlanningProblemWithCost extends PlanningProblem {
	private final Map<Action, Integer> actionsCostMap;

	/**
	 * PlanningProblemWithCost's constructor initializing it with an initialState, a goalState and a list of Action
	 * that can be used.
	 * <p>
	 * It calculates the cost of each Action of the actionsList and collect it into a map.
	 * </p>
	 *
	 * @param initialState the initialState
	 * @param goalState    the goalState
	 * @param actionsList  the list of Action that can be used
	 * @see PlanningProblem
	 */
	public PlanningProblemWithCost(State initialState, State goalState, List<Action> actionsList) {
		super(initialState, goalState, actionsList);

		actionsCostMap = new HashMap<>();

		actionsList.forEach(action -> {
			if (action.getRuleList().size() == 1)
				actionsCostMap.put(action, 2);
			else
				actionsCostMap.put(action, 1);
		});
	}

	/**
	 * Method searching an Action plan to go from the initialState to the goalState using the Dijkstra algorithm.
	 *
	 * @return an Action plan to go from the initialState to the goalState
	 */
	public Stack<Action> dijkstra() {
		Map<State, Integer> distance = new HashMap<>();
		Map<State, State> father = new HashMap<>();
		Map<State, Action> plan = new HashMap<>();

		Set<State> goals = new HashSet<>();
		Set<State> open = new HashSet<>();

		open.add(initialState);
		distance.put(initialState, 0);
		father.put(initialState, null);

		while (!open.isEmpty()) {
			State state = distance.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
			open.remove(state);

			System.out.println("loop");

			if (state.satisfies(goalState))
				goals.add(state);

			actionsList.forEach(action -> {
				if (action.isApplicable(state)) {
					State next = action.apply(state);

					if (!distance.containsKey(next))
						distance.put(next, Integer.MAX_VALUE);

					if (distance.get(next) > (distance.get(state) + actionsCostMap.get(action))) {
						distance.put(next, (distance.get(state) + actionsCostMap.get(action)));
						father.put(next, state);
						plan.put(next, action);
						open.add(next);
					}
				}
			});
		}

		return getDijkstraPlan(father, plan, distance);
	}

	/**
	 * Method rebuilding the Action plan to go from the initialState to the goalState.
	 *
	 * @param father   a Map linking State with each other
	 * @param plan     an Action plan
	 * @param distance a Map of State and the distance between them and the initialState
	 * @return an Action plan to go from the initialState to the goalState
	 */
	private Stack<Action> getDijkstraPlan(Map<State, State> father, Map<State, Action> plan, Map<State, Integer> distance) {
		State goal = distance.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();

		return calcPlan(father, plan, goal);
	}

	/**
	 * Method searching an Action plan to go from the initialState to the goalState using the A Star algorithm.
	 *
	 * @return an Action plan to go from the initialState to the goalState
	 */
	public Stack<Action> aStar() {
		Map<State, State> father = new HashMap<>();
		Map<State, Integer> distance = new HashMap<>();
		Map<State, Integer> value = new HashMap<>();
		Map<State, Action> actionMap = new HashMap<>();

		Set<State> open = new HashSet<>();
		open.add(initialState);

		father.put(initialState, null);
		distance.put(initialState, 0);
		value.put(initialState, heuristic(initialState));

		while (!open.isEmpty()) {
			State state = distance.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();

			if (state.satisfies(goalState)) {
				return calcPlan(father, actionMap, goalState);
			} else {
				open.remove(state);

				actionsList.forEach(action -> {
					if (action.isApplicable(state)) {
						State next = action.apply(state);

						if (!distance.containsKey(next))
							distance.put(next, Integer.MAX_VALUE);

						if (distance.get(next) > (distance.get(state) + actionsCostMap.get(action))) {
							distance.put(next, (distance.get(state) + actionsCostMap.get(action)));
							value.put(next, distance.get(next) + heuristic(next));
							father.put(next, state);
							actionMap.put(next, action);
							open.add(next);
						}
					}
				});
			}
		}

		return null;
	}

	/**
	 * Method calculating the heuristic value of a given State by comparing it with the goalState.
	 * <p>
	 * The heuristic value is the number of differences between the given State and the goalState.
	 * </p>
	 *
	 * @param state the State which we are calculating the heuristic value
	 * @return the heuristic value of the State
	 */
	private int heuristic(State state) {
		int differences = 0;

		for (Map.Entry<Variable, String> entry : state.entrySet()) {
			Variable key = entry.getKey();
			String value = entry.getValue();
			if (!goalState.get(key).equals(value))
				differences++;
		}

		return differences;
	}
}
