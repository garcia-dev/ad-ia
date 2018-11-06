package extraction;

import representations.Variable;

import java.util.List;
import java.util.Map;

public class BooleanDatabase {
	private List<Variable> variables;
	private List<Map<Variable, String>> transactions;

	public BooleanDatabase(List<Variable> variables, List<Map<Variable, String>> transactions) {
		this.variables = variables;
		this.transactions = transactions;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public List<Map<Variable, String>> getTransactions() {
		return transactions;
	}
}