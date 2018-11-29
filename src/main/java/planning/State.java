package planning;

import representations.Variable;

import java.util.LinkedHashMap;

/**
 * State's class
 * <p>
 * The class State represents an assignment of Variable and their value.
 * </p>
 *
 * @author DORANGE Marin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-27
 * @see Variable
 */
public class State extends LinkedHashMap<Variable, String> {
	/**
	 * Method checking if the current State satisfies the given State.
	 * <p>
	 * A State satisfies another State when it contains all of the Variable of the given State.
	 * </p>
	 *
	 * @param state the State to check the satisfaction
	 * @return True if the current State satisfies the given one
	 */
	boolean satisfies(State state) {
		for (Variable variable : state.keySet())
			if (!keySet().contains(variable) || !get(variable).equals(state.get(variable)))
				return false;

		return true;
	}
}
