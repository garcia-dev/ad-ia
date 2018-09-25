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
		booleanSet.add("True");
		booleanSet.add("False");

		/* Color variables */
		Variable roofColor = new Variable("roofColor", colorSet);
		Variable hoodColor = new Variable("hoodColor", colorSet);
		Variable tailgateColor = new Variable("tailgateColor", colorSet);
		Variable leftSide = new Variable("leftSide", colorSet);
		Variable rightSide = new Variable("rightSide", colorSet);

		Variable openingRoof = new Variable("toit_ouvrant", booleanSet);
		Variable sono = new Variable("sono", booleanSet);

		HashSet<Constraint> allConstraint = new HashSet<>();
		HashSet<Variable> allVariable = new HashSet<>();

		HashSet<Variable> allEqualVariable = new HashSet<>();
		allEqualVariable.add(leftSide);
		allEqualVariable.add(rightSide);
		allEqualVariable.add(roofColor);


		allVariable.add(roofColor);
		allVariable.add(hoodColor);
		allVariable.add(tailgateColor);
		allVariable.add(leftSide);
		allVariable.add(rightSide);
		allVariable.add(openingRoof);
		allVariable.add(sono);

		Constraint allEq = new AllEqualConstraint(allEqualVariable);

		HashMap<Variable, String> premise = new HashMap<>();
		premise.put(roofColor, "red");
		HashMap<Variable, String> conclusion = new HashMap<>();
		conclusion.put(sono, "True");
		Constraint rule = new Rule(premise, conclusion);

		allConstraint.add(rule);
		allConstraint.add(allEq);

		Backtracking ppc = new Backtracking(allConstraint, allVariable);

		System.out.println("solution: " + ppc.solution(new HashMap<>()));
	}

}
