package extraction;

import representations.Variable;

import java.util.List;
import java.util.Map;

public class Database {

	private List<Variable> variables;
	private List<Map<Variable, String>> transactions;

	public Database(List<Variable> variables, List<Map<Variable, String>> transactions) {
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
