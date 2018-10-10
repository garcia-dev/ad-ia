package examples;

import representations.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Examples {

	/* Color domain */
	private static final Set<String> COLORS =
			new HashSet<>(Arrays.asList("black", "red", "green", "blue"));

	/* Boolean domain */
	private static final Set<String> BOOLEANS =
			new HashSet<>(Arrays.asList("true", "false"));

	/* Color variables */
	public static Variable roofColor = new Variable("roofColor", COLORS);
	public static Variable hoodColor = new Variable("hoodColor", COLORS);
	public static Variable tailgateColor = new Variable("tailgateColor", COLORS);
	public static Variable leftSideColor = new Variable("leftSideColor", COLORS);
	public static Variable rightSideColor = new Variable("rightSideColor", COLORS);

	/* Boolean variables */
	public static Variable hasOpeningRoof = new Variable("hasOpeningRoof", BOOLEANS);
	public static Variable hasSono = new Variable("hasSono", BOOLEANS);

	/* Constraints */
	private Constraint constraint1;
	private Constraint constraint2;
	private Constraint constraint3;
	private Constraint constraint4;

	public Examples() {

		/* Constraints */

		/*
		 * Constraint verifying that roof, hood and tailgate are the same color
		 */
		Set<Variable> constraint1Assignment = new HashSet<>();
		constraint1Assignment.add(roofColor);
		constraint1Assignment.add(hoodColor);
		constraint1Assignment.add(tailgateColor);

		/*
		 * Constraint verifying that at least one side is the same color as the
		 * roof
		 */
		HashMap<Variable, String> constraint2Premise = new HashMap<>();
		HashMap<Variable, String> constraint2Conclusion = new HashMap<>();
		constraint2Premise.put(roofColor, "black");
		constraint2Conclusion.put(leftSideColor, "black");
		constraint2Conclusion.put(rightSideColor, "blue");

		/* Constraint verifying that the sides are not both black */
		HashMap<Variable, String> constraint3Assignment = new HashMap<>();
		constraint3Assignment.put(leftSideColor, "black");
		constraint3Assignment.put(rightSideColor, "black");

		/*
		 * Constraint verifying that the assignment doesn't have an opening roof
		 * and a sound system at the same time
		 */
		HashMap<Variable, String> constraint4Assignation = new HashMap<>();
		constraint4Assignation.put(hasOpeningRoof, "true");
		constraint4Assignation.put(hasSono, "true");

		constraint1 = new AllEqualConstraint(constraint1Assignment);
		constraint2 = new Rule(constraint2Premise, constraint2Conclusion);
		constraint3 = new IncompatibilityConstraint(constraint3Assignment);
		constraint4 = new IncompatibilityConstraint(constraint4Assignation);

	}

	public Constraint getConstraint1() {
		return constraint1;
	}

	public Constraint getConstraint2() {
		return constraint2;
	}

	public Constraint getConstraint3() {
		return constraint3;
	}

	public Constraint getConstraint4() {
		return constraint4;
	}
}
