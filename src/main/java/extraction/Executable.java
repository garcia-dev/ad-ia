package extraction;

import representations.Variable;

import java.util.*;

/**
 * Executable's class
 * <p>
 * The class Executable is the executable of the package 'extraction' which reads a Database from a text file and
 * translates it into a Boolean Database.
 * </p>
 *
 * @author DORANGE Marin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-15
 * @see Variable
 * @see BooleanDatabase
 * @see BooleanDBReader
 * @see Database
 */
public class Executable {
	// BOOLEAN DOMAIN
	private static final Set<String> BOOLEANS = new HashSet<>(Arrays.asList("0", "1"));

	// BOOLEAN VARIABLES
	private static final Variable LEFT_COLOR_BLACK = new Variable("couleur_gauche_noir", BOOLEANS);
	private static final Variable LEFT_COLOR_RED = new Variable("couleur_gauche_rouge", BOOLEANS);
	private static final Variable LEFT_COLOR_WHITE = new Variable("couleur_gauche_blanc", BOOLEANS);
	private static final Variable ROOF_COLOR_BLACK = new Variable("couleur_toit_noir", BOOLEANS);
	private static final Variable ROOF_COLOR_RED = new Variable("couleur_toit_rouge", BOOLEANS);
	private static final Variable ROOF_COLOR_WHITE = new Variable("couleur_toit_blanc", BOOLEANS);
	private static final Variable OPENING_ROOF_0 = new Variable("toit_ouvrant_0", BOOLEANS);
	private static final Variable OPENING_ROOF_1 = new Variable("toit_ouvrant_1", BOOLEANS);
	private static final Variable HOOD_COLOR_BLACK = new Variable("couleur_capot_noir", BOOLEANS);
	private static final Variable HOOD_COLOR_RED = new Variable("couleur_capot_rouge", BOOLEANS);
	private static final Variable HOOD_COLOR_WHITE = new Variable("couleur_capot_blanc", BOOLEANS);
	private static final Variable SONO_0 = new Variable("sono_0", BOOLEANS);
	private static final Variable SONO_1 = new Variable("sono_1", BOOLEANS);
	private static final Variable TAILGATE_COLOR_BLACK = new Variable("couleur_hayon_noir", BOOLEANS);
	private static final Variable TAILGATE_COLOR_RED = new Variable("couleur_hayon_rouge", BOOLEANS);
	private static final Variable TAILGATE_COLOR_WHITE = new Variable("couleur_hayon_blanc", BOOLEANS);
	private static final Variable RIGHT_COLOR_BLACK = new Variable("couleur_droite_noir", BOOLEANS);
	private static final Variable RIGHT_COLOR_RED = new Variable("couleur_droite_rouge", BOOLEANS);
	private static final Variable RIGHT_COLOR_WHITE = new Variable("couleur_droite_blanc", BOOLEANS);

	private static final List<Variable> BOOLEAN_VARIABLE_LIST = new ArrayList<>(Arrays.asList(LEFT_COLOR_BLACK,
			LEFT_COLOR_RED, LEFT_COLOR_WHITE, ROOF_COLOR_BLACK, ROOF_COLOR_RED, ROOF_COLOR_WHITE, OPENING_ROOF_0,
			OPENING_ROOF_1, HOOD_COLOR_BLACK, HOOD_COLOR_RED, HOOD_COLOR_WHITE, SONO_0, SONO_1, TAILGATE_COLOR_BLACK,
			TAILGATE_COLOR_RED, TAILGATE_COLOR_WHITE, RIGHT_COLOR_BLACK, RIGHT_COLOR_RED, RIGHT_COLOR_WHITE));

	public static void main(String[] args) {
		assert (args.length == 3) : "Incorrect arguments number";

		BooleanDatabase booleanDatabase = new BooleanDBReader(BOOLEAN_VARIABLE_LIST).readDB(args[0]);

		Map<List<?>, List<Double>> associationRuleMap = new AssociationRuleMiner(
				new FrequentItemsetMiner(booleanDatabase)
						.frequentItemsets(Double.valueOf(args[1]))).calcAssociationRule(Double.parseDouble(args[2]));

		associationRuleMap.forEach((key, value) -> System.out.println(key.get(0) + " -> " + key.get(1) +
				" - Frequency: " + value.get(0) + " - Trust: " + value.get(1)));
	}
}
