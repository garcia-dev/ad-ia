package extraction;

import representations.Variable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * AssociationRuleMiner's class
 * <p>
 * The class AssociationRuleMiner generates the association rules based on the Frequent Items.
 * </p>
 *
 * @author DORANGE Martin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-20
 * @see Variable
 */
public class AssociationRuleMiner {
	private final Map<Set<Variable>, Double> frequentItemsets;

	public AssociationRuleMiner(Map<Set<Variable>, Double> frequentItemsets) {
		this.frequentItemsets = frequentItemsets;
	}

	/**
	 * Method calculating every association rules possible.
	 *
	 * @return a map of association rules
	 */
	public Map<List<?>, List<Double>> calcAssociationRule(double minimalTrust) {
		Map<List<?>, List<Double>> associationRuleMap = new HashMap<>();

		List<List<Variable>> keyList = new ArrayList<>();

		// An association rule needs 2 variables so we are only working with sets bigger than 1 variable
		frequentItemsets.forEach((key, value) -> {
			if (key.size() > 1)
				keyList.add(new ArrayList<>(key));
		});

		for (List<Variable> key : keyList) {
			// Generating every subsets
			List<Set<Variable>> subSetList = Powerset.calcPowerSet(key);

			// Generating pairs, calculating the Trust value and putting it into the map
			for (int i = 0; i < subSetList.size(); i++)
				for (int j = i + 1; j < subSetList.size(); j++)
					// If there is no duplicate, then we're calculating the association rules
					if (!subSetList.get(i).containsAll(subSetList.get(j)) &&
							!subSetList.get(j).containsAll(subSetList.get(i)) &&
							Collections.disjoint(subSetList.get(i), subSetList.get(j))) {
						List<Set<Variable>> variableSetList =
								new ArrayList<>(Arrays.asList(subSetList.get(i), subSetList.get(j)));

						Set<Variable> test = new HashSet<>();
						test.addAll(subSetList.get(i));
						test.addAll(subSetList.get(j));

						/*
							Adding the pair into the map, with its Frequency and Trust value if Trust value is higher
						    than minimalTrust
						 */
						double trust = frequentItemsets.get(test) / frequentItemsets.get(subSetList.get(j));
						if (trust >= minimalTrust)
							associationRuleMap.put(variableSetList, Arrays.asList(frequentItemsets.get(test), trust));

						/*
							Adding the pair reverse into the map, with its Frequency and Trust value if Trust value
							is higher than minimalTrust
						 */
						trust = frequentItemsets.get(test) / frequentItemsets.get(subSetList.get(j));
						if (trust >= minimalTrust)
							associationRuleMap.put(IntStream.range(0,
									variableSetList.size()).mapToObj(s ->
											variableSetList.get(variableSetList.size() - 1 - s))
											.collect(Collectors.toList()),
									Arrays.asList(frequentItemsets.get(test), trust));
					}
		}

		return associationRuleMap;
	}
}
