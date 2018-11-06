import examples.Examples;
import extraction.BooleanDatabase;
import extraction.FrequentItemsetMiner;
import representations.Variable;

import java.util.*;

public class ExtractionTests {

	/* Boolean domain */
	private static final Set<String> BOOLEANS =
			new HashSet<>(Arrays.asList("true", "false"));

	public static Variable JACKET = new Variable("Jacket", BOOLEANS);
	public static Variable JEANS = new Variable("Jeans", BOOLEANS);
	public static Variable SHIRT = new Variable("Shirt", BOOLEANS);
	public static Variable SHOES = new Variable("Shoes", BOOLEANS);
	public static Variable SWEAT_SHIRT = new Variable("Sweat-shirt", BOOLEANS);

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
		item1.put(SHOES, "true");
		item1.put(SHIRT, "true");
		item1.put(JACKET, "true");

		Map<Variable, String> item2 = new HashMap<>();
		item2.put(SHOES, "true");
		item2.put(JACKET, "true");

		Map<Variable, String> item3 = new HashMap<>();
		item3.put(SHOES, "true");
		item3.put(JEANS, "true");

		Map<Variable, String> item4 = new HashMap<>();
		item4.put(SHIRT, "true");
		item4.put(SWEAT_SHIRT, "true");

		/* Transactions */
		List<Map<Variable, String>> transactions = new ArrayList<>();
		transactions.add(item1);
		transactions.add(item2);
		transactions.add(item3);
		transactions.add(item4);

		BooleanDatabase booleanDatabase =
				new BooleanDatabase(variables, transactions);

		FrequentItemsetMiner frequentItemsetMiner =
				new FrequentItemsetMiner(booleanDatabase);


	}

}
