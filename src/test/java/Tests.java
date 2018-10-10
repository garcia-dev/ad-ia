import examples.Examples;
import representations.Variable;

import java.util.HashMap;

public class Tests {

	public static void main(String[] args) {

		Examples examples = new Examples();

		/* Cars */

		/* Car whose roof, hood and tailgate are the same color */
		HashMap<Variable, String> car1 = new HashMap<>();
		car1.put(Examples.roofColor, "black");
		car1.put(Examples.hoodColor, "black");
		car1.put(Examples.tailgateColor, "black");

		/* Car whose at least one side is the same color as the roof */
		HashMap<Variable, String> car2 = new HashMap<>();
		car2.put(Examples.roofColor, "black");
		car2.put(Examples.leftSideColor, "black");
		car2.put(Examples.rightSideColor, "blue");

		/* Car whose sides are not both black */
		HashMap<Variable, String> car3 = new HashMap<>();
		car3.put(Examples.leftSideColor, "black");
		car3.put(Examples.rightSideColor, "blue");

		/*
		 * Car which doesn't have an opening roof and a sound system at the same
		 * time
		 */
		HashMap<Variable, String> car4 = new HashMap<>();
		car4.put(Examples.hasOpeningRoof, "true");
		car4.put(Examples.hasSono, "false");

		/* Tests */
		if (examples.getConstraint1().isSatisfiedBy(car1)) {
			System.out.println("The first car satisfies the first constraint.");
		} else {
			System.out.println("There is something wrong with the first "
							   + "constraint.");
		}

		if (examples.getConstraint2().isSatisfiedBy(car2)) {
			System.out.println("The second car satisfies the second constraint.");
		} else {
			System.out.println("There is something wrong with the second "
							   + "constraint.");
		}

		if (examples.getConstraint3().isSatisfiedBy(car3)) {
			System.out.println("The third car satisfies the third constraint.");
		} else {
			System.out.println("There is something wrong with the third "
							   + "constraint.");
		}

		if (examples.getConstraint4().isSatisfiedBy(car4)) {
			System.out.println("The fourth car satisfies the fourth constraint.");
		} else {
			System.out.println("There is something wrong with the fourth "
							   + "constraint.");
		}
	}

}
