import example.Examples;
import ppc.Backtracking;
import representations.Variable;

import java.util.HashMap;
import java.util.Map;

public class BacktrackingTests {
	public static void main(String[] args) {
		/* Cars */

		/* Car whose roof, hood and tailgate are the same color */
		HashMap<Variable, String> car1 = new HashMap<>();
		car1.put(Examples.ROOF_COLOR, "black");
		car1.put(Examples.HOOD_COLOR, "black");
		car1.put(Examples.TAILGATE_COLOR, "black");

		/* Car whose at least one side is the same color as the roof */
		HashMap<Variable, String> car2 = new HashMap<>();
		car2.put(Examples.ROOF_COLOR, "black");
		car2.put(Examples.LEFT_SIDE_COLOR, "black");
		car2.put(Examples.RIGHT_SIDE_COLOR, "blue");

		/* Car whose sides are not both black */
		HashMap<Variable, String> car3 = new HashMap<>();
		car3.put(Examples.LEFT_SIDE_COLOR, "black");
		car3.put(Examples.RIGHT_SIDE_COLOR, "blue");

		/* Car which doesn't have an opening roof and a sound system at the same time */
		HashMap<Variable, String> car4 = new HashMap<>();
		car4.put(Examples.HAS_OPENING_ROOF, "true");
		car4.put(Examples.HAS_SONO, "false");

		/* Simple tests */

		if (Examples.getConstraint1().isSatisfiedBy(car1)) {
			System.out.println("The first constraint is satisfied by the first car.");
		} else {
			System.out.println("There is something wrong with the first constraint.");
		}

		if (Examples.getConstraint2().isSatisfiedBy(car2)) {
			System.out.println("The second constraint is satisfied by the second car.");
		} else {
			System.out.println("There is something wrong with the second constraint.");
		}

		if (Examples.getConstraint3().isSatisfiedBy(car3)) {
			System.out.println("The third constraint is satisfied by the third car.");
		} else {
			System.out.println("There is something wrong with the third constraint.");
		}

		if (Examples.getConstraint4().isSatisfiedBy(car4)) {
			System.out.println("The fourth constraint is satisfied by the fourth car.");
		} else {
			System.out.println("There is something wrong with the fourth constraint.");
		}

		/* Backtracking tests */

		backtrack();

	}

	private static void backtrack() {
		int numberOfSolutions = 0;
		long startTime = System.currentTimeMillis();

		Backtracking backtracking = new Backtracking(Examples.getVariables(),
				Examples.getConstraints());

		Map<Variable, String> car = backtracking.solution();
		while (car != null) {
			numberOfSolutions++;
			printCar(car);
			car = backtracking.solution();
		}

		System.out.println("\nNumber of solutions: " + numberOfSolutions);

		long endTime = System.currentTimeMillis();
		System.out.println("Total elapsed time in execution for the backtracking:" + (endTime - startTime));

	}

	private static void printCar(Map<Variable, String> car) {
		System.out.println("\nSolution:");
		car.forEach(
				(key, value) -> System.out.println("\t" + key.getName() + ": " + value + ",")
		);
	}

}
