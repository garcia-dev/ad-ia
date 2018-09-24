package examples;

import representations.*;
import ppc.*;

import java.util.HashSet;
import java.util.HashMap;
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

		/* Colors */

		Variable roofColor = new Variable("couleur_toit", colorSet);
		Variable hoodColor = new Variable("couleur_capot", colorSet);
		Variable tailgateColor = new Variable("couleur_hayon", colorSet);

		Variable leftSide = new Variable("couleur_gauche", colorSet);
		Variable rightSide = new Variable("couleur_droit", colorSet);

		/* Options */

		Variable openingRoof = new Variable("toit_ouvrant", booleanSet);
		Variable sono = new Variable("sono", booleanSet);
		
		HashSet<Constraint>  allConstraint=new HashSet();
		HashSet<Variable>  allVariable=new HashSet();
		
		HashSet<Variable>  allEqualVariable=new HashSet();
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
		
		Constraint allEq=new AllEqualConstraint(allEqualVariable);
		
		HashMap<Variable,String> premise=new HashMap();
		premise.put(roofColor, "red");
		HashMap<Variable,String> conclusion=new HashMap();
		conclusion.put(sono, "True");
		Constraint rule=new Rule(premise,conclusion);
		
		allConstraint.add(rule);
		allConstraint.add(allEq);
		
		BackTracking ppc=new BackTracking(allConstraint,allVariable);
		
		System.out.println("solution: "+ppc.solution(new HashMap()));
	}

}
