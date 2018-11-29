import diagnosis.Diagnoser;
import example.Examples;

import java.util.HashMap;

public class DiagnosisTests {

	public static void main(String[] args) {

		/* Diagnoser */

		Diagnoser diagnoser1 = new Diagnoser(new HashMap<>(), Examples.getConstraints());
		diagnoser1.add(Examples.HOOD_COLOR, "red");
		diagnoser1.add(Examples.RIGHT_SIDE_COLOR, "black");
		diagnoser1.add(Examples.TAILGATE_COLOR, "red");

		//System.out.println(diagnoser1.isExplanation(diagnoser1.getVariables(), Examples.LEFT_SIDE_COLOR, "black"));
		System.out.println("Diagnoser explanation: "
								   + diagnoser1.explanation(Examples.LEFT_SIDE_COLOR, "black"));

	}

}
