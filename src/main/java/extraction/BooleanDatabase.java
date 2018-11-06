package main.java.extraction;

import main.java.representations.Variable;

import java.util.List;
import java.util.Map;

class BooleanDatabase {
	private List<Variable> variableList;
	private List<Map<Variable, String>> transactionList;

	public BooleanDatabase(List<Variable> variableList, List<Map<Variable, String>> transactionList) {
		this.variableList = variableList;
		this.transactionList = transactionList;
	}
}
