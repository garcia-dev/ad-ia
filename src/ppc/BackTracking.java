package ppc;

import representations.Variable;
import representations.Constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

public class BackTracking{
		private Set<Constraint> allConstraint;
		private Set<Variable> allVariable;
		private List<Variable> notUsed;
		public BackTracking(Set<Constraint> allConstraint,Set<Variable> allVariable){
			this.allConstraint=allConstraint;
			this.allVariable=allVariable;
			
			this.notUsed=new ArrayList(allVariable);
			
		}
		
		public HashMap<Variable,String> solution(HashMap<Variable,String> car){
			if(!notUsed.isEmpty()){
				if(car != null){
					//assign the next value (use notUsed)
					car.put(notUsed.get(0),getValue(car.get(notUsed.get(0)),notUsed.get(0)));
					if(doTest(car)){
						this.notUsed.remove(0);
						return solution(car);
					}
					else{
						return null;
					}
				}
				else{
					//go back and use all variable (use allVariable) 
					this.notUsed=new ArrayList(allVariable);
					car=new HashMap();
					car.put(notUsed.get(0),getValue(car.get(notUsed.get(0)),notUsed.get(0)));
					if(doTest(car)){
						this.notUsed.remove(0);
						return solution(car);
					}
					else{
						return null;
					}
				}
			}
			else {return car;}
			
		}
		
		public String getValue(String current,Variable variable){
			List<String> domain=new ArrayList(variable.getDomain());
			if(domain.indexOf(current)<domain.size()){
				return domain.get(0);
			}
			else{
				return domain.get(domain.indexOf(current)+1);
			}
		}
		
		public boolean doTest(HashMap<Variable,String> car){
			List<Variable> variables=new ArrayList(car.keySet());
			for(Constraint c:this.allConstraint){
				if(car.keySet().containsAll(c.getScope())){
					if(!c.isSatisfiedBy(car)){
						return false;
					}
				}
			}
			return true;
		}
}
