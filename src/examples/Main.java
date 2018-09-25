package examples;

import representations.*;
import ppc.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		/* Values that a color variable can take */
		Set<String> colorSet = new HashSet<>();
		colorSet.add("black");
		colorSet.add("blue");
		colorSet.add("green");
		colorSet.add("red");

		/* Values that a boolean variable can take */
		Set<String> booleanSet = new HashSet<>();
		booleanSet.add("true");
		booleanSet.add("false");

		/* Color variables */
		Variable roofColor = new Variable("roofColor", colorSet);
		Variable hoodColor = new Variable("hoodColor", colorSet);
		Variable tailgateColor = new Variable("tailgateColor", colorSet);
		Variable leftSide = new Variable("leftSide", colorSet);
		Variable rightSide = new Variable("rightSide", colorSet);

		Variable openingRoof = new Variable("openingRoof", booleanSet);
		Variable sono = new Variable("sono", booleanSet);
		Variable aerial = new Variable("aerial", booleanSet);

		HashSet<Constraint> constraints = new HashSet<>();
		HashSet<Variable> variables = new HashSet<>();

		HashMap<Variable, String> openingRoofAndSonoAndAerial = new HashMap<>();
		openingRoofAndSonoAndAerial.put(openingRoof, "true");
		openingRoofAndSonoAndAerial.put(sono, "true");
		openingRoofAndSonoAndAerial.put(aerial, "true");
		HashMap<Variable, String> antiOpeningRoofAndSonoAndAerial = new HashMap<>();
		antiOpeningRoofAndSonoAndAerial.put(openingRoof, "true");
		antiOpeningRoofAndSonoAndAerial.put(sono, "true");
		antiOpeningRoofAndSonoAndAerial.put(aerial, "false");
		System.out.println("Opening roof and sono and aerial are satisfied: "
				+ new IncompatibilityConstraint(openingRoofAndSonoAndAerial).isSatisfiedBy(antiOpeningRoofAndSonoAndAerial));

		HashSet<Variable> allEqualVariable = new HashSet<>();
		allEqualVariable.add(leftSide);
		allEqualVariable.add(rightSide);
		allEqualVariable.add(roofColor);

		variables.add(roofColor);
		variables.add(hoodColor);
		variables.add(tailgateColor);
		variables.add(leftSide);
		variables.add(rightSide);
		variables.add(openingRoof);
		variables.add(sono);

		Constraint allEqualConstraint = new AllEqualConstraint(allEqualVariable);

		HashMap<Variable, String> premise = new HashMap<>();
		premise.put(roofColor, "red");

		HashMap<Variable, String> conclusion = new HashMap<>();
		conclusion.put(sono, "True");

		Constraint rule = new Rule(premise, conclusion);

		constraints.add(rule);
		constraints.add(allEqualConstraint);

		Backtracking ppc = new Backtracking(constraints, variables);

		System.out.println("Solution: " + ppc.solution());
	}

}
