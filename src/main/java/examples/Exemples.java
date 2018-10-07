package examples;

import ppc.Backtracking;
import representations.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Exemples {

	/* Color domain */
	private static final Set<String> COLORS = new HashSet<>(Arrays.asList("black", "red", "green", "blue"));

	/* Boolean domain */
	private static final Set<String> BOOLEANS = new HashSet<>(Arrays.asList("true", "false"));

	/* Color variables */
	private Variable roofColor = new Variable("roofColor", COLORS);
	private Variable hoodColor = new Variable("hoodColor", COLORS);
	private Variable tailgateColor = new Variable("tailgateColor", COLORS);
	private Variable leftSideColor = new Variable("leftSideColor", COLORS);
	private Variable rightSideColor = new Variable("rightSideColor", COLORS);

	/* Boolean variables */
	private Variable hasOpeningRoof = new Variable("hasOpeningRoof", BOOLEANS);
	private Variable hasSono = new Variable("hasSono", BOOLEANS);

	/* Constraints */
	public AllEqualConstraint constraint1;
	public AllEqualConstraint constraint2;
	public AllEqualConstraint constraint3;
	public AllEqualConstraint constraint4;

	public Exemples() {

		/* Cars */

		/* Car whose roof, hood and tailgate are the same color */
		HashMap<Variable, String> car1 = new HashMap<>();
		car1.put(roofColor, "black");
		car1.put(hoodColor, "black");
		car1.put(tailgateColor, "black");

		/* Car whose at least one side is the same color as the roof */
		HashMap<Variable, String> car2 = new HashMap<>();
		car2.put(roofColor, "black");
		car2.put(leftSideColor, "black");
		car2.put(rightSideColor, "blue");

		/* Car whose sides are not both black */
		HashMap<Variable, String> car3 = new HashMap<>();
		car3.put(roofColor, "black");
		car3.put(leftSideColor, "black");
		car3.put(rightSideColor, "blue");

		/* Constraints */

		HashSet<Constraint> constraints = new HashSet<>();
		HashSet<Variable> variables = new HashSet<>();

		HashMap<Variable, String> openingRoofAndSono = new HashMap<>();
		openingRoofAndSono.put(hasOpeningRoof, "true");
		openingRoofAndSono.put(hasSono, "true");

		HashMap<Variable, String> antiOpeningRoofAndSonoAndAerial = new HashMap<>();
		antiOpeningRoofAndSonoAndAerial.put(hasOpeningRoof, "true");
		antiOpeningRoofAndSonoAndAerial.put(hasSono, "true");

		System.out.println("Opening roof and hasSono and aerial are satisfied: "
				+ new IncompatibilityConstraint(openingRoofAndSono).isSatisfiedBy(antiOpeningRoofAndSonoAndAerial));

		HashSet<Variable> allEqualVariable = new HashSet<>();
		allEqualVariable.add(leftSideColor);
		allEqualVariable.add(rightSideColor);
		allEqualVariable.add(roofColor);

		variables.add(roofColor);
		variables.add(hoodColor);
		variables.add(tailgateColor);
		variables.add(leftSideColor);
		variables.add(rightSideColor);
		variables.add(hasOpeningRoof);
		variables.add(hasSono);

		Constraint allEqualConstraint = new AllEqualConstraint(allEqualVariable);

		HashMap<Variable, String> premise = new HashMap<>();
		premise.put(roofColor, "red");

		HashMap<Variable, String> conclusion = new HashMap<>();
		conclusion.put(hasSono, "True");

		Constraint rule = new Rule(premise, conclusion);

		constraints.add(rule);
		constraints.add(allEqualConstraint);

		Backtracking ppc = new Backtracking(constraints, variables);

		System.out.println("Solution: " + ppc.solution());
	}


}
