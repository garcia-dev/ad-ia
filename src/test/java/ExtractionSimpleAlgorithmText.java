import extraction.AssociationRuleMiner;
import extraction.BooleanDatabase;
import extraction.FrequentItemsetMiner;
import representations.Variable;

import java.util.*;

class ExtractionSimpleAlgorithmText {

	/* Boolean domain */
	private static final Set<String> BOOLEANS =
			new HashSet<>(Arrays.asList("0", "1s"));

	private static Variable JACKET = new Variable("Jacket", BOOLEANS);
	private static Variable JEANS = new Variable("Jeans", BOOLEANS);
	private static Variable SHIRT = new Variable("Shirt", BOOLEANS);
	private static Variable SHOES = new Variable("Shoes", BOOLEANS);
	private static Variable SWEAT_SHIRT = new Variable("Sweat-shirt", BOOLEANS);

	public static void main(String[] args) {

		/* Variables */
		List<Variable> variables = new ArrayList<>();
		variables.add(JACKET);
		variables.add(JEANS);
		variables.add(SHIRT);
		variables.add(SHOES);
		variables.add(SWEAT_SHIRT);

		/* Assignments */
		Map<Variable, String> item1 = new HashMap<>();
		item1.put(SHOES, "1");
		item1.put(SHIRT, "1");
		item1.put(JACKET, "1");

		Map<Variable, String> item2 = new HashMap<>();
		item2.put(SHOES, "1");
		item2.put(JACKET, "1");

		Map<Variable, String> item3 = new HashMap<>();
		item3.put(SHOES, "1");
		item3.put(JEANS, "1");

		Map<Variable, String> item4 = new HashMap<>();
		item4.put(SHIRT, "1");
		item4.put(SWEAT_SHIRT, "1");

		/* Transactions */
		List<Map<Variable, String>> transactions = new ArrayList<>();
		transactions.add(item1);
		transactions.add(item2);
		transactions.add(item3);
		transactions.add(item4);

		AssociationRuleMiner associationRuleMiner =
				new AssociationRuleMiner(new FrequentItemsetMiner(
						new BooleanDatabase(variables, transactions)).frequentItemsets(0.5));

		Map<List<Variable>, List<Double>> associationRuleMap = associationRuleMiner.calcAssociationRule();

		associationRuleMap.forEach((key, value) -> System.out.println(key.get(0) + " -> " + key.get(1) +
				" - Frequency: " + value.get(0) + " - Trust: " + value.get(1)));
	}
}