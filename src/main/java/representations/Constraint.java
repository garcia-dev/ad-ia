package main.java.representations;

import java.util.Map;
import java.util.Set;

public interface Constraint {

	Set<Variable> getScope();

	boolean isSatisfiedBy(Map<Variable, String> allocation);

	boolean filter(Map<Variable, String> allocation, Map<Variable, Set<String>> variableDomain);

}
