import example.AssemblyLine;
import planning.PlanningProblem;
import planning.PlanningProblemWithCost;
import planning.State;

import java.util.ArrayList;
import java.util.Stack;

class AssemblyLineTest {
	public static void main(String[] args) {
		AssemblyLine assemblyLine = new AssemblyLine();

		State goalState = assemblyLine.generateRandomGoalState();

		PlanningProblem planningProblem = new PlanningProblem(new State(), goalState, AssemblyLine.getActionList());
		System.out.println(planningProblem.depthFirstSearch(new State(), new Stack<>(), new ArrayList<>()));
		System.out.println(planningProblem.breathFirstSearch());

		PlanningProblemWithCost planningProblemWithCost = new PlanningProblemWithCost(new State(), goalState, AssemblyLine.getActionList());
		System.out.println(planningProblemWithCost.dijkstra());
		System.out.println(planningProblemWithCost.aStar());
	}
}
