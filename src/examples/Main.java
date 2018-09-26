package examples;

import ppc.BackTracking;
import representations.*;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Set<String> colorSet = new HashSet<>();
		colorSet.add("black");
		colorSet.add("blue");
		colorSet.add("green");
		colorSet.add("red");

		Set<String> booleanSet = new HashSet<>();
		booleanSet.add("True");
		booleanSet.add("False");

		Set<Variable> allVariable = new HashSet<>();
		Set<Constraint> allConstraint = new HashSet<>();
		// Colors

		Variable roofColor = new Variable("couleur_toit", colorSet);
		Variable hoodColor = new Variable("couleur_capot", colorSet);
		Variable tailgateColor = new Variable("couleur_hayon", colorSet);

		Variable leftSide = new Variable("couleur_gauche", colorSet);
		Variable rightSide = new Variable("couleur_droit", colorSet);

		/* Options */

		Variable openingRoof = new Variable("toit_ouvrant", booleanSet);
		Variable sono = new Variable("sono", booleanSet);
		allVariable.add(roofColor);
		allVariable.add(hoodColor);
		allVariable.add(tailgateColor);
		allVariable.add(leftSide);
		allVariable.add(rightSide);
		allVariable.add(openingRoof);
		allVariable.add(sono);

		Set<Variable> allEqualVariable = new HashSet<>();
		allEqualVariable.add(roofColor);
		allEqualVariable.add(hoodColor);

		Constraint allEq = new AllEqualConstraint(allEqualVariable);

		HashMap<Variable, String> premise = new HashMap<>();
		premise.put(roofColor, "red");
		HashMap<Variable, String> conclusion = new HashMap<>();
		conclusion.put(hoodColor, "blue");
		Constraint rule = new Rule(premise, conclusion);

		HashMap<Variable, String> varIC = new HashMap<>();
		varIC.put(sono, "True");
		varIC.put(openingRoof, "True");
		Constraint incompatibilityConstraint = new IncompatibilityConstraint(varIC);

		allConstraint.add(rule);
		allConstraint.add(allEq);
		allConstraint.add(incompatibilityConstraint);

		BackTracking ppc = new BackTracking(allConstraint, allVariable);
		HashMap<Variable, String> car = ppc.solution(new HashMap<>(), 0);

		System.out.println("solution: ");
		printCar(car);
	}

	private static void printCar(Map<Variable, String> car) {
		car.forEach((key, value) -> System.out.println(key.getName() + " : " + value));
	}
}
