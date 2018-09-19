package examples;

import representations.Variable;

import java.util.HashSet;
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
	}

}
