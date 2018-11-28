package extraction;

import representations.Variable;

import java.util.List;
import java.util.Map;

/**
 * BooleanDatabase's class
 * <p>
 * The BooleanDatabase's class is a class representing a boolean database.
 *
 * @author DORANGE Martin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-15
 * @see Variable
 */
public class BooleanDatabase {
	private List<Variable> variableList;
	private List<Map<Variable, String>> transactionList;

	public BooleanDatabase(List<Variable> variableList, List<Map<Variable, String>> transactionList) {
		this.variableList = variableList;
		this.transactionList = transactionList;
	}

	/**
	 * Getter of the Transaction list affiliated to the BooleanDatabase.
	 *
	 * @return the Transaction list affiliated to the BooleanDatabase
	 */
	List<Map<Variable, String>> getTransactionList() {
		return transactionList;
	}

	public List<Variable> getVariableList() {
		return variableList;
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
