import extraction.AssociationRuleMiner;
import extraction.BooleanDatabase;
import extraction.FrequentItemsetMiner;
import representations.Variable;

import java.util.*;

class ExtractionSimpleAlgorithmText {

	/* Boolean domain */
	private static final Set<String> BOOLEANS = new HashSet<>(Arrays.asList("0", "1"));

	private static Variable JACKET = new Variable("Jacket", BOOLEANS);
	private static Variable JEANS = new Variable("Jeans", BOOLEANS);
	private static Variable SHIRT = new Variable("Shirt", BOOLEANS);
	private static Variable SHOES = new Variable("Shoes", BOOLEANS);
	private static Variable SWEAT_SHIRT = new Variable("Sweat-shirt", BOOLEANS);

	public static void main(String[] args) {

		/* Variables */
		List<Variable> variables = new ArrayList<>(List.of(JACKET, JEANS, SHIRT, SHOES, SWEAT_SHIRT));

		/* Assignments */
		Map<Variable, String> item1 = new LinkedHashMap<>();
		item1.put(JACKET, "1");
		item1.put(JEANS, "0");
		item1.put(SHIRT, "1");
		item1.put(SHOES, "1");
		item1.put(SWEAT_SHIRT, "0");

		Map<Variable, String> item2 = new LinkedHashMap<>();
		item2.put(JACKET, "1");
		item2.put(JEANS, "0");
		item2.put(SHIRT, "0");
		item2.put(SHOES, "1");
		item2.put(SWEAT_SHIRT, "0");

		Map<Variable, String> item3 = new LinkedHashMap<>();
		item3.put(JACKET, "0");
		item3.put(JEANS, "1");
		item3.put(SHIRT, "0");
		item3.put(SHOES, "1");
		item3.put(SWEAT_SHIRT, "0");

		Map<Variable, String> item4 = new LinkedHashMap<>();
		item4.put(JACKET, "0");
		item4.put(JEANS, "0");
		item4.put(SHIRT, "1");
		item4.put(SHOES, "0");
		item4.put(SWEAT_SHIRT, "1");

		/* Transactions */
		List<Map<Variable, String>> transactions = new ArrayList<>(List.of(item1, item2, item3, item4));

		AssociationRuleMiner associationRuleMiner =
				new AssociationRuleMiner(new FrequentItemsetMiner(
						new BooleanDatabase(variables, transactions)).frequentItemsets(0.5));

		Map<List<?>, List<Double>> associationRuleMap = associationRuleMiner.calcAssociationRule();

		associationRuleMap.forEach((key, value) -> System.out.println(key.get(0) + " -> " + key.get(1) +
				" - Frequency: " + value.get(0) + " - Trust: " + value.get(1)));
	}
}