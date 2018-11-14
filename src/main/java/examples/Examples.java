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
	public static Variable ROOF_COLOR = new Variable("Roof color", COLORS);
	public static Variable HOOD_COLOR = new Variable("Hood color", COLORS);
	public static Variable TAILGATE_COLOR = new Variable("Tailgate color", COLORS);
	public static Variable LEFT_SIDE_COLOR = new Variable("Left-side color", COLORS);
	public static Variable RIGHT_SIDE_COLOR = new Variable("Right-side color", COLORS);

	/* Boolean variables */
	public static Variable HAS_OPENING_ROOF = new Variable("Has opening roof", BOOLEANS);
	public static Variable HAS_SONO = new Variable("Has sono", BOOLEANS);

	/* Constraints */
	private Constraint constraint1;
	private Constraint constraint2;
	private Constraint constraint3;
	private Constraint constraint4;

	private Constraint constraintTest;

	/* Sets */
	private Set<Constraint> constraints = new HashSet<>();
	private Set<Variable> variables = new HashSet<>();

	public Examples() {

		/* Constraints */

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
		HashMap<Variable, String> constraint2Premise = new HashMap<>();
		HashMap<Variable, String> constraint2Conclusion = new HashMap<>();
		constraint2Premise.put(ROOF_COLOR, "black");
		constraint2Conclusion.put(LEFT_SIDE_COLOR, "black");
		constraint2Conclusion.put(RIGHT_SIDE_COLOR, "blue");

		/* Constraint verifying that the sides are not both black */
		HashMap<Variable, String> constraint3Assignment = new HashMap<>();
		constraint3Assignment.put(LEFT_SIDE_COLOR, "black");
		constraint3Assignment.put(RIGHT_SIDE_COLOR, "black");

		/*
		 * Constraint verifying that the assignment doesn't have an opening roof
		 * and a sound system at the same time
		 */
		HashMap<Variable, String> constraint4Assignment = new HashMap<>();
		constraint4Assignment.put(HAS_OPENING_ROOF, "true");
		constraint4Assignment.put(HAS_SONO, "true");

		HashMap<Variable, String> constraintTestPremise = new HashMap<>();
		HashMap<Variable, String> constraintTestConclusion = new HashMap<>();
		constraintTestPremise.put(Examples.ROOF_COLOR, "blue");
		constraintTestConclusion.put(Examples.TAILGATE_COLOR, "red");
		constraintTestConclusion.put(Examples.HOOD_COLOR, "black");

		constraint1 = new AllEqualConstraint(constraint1Assignment);
		constraint2 = new Rule(constraint2Premise, constraint2Conclusion);
		constraint3 = new IncompatibilityConstraint(constraint3Assignment);
		constraint4 = new IncompatibilityConstraint(constraint4Assignment);
		constraintTest = new Rule(constraintTestPremise, constraintTestConclusion);

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
		constraints.add(constraintTest);

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

	public Set<Constraint> getConstraints() {
		return constraints;
	}

	public Set<Variable> getVariables() {
		return variables;
	}
}
