package extraction;

import representations.Variable;

import java.util.*;

public class FrequentItemsetMiner {

	private BooleanDatabase booleanDatabase;

	public FrequentItemsetMiner(BooleanDatabase booleanDatabase) {
		this.booleanDatabase = booleanDatabase;
	}

	public Map<Set<Variable>, Double> frequentItemsets(Double minimalSupport) {
		Map<Set<Variable>, Double> frequentItemsets = new HashMap<>();

		// Add every transaction as a frequentItemsets
		booleanDatabase.getTransactions().forEach(transaction -> {
			Set<Variable> itemSet = transaction.keySet();
			if (!frequentItemsets.containsKey(itemSet))
				frequentItemsets.put(itemSet, 1.0);
			else {
				Double occurrences = frequentItemsets.get(transaction.keySet()) + 1;
				frequentItemsets.put(transaction.keySet(), occurrences);
			}
		});

		// Add every variable as a frequentItemsets
		booleanDatabase.getVariables().forEach(variable -> frequentItemsets.put(new HashSet<>(Collections.singleton(variable)), 0.0));

		// Count the occurrences of each key in frequentItemsets and updating the value
		frequentItemsets.forEach((key, value) -> frequentItemsets.forEach((secondKey, secondValue) -> {
			if (secondKey != key && secondKey.containsAll(key)) {
				Double occurences = frequentItemsets.get(key) + 1;
				frequentItemsets.put(key, occurences);
			}
		}));

		// Initialize a list of keys that will be removed from frequentItemsets
		List<Set<Variable>> keysToRemoveSet = new ArrayList<>();

		/*
		 * Update the value of each key to its support value, if this value is
		 * under the minimalSupport value as parameter, it's added in the list
		 * of keys to be removed
		 */
		frequentItemsets.forEach((key, value) -> {
			Double support = value / booleanDatabase.getTransactions().size();

			if (support >= minimalSupport) {
				frequentItemsets.put(key, support);
			} else {
				keysToRemoveSet.add(key);
			}
		});

		// Remove the keys with a lower support value than minimalSupport
		keysToRemoveSet.forEach(frequentItemsets::remove);

		return frequentItemsets;
	}

}