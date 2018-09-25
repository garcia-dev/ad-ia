package representations;

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class IncompatibilityConstraint implements Constraint {

	private Map<Variable, String> variables;

	public IncompatibilityConstraint(Map<Variable, String> variables) {
		this.variables = variables;
	}

	@Override
	public Set<Variable> getScope() {

		return variables.keySet();
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		boolean test=true;
		ArrayList<Variable> keySet=new ArrayList(this.variables.keySet());
		for(Variable var: keySet){
			if(allocation.containsKey(var)){
				test&=(this.variables.get(var)==allocation.get(var));
			}
			else{
				return false;
			}
		}
		
		return !test;
	}
	
}
