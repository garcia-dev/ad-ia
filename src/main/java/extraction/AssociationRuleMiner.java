package extraction;

import representations.Variable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * AssociationRuleMiner's class
 * <p>
 * The AssociationRuleMiner's class is a class generating the association rules based on the Frequent Items.
 * </p>
 *
 * @author DORANGE Martin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-15
 * @see Variable
 */

public class AssociationRuleMiner {
	private Map<Set<Variable>, Double> frequentItemsets;

	public AssociationRuleMiner(Map<Set<Variable>, Double> frequentItemsets) {
		this.frequentItemsets = frequentItemsets;
	}

	/**
	 * The method calculating every association rules possible.
	 *
	 * @return a map of assocation rules
	 */
	public Map<List<?>, List<Double>> calcAssociationRule() {
		Map<List<?>, List<Double>> associationRuleMap = new HashMap<>();

		List<List<Variable>> keyList = new ArrayList<>();

		// An association rule needs 2 variables so we are only working with sets bigger than 1 variable
		frequentItemsets.forEach((key, value) -> {
			if (key.size() > 1)
				keyList.add(new ArrayList<>(key));
		});

		for (List<Variable> key : keyList) {
			// Generating every subsets
			List<Set<Variable>> subsetsList = new ArrayList<>();

			double subsetsNumber = Math.pow(2, key.size());

			for (int i = 1; i < subsetsNumber; i++) {
				Set<Variable> subset = new HashSet<>();

				for (int j = 0; j < key.size(); j++)
					if ((i & (1 << j)) != 0)
						subset.add(key.get(j));

				if (subset.size() < key.size())
					subsetsList.add(subset);
			}

			// Generating pairs, calculating the Trust value and putting it into the map
			for (int i = 0; i < subsetsList.size(); i++)
				for (int j = i + 1; j < subsetsList.size(); j++)
					// Checking if there is no duplicate
					if (!subsetsList.get(i).containsAll(subsetsList.get(j)) &&
							!subsetsList.get(j).containsAll(subsetsList.get(i)) &&
							Collections.disjoint(subsetsList.get(i), subsetsList.get(j))) {
						List<Set<Variable>> variableSetList =
								new ArrayList<>(List.of(subsetsList.get(i), subsetsList.get(j)));

						Set<Variable> test = new HashSet<>();
						test.addAll(subsetsList.get(i));
						test.addAll(subsetsList.get(j));

						// Adding the pair into the map, with its Frequency and Trust value
						associationRuleMap.put(variableSetList,
								List.of(frequentItemsets.get(test),
										frequentItemsets.get(test) / frequentItemsets.get(subsetsList.get(i))));

						// Adding the pair reverse into the map, with its Frequency and Trust value
						associationRuleMap.put(IntStream.range(0,
								variableSetList.size()).mapToObj(s ->
										variableSetList.get(variableSetList.size() - 1 - s))
										.collect(Collectors.toList()),
								List.of(frequentItemsets.get(test),
										frequentItemsets.get(test) / frequentItemsets.get(subsetsList.get(j))));
					}
		}

		return associationRuleMap;
	}
}
