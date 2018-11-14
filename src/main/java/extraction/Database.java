package extraction;

import representations.Variable;

import java.util.*;

public class Database {
	private List<Variable> variableList;
	private List<Map<Variable, String>> transactionList;

	public Database(List<Variable> variableList, List<Map<Variable, String>> transactionList) {
		this.variableList = variableList;
		this.transactionList = transactionList;
	}

	public BooleanDatabase translateToBoolean() {
		List<Variable> booleanVariableList = new ArrayList<>();
		List<Map<Variable, String>> booleanTransactionList = new ArrayList<>();

		variableList.forEach(variable -> variable.getDomain().forEach(value -> booleanVariableList.add(
				new Variable(variable.getName() + "_" + value, Set.of("0", "1")))));

		transactionList.forEach(transaction -> {
			Map<Variable, String> booleanTransactionMap = new LinkedHashMap<>();

			transaction.forEach(((variable, value) -> booleanVariableList.forEach(booleanVariable -> {
				if (booleanVariable.getName().equals(variable.getName() + "_" + value))
					booleanTransactionMap.put(booleanVariable, "1");
			})));

			booleanVariableList.forEach(variable -> {
				if (!booleanTransactionMap.containsKey(variable))
					booleanTransactionMap.put(variable, "0");
			});

			booleanTransactionList.add(booleanTransactionMap);
		});

		return new BooleanDatabase(booleanVariableList, booleanTransactionList);
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
