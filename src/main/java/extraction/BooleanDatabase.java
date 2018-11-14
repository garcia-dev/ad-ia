package extraction;

import representations.Variable;

import java.util.List;
import java.util.Map;

public class BooleanDatabase {
	private List<Variable> variableList;
	private List<Map<Variable, String>> transactionList;

	public BooleanDatabase(List<Variable> variableList, List<Map<Variable, String>> transactionList) {
		this.variableList = variableList;
		this.transactionList = transactionList;
	}

	public List<Variable> getVariableList() {
		return variableList;
	}

	public List<Map<Variable, String>> getTransactionList() {
		return transactionList;
	}

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
