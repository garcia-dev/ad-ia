package ppc;

import representations.Variable;
import representations.Constraint;
import java.util.Set;
import java.util.HashMap;



public class GenAndTest{
	
	private Set<Constraint> allConstraint;
	private ArrayList<Variable> allVariable;
	public GenAndTest(Set<Constraint> allConstraint,Set<Variable> allVariable){
		this.allConstraint=allConstraint;
		this.allVariable=new ArrayList(allVariable);
		HashMap<Variable,String> car=new HashMap();
		
		for(Variable v:allVariable){
			ArrayList<String> domain=new ArrayList(v.getDomain());
			car.put(v,domain.get(0));
		}
		boolean test=doTest(car);
		int j=1;
		while(test){
			for(int i=0;i<j;i++){
				ArrayList<String> domain=new ArrayList(v.getDomain());
				if((i % 3)==0){
					car.put(this.allVariable.get(i),domain.get(2));
				}
				else{
					car.put(this.allVariable.get(i),domain.get((i%3)-1));
				}
			
		}
		test=doTest();
		j++;
	}
	if(test){System.out.println(car);}
}
	public boolean doTest(HashMap<Variable,String> car){
		for(Constraint c:this.allConstraint){
			if(!c.isSatisfiedBy(car)){
				return false;
			}
		}
		return true;
	}
	
}
