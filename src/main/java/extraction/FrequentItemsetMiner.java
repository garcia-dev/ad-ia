package extraction;

import representations.Variable;

import java.util.*;

public class FrequentItemsetMiner {
	private BooleanDatabase booleanDatabase;

	public FrequentItemsetMiner(BooleanDatabase booleanDatabase) {
		this.booleanDatabase = booleanDatabase;
	}

	public Map<Set<Variable>, Double> frequentItemsets(Double minimalSupport) {
		Map<Set<Variable>, Double> frequentItemset = new HashMap<>();

		// Adding every transaction as a frequentItemset
		booleanDatabase.getTransactionList().forEach(map -> {
			if (!frequentItemset.containsKey(map.keySet()))
				frequentItemset.put(map.keySet(), 1.0);
			else
				frequentItemset.put(map.keySet(), frequentItemset.get(map.keySet()) + 1);
		});

		// Adding every variable as a frequentItemset
		booleanDatabase.getVariableList().forEach(variable -> frequentItemset.put(
				new HashSet<>(Set.of(variable)), 0.0));

		// Counting the occurences of each key in frequentItemset and updating the value
		frequentItemset.forEach((key, value) -> frequentItemset.forEach((secondKey, secondValue) -> {
			if (secondKey != key && secondKey.containsAll(key))
				frequentItemset.put(key, frequentItemset.get(key) + 1);
		}));

		// Initializing a List of key that will be removed from frequentItemset
		List<Set<Variable>> keyToRemoveSet = new ArrayList<>();

		/*
			Updating the value of each key to its support value, if this value is under the minimalSupport value as
			parameter, it's added in the list of keys to be removed
		 */
		frequentItemset.forEach((key, value) -> {
			Double support = value / booleanDatabase.getTransactionList().size();

			if (support >= minimalSupport)
				frequentItemset.put(key, support);
			else
				keyToRemoveSet.add(key);
		});

		// Removing the keys with a lower support value than minimalSupport
		keyToRemoveSet.forEach(frequentItemset::remove);

		return frequentItemset;
	}
}
