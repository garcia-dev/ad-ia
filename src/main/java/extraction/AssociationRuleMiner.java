package extraction;

import representations.Variable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AssociationRuleMiner {
	private Map<Set<Variable>, Double> frequentItemsets;

	public AssociationRuleMiner(Map<Set<Variable>, Double> frequentItemsets) {
		this.frequentItemsets = frequentItemsets;
	}

	public Map<List<Variable>, List<Double>> calcAssociationRule() {
		Map<List<Variable>, List<Double>> associationRuleMap = new HashMap<>();

		frequentItemsets.forEach((key, value) -> {
			if (key.size() > 1) {
				ArrayList<Variable> keys = new ArrayList<>(key);

				associationRuleMap.put(keys, new ArrayList<>(Arrays.asList(value, value / frequentItemsets.get(Set.of(keys.get(0))))));

				associationRuleMap.put(IntStream.range(0, keys.size()).mapToObj(i -> keys.get(keys.size() - 1 - i)).collect(Collectors.toList()), new ArrayList<>(Arrays.asList(value, value / frequentItemsets.get(Set.of(keys.get(0))))));
			}
		});

		associationRuleMap.forEach((key, value) -> System.out.println(key));

		return associationRuleMap;
	}
}
