package extraction;

import representations.Variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Powerset's class
 * <p>
 * The class Powerset is an utility class used to create a PowerSet from a list of Variable.
 * A PowerSet is a list of all the subSets that can be created from a Set/List.
 * </p>
 *
 * @author DORANGE Marin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-29
 * @see Variable
 */
abstract class Powerset {
	static List<Set<Variable>> calcPowerSet(List<Variable> variableList) {
		List<Set<Variable>> subSetList = new ArrayList<>();

		double subsetsNumber = Math.pow(2, variableList.size());

		for (int i = 1; i < subsetsNumber; i++) {
			Set<Variable> subset = new HashSet<>();

			for (int j = 0; j < variableList.size(); j++)
				if ((i & 1 << j) != 0)
					subset.add(variableList.get(j));

			subSetList.add(subset);
		}

		return subSetList;
	}
}