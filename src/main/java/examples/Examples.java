package examples;

import representations.*;

import java.util.*;

public class Examples {

	// Colors domain
	public static final Set<String> COLORS =
			new HashSet<>(Arrays.asList("black", "blue", "red"));

	// Boolean domain
	public static final Set<String> BOOLEANS =
			new HashSet<>(Arrays.asList("true", "false"));

	// Color variables
	public static Variable ROOF_COLOR = new Variable("roofColor", COLORS);
	public static Variable HOOD_COLOR = new Variable("hoodColor", COLORS);
	public static Variable TAILGATE_COLOR = new Variable("tailgateColor", COLORS);
	public static Variable LEFT_SIDE_COLOR = new Variable("leftSideColor", COLORS);
	public static Variable RIGHT_SIDE_COLOR = new Variable("rightSideColor", COLORS);

	// Boolean variables
	public static Variable HAS_OPENING_ROOF = new Variable("hasOpeningRoof", BOOLEANS);
	public static Variable HAS_SONO = new Variable("hasSono", BOOLEANS);

	// Constraints definitions
	private static Constraint constraint1;
	private static Constraint constraint2;
	private static Constraint constraint3;
	private static Constraint constraint4;

	// Sets
	private static Set<Constraint> constraints = new HashSet<>();
	private static Set<Variable> variables = new HashSet<>();

	// Constraints assignments
	static {

		/*
		 * Constraint verifying that roof, hood and tailgate are the same color
		 */
		Set<Variable> constraint1Assignment = new HashSet<>();
		constraint1Assignment.add(ROOF_COLOR);
		constraint1Assignment.add(HOOD_COLOR);
		constraint1Assignment.add(TAILGATE_COLOR);

		/*
		 * Constraint verifying that at least one side is the same color as the
		 * roof
		 */
		Map<Variable, String> constraint2Premise = new HashMap<>();
		Map<Variable, String> constraint2Conclusion = new HashMap<>();
		constraint2Premise.put(ROOF_COLOR, "black");
		constraint2Conclusion.put(LEFT_SIDE_COLOR, "black");
		constraint2Conclusion.put(RIGHT_SIDE_COLOR, "blue");

		/* Constraint verifying that the sides are not both black */
		Map<Variable, String> constraint3Assignment = new HashMap<>();
		constraint3Assignment.put(LEFT_SIDE_COLOR, "black");
		constraint3Assignment.put(RIGHT_SIDE_COLOR, "black");

		/*
		 * Constraint verifying that the assignment doesn't have an opening roof
		 * and a sound system at the same time
		 */
		Map<Variable, String> constraint4Assignment = new HashMap<>();
		constraint4Assignment.put(HAS_OPENING_ROOF, "true");
		constraint4Assignment.put(HAS_SONO, "true");

		constraint1 = new AllEqualConstraint(constraint1Assignment);
		constraint2 = new Rule(constraint2Premise, constraint2Conclusion);
		constraint3 = new IncompatibilityConstraint(constraint3Assignment);
		constraint4 = new IncompatibilityConstraint(constraint4Assignment);

		/* Sets */
		variables.add(ROOF_COLOR);
		variables.add(HOOD_COLOR);
		variables.add(TAILGATE_COLOR);
		variables.add(LEFT_SIDE_COLOR);
		variables.add(RIGHT_SIDE_COLOR);
		variables.add(HAS_OPENING_ROOF);
		variables.add(HAS_SONO);

		constraints.add(constraint1);
		constraints.add(constraint2);
		constraints.add(constraint3);
		constraints.add(constraint4);

	}

	public static Constraint getConstraint1() {
		return constraint1;
	}

	public static Constraint getConstraint2() {
		return constraint2;
	}

	public static Constraint getConstraint3() {
		return constraint3;
	}

	public static Constraint getConstraint4() {
		return constraint4;
	}

	public static Set<Constraint> getConstraints() {
		return constraints;
	}

	public static Set<Variable> getVariables() {
		return variables;
	}
}
