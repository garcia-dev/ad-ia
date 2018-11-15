import extraction.*;
import representations.Variable;

import java.io.IOException;
import java.util.*;

public class ExtractionDatabaseTest {
	private static final Set<String> BOOLEANS = new HashSet<>(Arrays.asList("0", "1"));

	private static final Set<String> COLORS = new HashSet<>(Arrays.asList("noir", "rouge", "blanc"));

	private static Variable LEFT_COLOR = new Variable("couleur_gauche", COLORS);
	private static Variable ROOF_COLOR = new Variable("couleur_toit", COLORS);
	private static Variable OPENING_ROOF = new Variable("toit_ouvrant", BOOLEANS);
	private static Variable HOOD_COLOR = new Variable("couleur_capot", COLORS);
	private static Variable SONO = new Variable("sono", BOOLEANS);
	private static Variable TAILGATE_COLOR = new Variable("couleur_hayon", COLORS);
	private static Variable RIGHT_COLOR = new Variable("couleur_droite", COLORS);

	public static void main(String[] args) {
		List<Variable> variableList = new ArrayList<>(List.of(LEFT_COLOR, ROOF_COLOR, OPENING_ROOF, HOOD_COLOR, SONO,
				TAILGATE_COLOR, RIGHT_COLOR));

		Database database = null;

		try {
			database = new DBReader(new HashSet<>(variableList)).importDB("src/main/resources/test_db.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}

		BooleanDatabase booleanDatabase = Objects.requireNonNull(database).translateToBoolean();

		AssociationRuleMiner associationRuleMiner =
				new AssociationRuleMiner(new FrequentItemsetMiner(booleanDatabase).frequentItemsets(0.5));

		Map<List<?>, List<Double>> associationRuleMap = associationRuleMiner.calcAssociationRule();

		associationRuleMap.forEach((key, value) -> System.out.println(key.get(0) + " -> " + key.get(1) +
				" - Frequency: " + value.get(0) + " - Trust: " + value.get(1)));
	}
}
