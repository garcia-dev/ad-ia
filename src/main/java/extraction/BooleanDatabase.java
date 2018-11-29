package extraction;

import representations.Variable;

import java.util.List;
import java.util.Map;

/**
 * BooleanDatabase's class
 * <p>
 * The class BooleanDatabase represents a boolean database.
 * </p>
 *
 * @author DORANGE Martin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-15
 * @see Variable
 */

public class BooleanDatabase extends Database {
	public BooleanDatabase(List<Variable> variableList, List<Map<Variable, String>> transactionList) {
		super(variableList, transactionList);
	}
}