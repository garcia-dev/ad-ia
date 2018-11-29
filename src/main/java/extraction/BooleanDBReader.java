package extraction;

import representations.Variable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * BooleanDBReader's class
 * <p>
 * The class BooleanDBReader reads a database under csv format and creates its boolean database.
 * </p>
 *
 * @author DORANGE Martin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-15
 * @see Variable
 */

public class BooleanDBReader {
	private final List<Variable> booleanVariableList;

	public BooleanDBReader(List<Variable> booleanVariableList) {
		this.booleanVariableList = booleanVariableList;
	}

	/**
	 * Method reading the csv file and creating the boolean database.
	 *
	 * @param fileURI the path of the database file
	 * @return a BooleanDatabase representing the file database
	 */
	public BooleanDatabase readDB(String fileURI) {
		List<Map<Variable, String>> booleanTransactionList = new ArrayList<>();

		List<String> variableNameList = new ArrayList<>();

		List<List<String>> transactionValuesList = new ArrayList<>();

		// Getting all variables used in the file database
		try {
			variableNameList.addAll(Arrays.asList(Files.lines(Paths.get(ClassLoader.getSystemResource(fileURI).toURI()))
					.map(line -> line.split(";")).findFirst().get()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

		// Splitting the file lines into a List, skipping the header line
		try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(fileURI).toURI())).skip(1)) {
			stream.forEach(line -> transactionValuesList.add(Arrays.asList(line.split(";"))));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

		/*
			For each transaction, it creates a booleanTransaction, fills it of all the variables at 0 and fills the
		    variables that are into the file database at 1
		*/
		transactionValuesList.forEach(transactionValues -> {
			Map<Variable, String> booleanTransaction = new LinkedHashMap<>();

			booleanVariableList.forEach(booleanVariable -> booleanTransaction.put(booleanVariable, "0"));

			IntStream.range(0, transactionValues.size()).forEach(i -> {
				for (Variable booleanVariable : booleanVariableList)
					if (booleanVariable.getName().equals(variableNameList.get(i) + "_" + transactionValues.get(i)))
						booleanTransaction.put(booleanVariable, "1");
			});

			booleanTransactionList.add(booleanTransaction);
		});

		return new BooleanDatabase(booleanVariableList, booleanTransactionList);
	}
}
