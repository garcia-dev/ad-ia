package examples;

import representations.AllEqualConstraint;
import representations.Rule;
import representations.Variable;

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

		/* Colors */

		Variable roofColor = new Variable("roofColor", colorSet);
		Variable hoodColor = new Variable("hoodColor", colorSet);
		Variable tailgateColor = new Variable("tailgateColor", colorSet);

		Variable leftSide = new Variable("leftSide", colorSet);
		Variable rightSide = new Variable("rightSide", colorSet);

		/* Options */

		Variable openingRoof = new Variable("openingRoof", booleanSet);
		Variable sono = new Variable("sono", booleanSet);

		/* Cars */

		Set<Variable> sameColorElements = new HashSet<>();
		sameColorElements.add(roofColor);
		sameColorElements.add(hoodColor);
		sameColorElements.add(tailgateColor);

		AllEqualConstraint allEqualConstraint = new AllEqualConstraint(sameColorElements);


		Map<Variable, String> roofRule1 = new HashMap<>();
		roofRule1.put(roofColor, "black");

		Map<Variable, String> sidesRule1 = new HashMap<>();
		sidesRule1.put(leftSide, roofRule1.get(roofColor));
		sidesRule1.put(rightSide, roofRule1.get(roofColor));

		Rule roofLikeOneSide = new Rule(roofRule1, sidesRule1);


		Map<Variable, String> car1 = new HashMap<>();
		car1.put(roofColor, "black");
		car1.put(hoodColor, "black");
		car1.put(tailgateColor, "black");

		Map<Variable, String> car2 = new HashMap<>();
		car2.put(roofColor, "black");
		car2.put(hoodColor, "blue");
		car2.put(tailgateColor, "black");

		System.out.println(allEqualConstraint.isSatisfiedBy(car1));
		System.out.println(!allEqualConstraint.isSatisfiedBy(car2));
	}

}
