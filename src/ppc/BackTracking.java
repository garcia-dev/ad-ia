package ppc;

import representations.Variable;
import representations.Constraint;
import examples.Main;

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
		
		public HashMap<Variable,String> solution(HashMap<Variable,String> car,int index){
			if(index<notUsed.size() && index>=0){
					String nextValue=getValue(car.get(notUsed.get(index)),notUsed.get(index));  //compute the next value return "" if there is no more value
					if(nextValue==""){
						car.remove(notUsed.get(index));
						return solution(car,index-1);	//no other value in the domain so go back
					}
					else{
						car.put(notUsed.get(index),nextValue);
						if(doTest(car)){
							return solution(car,index+1); // the test is currently succesful so go ahead to add an other variable
						}
						else{
							return solution(car,index); //there is other value in the domain so try to test another one
						}
					}
				
			}
			else {return car;}
			
		}
		
		
		private String getValue(String current,Variable variable){
			List<String> domain=new ArrayList(variable.getDomain());
			int index=domain.indexOf(current);
			System.out.println("index value: "+index);
			if(index==0){
				System.out.println("test0");
				return ""; //if we have seen all possible value of the domain of the variable.
			}
			else {
				if(index>-1){
				System.out.println("test");
				return domain.get(index+1);
				}
				else{
					System.out.println("test2");
					return domain.get(0);
				}
			}
		}
		
		public boolean doTest(HashMap<Variable,String> car){
			List<Variable> variables=new ArrayList(car.keySet());
			for(Constraint c:this.allConstraint){
				if(car!=null){
					if(car.keySet().containsAll(c.getScope())){
						if(!c.isSatisfiedBy(car)){
							return false;
						}
					}
				}
			}
			return true;
		}
}
