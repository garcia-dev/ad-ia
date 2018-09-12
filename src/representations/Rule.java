package representations;

import java.util.Map;
import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

class Rule implements Constraint {

    private Map<Variable, String> premise;
    private Map<Variable, String> conclusion;

    public Rule(Map<Variable, String> premise, Map<Variable, String> conclusion) {
        this.premise = premise;
        this.conclusion = conclusion;
    }

    public Rule(Map<Variable, String> premise) {
        this(premise, null);
    }

    @Override
    public Set<Variable> getScope() {
        return null;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> allocation) {
        for ()
    }

}
