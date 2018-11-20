import diagnosis.Diagnoser;
import examples.Examples;
import representations.Variable;

import java.util.*;

public class DiagnosisTests {

	public static void main(String[] args) {

		/* Choices assignments */

		Diagnoser diagnoser = new Diagnoser(new HashMap<>(), Examples.getConstraints());
		diagnoser.add(Examples.HOOD_COLOR, "red");
		diagnoser.add(Examples.RIGHT_SIDE_COLOR, "black");
		diagnoser.add(Examples.TAILGATE_COLOR, "red");

		//System.out.println(diagnoser.isExplanation(diagnoser.getVariables(), Examples.LEFT_SIDE_COLOR, "black"));
		System.out.println(diagnoser.explanation(Examples.LEFT_SIDE_COLOR, "black"));

	}

}
