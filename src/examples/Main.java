package examples;

import ppc.BackTracking;
import representations.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

		// Colors

		Variable roofColor = new Variable("couleur_toit", colorSet);
		Variable hoodColor = new Variable("couleur_capot", colorSet);
		Variable tailgateColor = new Variable("couleur_hayon", colorSet);

		Variable leftSide = new Variable("couleur_gauche", colorSet);
		Variable rightSide = new Variable("couleur_droit", colorSet);

		// Options

		Variable openingRoof = new Variable("toit_ouvrant", booleanSet);
		Variable sono = new Variable("sono", booleanSet);

		// Putting every variables into a Set
		Set<Variable> variableSet = new HashSet<>();
		variableSet.add(roofColor);
		variableSet.add(hoodColor);
		variableSet.add(tailgateColor);
		variableSet.add(leftSide);
		variableSet.add(rightSide);
		variableSet.add(openingRoof);
		variableSet.add(sono);

		Set<Variable> equalVariableSet = new HashSet<>();
		equalVariableSet.add(roofColor);
		equalVariableSet.add(hoodColor);
		equalVariableSet.add(tailgateColor);
		equalVariableSet.add(leftSide);
		equalVariableSet.add(rightSide);

		Map<Variable,String> disjunctionSet=new HashMap();
		disjunctionSet.put(hoodColor,"blue");
		disjunctionSet.put(rightSide,"red");

		Constraint dis=new Disjunction(disjunctionSet);

		Constraint allEq = new AllEqualConstraint(equalVariableSet);

		HashMap<Variable, String> premise = new HashMap<>();
		premise.put(roofColor, "red");
		HashMap<Variable, String> conclusion = new HashMap<>();
		conclusion.put(hoodColor, "blue");
		Constraint rule = new Rule(premise, conclusion);

		HashMap<Variable, String> varIC = new HashMap<>();
		varIC.put(sono, "True");
		varIC.put(openingRoof, "True");
		Constraint incompatibilityConstraint = new IncompatibilityConstraint(varIC);

		Set<Constraint> constraintSet = new HashSet<>();
		//constraintSet.add(rule);
		constraintSet.add(dis);
		constraintSet.add(allEq);
		constraintSet.add(incompatibilityConstraint);
		int sol=0;
		BackTracking ppc = new BackTracking(constraintSet, variableSet);
		 HashMap<Variable, String> car = ppc.solution();
		while(car!=null){
			sol++;
			printCar(car);
			System.out.println(dis.isSatisfiedBy(car));
			System.out.println(allEq.isSatisfiedBy(car));
			System.out.println(incompatibilityConstraint.isSatisfiedBy(car));

			System.out.println();
			car=ppc.solution();
		}
		System.out.println(sol);
		System.out.println(ppc.notUsed);
	}

	public static void printCar(Map<Variable, String> car) {
		System.out.println("solution => ");
		car.forEach((key, value) -> System.out.println(key.getName() + " : " + value));

	}
}
