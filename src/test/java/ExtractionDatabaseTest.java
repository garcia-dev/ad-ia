import extraction.*;
import representations.Variable;

import java.io.IOException;
import java.util.*;

public class ExtractionDatabaseTest {
	private static final Set<String> BOOLEANS = new HashSet<>(Arrays.asList("0", "1"));

	private static final Set<String> COLORS = new HashSet<>(Arrays.asList("noir", "rouge", "blanc"));

	// NON BOOLEAN VARIABLES
	private static Variable LEFT_COLOR = new Variable("couleur_gauche", COLORS);
	private static Variable ROOF_COLOR = new Variable("couleur_toit", COLORS);
	private static Variable OPENING_ROOF = new Variable("toit_ouvrant", BOOLEANS);
	private static Variable HOOD_COLOR = new Variable("couleur_capot", COLORS);
	private static Variable SONO = new Variable("sono", BOOLEANS);
	private static Variable TAILGATE_COLOR = new Variable("couleur_hayon", COLORS);
	private static Variable RIGHT_COLOR = new Variable("couleur_droite", COLORS);

	// BOOLEAN VARIABLES
	private static Variable LEFT_COLOR_BLACK = new Variable("couleur_gauche_noir", BOOLEANS);
	private static Variable LEFT_COLOR_RED = new Variable("couleur_gauche_rouge", BOOLEANS);
	private static Variable LEFT_COLOR_WHITE = new Variable("couleur_gauche_blanc", BOOLEANS);
	private static Variable ROOF_COLOR_BLACK = new Variable("couleur_toit_noir", BOOLEANS);
	private static Variable ROOF_COLOR_RED = new Variable("couleur_toit_rouge", BOOLEANS);
	private static Variable ROOF_COLOR_WHITE = new Variable("couleur_toit_blanc", BOOLEANS);
	private static Variable OPENING_ROOF_0 = new Variable("toit_ouvrant_0", BOOLEANS);
	private static Variable OPENING_ROOF_1 = new Variable("toit_ouvrant_1", BOOLEANS);
	private static Variable HOOD_COLOR_BLACK = new Variable("couleur_capot_noir", BOOLEANS);
	private static Variable HOOD_COLOR_RED = new Variable("couleur_capot_rouge", BOOLEANS);
	private static Variable HOOD_COLOR_WHITE = new Variable("couleur_capot_blanc", BOOLEANS);
	private static Variable SONO_0 = new Variable("sono_0", BOOLEANS);
	private static Variable SONO_1 = new Variable("sono_1", BOOLEANS);
	private static Variable TAILGATE_COLOR_BLACK = new Variable("couleur_hayon_noir", BOOLEANS);
	private static Variable TAILGATE_COLOR_RED = new Variable("couleur_hayon_rouge", BOOLEANS);
	private static Variable TAILGATE_COLOR_WHITE = new Variable("couleur_hayon_blanc", BOOLEANS);
	private static Variable RIGHT_COLOR_BLACK = new Variable("couleur_droite_noir", BOOLEANS);
	private static Variable RIGHT_COLOR_RED = new Variable("couleur_droite_rouge", BOOLEANS);
	private static Variable RIGHT_COLOR_WHITE = new Variable("couleur_droite_blanc", BOOLEANS);

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

//		associationRuleMap.forEach((key, value) -> System.out.println(key.get(0) + " -> " + key.get(1) +
//				" - Frequency: " + value.get(0) + " - Trust: " + value.get(1)));

		List<Variable> booleanVariableList = new ArrayList<>(List.of(LEFT_COLOR_BLACK, LEFT_COLOR_RED,
				LEFT_COLOR_WHITE, ROOF_COLOR_BLACK, ROOF_COLOR_RED, ROOF_COLOR_WHITE, OPENING_ROOF_0, OPENING_ROOF_1,
				HOOD_COLOR_BLACK, HOOD_COLOR_RED, HOOD_COLOR_WHITE, SONO_0, SONO_1, TAILGATE_COLOR_BLACK,
				TAILGATE_COLOR_RED, TAILGATE_COLOR_WHITE, RIGHT_COLOR_BLACK, RIGHT_COLOR_RED, RIGHT_COLOR_WHITE));

		BooleanDatabase booleanDatabase1 = new BooleanDBReader(booleanVariableList)
				.readDB("src/main/resources/test_db.csv");

		System.out.println(booleanDatabase1.toString());
	}
}
