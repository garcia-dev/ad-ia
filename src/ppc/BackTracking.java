package ppc;

import representations.Variable;
import representations.Constraint;
import examples.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class BackTracking{
		
		private Set<Constraint> allConstraint;
		private Set<Variable> allVariable;
		private List<Variable> notUsed;
		
		private HashMap<Variable,List<String>> variableDomain;
		private List<HashMap<Variable,String>> precCar;
		
		public BackTracking(Set<Constraint> allConstraint,Set<Variable> allVariable){
			this.allConstraint=allConstraint;
			this.allVariable=allVariable;
			
			this.notUsed=new ArrayList(allVariable);
			this.variableDomain=new HashMap();
			
			this.precCar=new ArrayList();
			
			for(Variable var:this.allVariable){
				this.variableDomain.put(var,new ArrayList(var.getDomain()));
			}	
		}
		
		public HashMap<Variable,String> solution(HashMap<Variable,String> car,int index){
			if(index<notUsed.size() && index>=0){
				String nextValue=getValue(notUsed.get(index));  //compute the next value return "" if there is no more value
				if(nextValue==""){
					car.remove(notUsed.get(index));
					return solution(car,index-1);	//no other value in the domain so go back
				}
				else{
					car.put(notUsed.get(index),nextValue);
					if(doTest(car)){
						filterDomain(car,this.variableDomain);
						return solution(car,index+1); // the test is currently succesful so go ahead to add an other variable
					}
					else{
						return solution(car,index); //there is other value in the domain so try to test another one
					}
				}
				
			}
			else {
				if(index==-1){
					return null;
				}
				else{
					if(alreadyGive(car)){
						car.remove(notUsed.get(index-1));
						return solution(car,index-2);	//no other value in the domain so go back
					}
					else{
						this.precCar.add(new HashMap(car));
						return car;
					}
				}
				
			}
			
		}
		

		
		private String getValue(Variable variable){
			List<String> possibleValue=this.variableDomain.get(variable);
			if(possibleValue.isEmpty()){
				this.variableDomain.put(variable,new ArrayList(variable.getDomain()));
				return "";
			}
			else{
				String value=possibleValue.get(0);
				possibleValue.remove(0);
				this.variableDomain.put(variable,possibleValue);
				return value;
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
		private boolean alreadyGive(HashMap<Variable,String> car){// test if the car make in solution has already been make ^^
			if(this.precCar.contains(car)){
				return true;
			}
			return false;
		}
		
		private void filterDomain(Map<Variable,String> car,Map<Variable,List<String>> domainVariable){
			
			for(Constraint c:this.allConstraint){
				boolean b=c.filter(car,varDomain);
				
			}
		}
		private Map<Variable,Set<String>> convert2Set(HashMap<Variable,List<String>> mapList){
			Map<Variable,Set<String>> varDomain=new HashMap();
			for (Map.Entry<Variable, List<String>> entry : mapList.entrySet()) {
				Variable key = entry.getKey();
				List<String> value = entry.getValue();
				varDomain.put(key,new HashSet(value));
			}
			return varDomain;
		}
		
		private Map<Variable,List<String>> convert2List(HashMap<Variable,Set<String>> mapSet){
			Map<Variable,List<String>> varDomain=new HashMap();
		}
}
