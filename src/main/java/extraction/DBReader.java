package extraction;

import representations.Variable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DBReader {

	protected Set<Variable> variables;

	public DBReader(Set<Variable> variables) {
		this.variables = variables;
	}

	/**
	 * Reads a database, that is, a list of instantiations, from a CSV file.
	 * <p>
	 * The expected format is the ';'-separated list of variable names as the
	 * first line, then one ';'-separated list of values per instance each on
	 * its own line
	 */
	public Database importDB(String filename) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			Database res = this.readDB(reader);
			reader.close();
			return res;
		}
	}

	private Database readDB(BufferedReader in) throws IOException {
		// Reading variables
		List<Variable> orderedVariables = new ArrayList<>();
		String variableLine = in.readLine();
		for (String variableName : variableLine.split(";")) {
			boolean found = false;
			for (Variable variable : variables) {
				if (variable.getName().equals(variableName)) {
					orderedVariables.add(variable);
					found = true;
					break;
				}
			}
			if (!found) {
				throw new IOException("Unknown variable name: " + variableName);
			}
		}
		// Reading instances
		List<Map<Variable, String>> instances = new ArrayList<>();
		String line;
		int lineNb = 1;
		while ((line = in.readLine()) != null) {
			String[] parts = line.split(";");
			if (parts.length != orderedVariables.size()) {
				throw new IOException("Wrong number of fields on line " + lineNb);
			}
			Map<Variable, String> instance = new HashMap<>();
			for (int i = 0; i < parts.length; i++) {
				instance.put(orderedVariables.get(i), parts[i]);
			}
			instances.add(instance);
			lineNb++;
		}
		return new Database(orderedVariables, instances);
	}
}
