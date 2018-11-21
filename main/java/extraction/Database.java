package extraction;

import representations.Variable;

import java.util.*;

/**
 * Database's class
 * <p>
 * The Database's class is a class representing a database.
 * </p>
 *
 * @author DORANGE Martin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-15
 * @see Variable
 */

public class Database {
	private List<Variable> variableList;
	private List<Map<Variable, String>> transactionList;

	Database(List<Variable> variableList, List<Map<Variable, String>> transactionList) {
		this.variableList = variableList;
		this.transactionList = transactionList;
	}

	/**
	 * Method translating the non-boolean database to a boolean database.
	 *
	 * @return a BooleanDatabase version of the Database
	 */
	public BooleanDatabase translateToBoolean() {
		List<Variable> booleanVariableList = new ArrayList<>();
		List<Map<Variable, String>> booleanTransactionList = new ArrayList<>();

		// Creating the boolean variables based on the variable + their value
		variableList.forEach(variable -> variable.getDomain().forEach(value -> booleanVariableList.add(
				new Variable(variable.getName() + "_" + value, new HashSet(Arrays.asList("0", "1"))))));

		// Creating the booleanTransactionList
		transactionList.forEach(transaction -> {
			Map<Variable, String> booleanTransactionMap = new LinkedHashMap<>();

			// For each variable in the list of boolean variables, we initialize it with a frequency of 0
			booleanVariableList.forEach(variable -> booleanTransactionMap.put(variable, "0"));

			// For each variable in transaction, we're changing the value of this variable to 1
			transaction.forEach((variable, value) -> booleanVariableList.forEach(booleanVariable -> {
				if (booleanVariable.getName().equals(variable.getName() + "_" + value))
					booleanTransactionMap.put(booleanVariable, "1");
			}));

			// Adding the generated map to the list of booleanTransactionMap
			booleanTransactionList.add(booleanTransactionMap);
		});

		return new BooleanDatabase(booleanVariableList, booleanTransactionList);
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();

		transactionList.get(0).forEach(((variable, value) -> res.append(variable.getName()).append(";")));

		res.append("\n");

		transactionList.forEach(transaction -> {
			transaction.forEach((variable, value) -> res.append(value).append(";"));

			res.append("\n");
		});

		return res.toString();
	}
}
