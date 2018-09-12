package representations;

import java.util.Map;
import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class Disjunction extends Rule {
    private Map<Variable, String> variables;

    public Disjunction(Map<Variable, String> allocation) {
        super(allocation);
    }

    @Override
    public Set<Variable> getScope() {
        return variables.keySet();
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> map) {
        return false;
    }
}
