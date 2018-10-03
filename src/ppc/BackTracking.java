package ppc;

import representations.Constraint;
import representations.Variable;
import examples.Main;
import java.util.*;

public class BackTracking {
	private Set<Constraint> allConstraint;
	private Set<Variable> allVariable;
	private ArrayList<Variable> notUsed;
	private HashMap<Variable, Set<String>> variableDomain;
	private List<HashMap<Variable, String>> precCar;

	public BackTracking(Set<Constraint> allConstraint, Set<Variable> allVariable) {
		this.allConstraint = allConstraint;
		this.allVariable = allVariable;

		this.notUsed = new ArrayList<>(allVariable);
		this.variableDomain = new HashMap<>();

		this.precCar = new ArrayList<>();

		for (Variable var : this.allVariable) {
			this.variableDomain.put(var, new HashSet<>(var.getDomain()));
		}
	}

	public HashMap<Variable, String> solution(HashMap<Variable, String> car, int index) {
		if(!filterDomain(car,domainVariable)){
			
			car.remove(notUsed.get(index - 1));
			return solution(car, index - 2);    //no other value in the domain so go back
		}
		if (index < notUsed.size() && index >= 0) {
			String nextValue = getValue(notUsed.get(index));  //compute the next value return "" if there is no more value
			if (nextValue.equals("")) {
				car.remove(notUsed.get(index));
				return solution(car, index - 1);    //no other value in the domain so go back
			} else {
				car.put(notUsed.get(index), nextValue);
				if (doTest(car)) {
					return solution(car, index + 1); // the test is currently succesful so go ahead to add an other variable
				} else {
					return solution(car, index); //there is other value in the domain so try to test another one
				}
			}

		} else {
			if (index <0) {
				return null;
			} else {
				if (alreadyGive(car)) {
					car.remove(notUsed.get(index - 1));
					return solution(car, index - 2);    //no other value in the domain so go back
				} else {
					this.precCar.add(new HashMap<>(car));
					return car;
				}
			}

		}
	}

	private String getValue(Variable variable) {
		//Map<Variable,Set<String>> variableDomain=new HashMap(this.variableDomain);
		Set<String> possibleValue = variableDomain.get(variable);

		if (!possibleValue.iterator().hasNext()) {
			variableDomain.put(variable, new HashSet<>(variable.getDomain()));
			return "";
		} else {
			Iterator<String> i=possibleValue.iterator();
			String value = i.next();
			i.remove();
			this.variableDomain.put(variable, possibleValue);
			return value;
		}
	}

	private boolean doTest(HashMap<Variable, String> car) {
		for (Constraint c : this.allConstraint)
			if (car.keySet().containsAll(c.getScope()) && !c.isSatisfiedBy(car))
				return false;

		return true;
	}

	private boolean alreadyGive(HashMap<Variable, String> car) {// test if the car make in solution has already been make ^^
		if (this.precCar.contains(car)) {
			//System.out.println("already give");
			return true;
		}
		return false;
	}
	private boolean filterDomain(Map<Variable,String> car,Map<Variable,Set<String>> domainVariable){
		boolean t=true;
			for(Constraint c:this.allConstraint){
				boolean b=c.filter(car,domainVariable);
				for(Variable var:domainVariable.keySet()){
					if(domainVariable.get(var).size()==0 ){
						domainVariable.put(var,var.getDomain());
						t=false;
					}
					else if(domainVariable.get(var).size()==1){
						Iterator<String> i=domainVariable.get(var).iterator();
						car.put(var,i.next());
					}
				}
				System.out.println(b);
			}
			return t;
		}
}
