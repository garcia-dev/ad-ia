package representations;

import java.util.Map;
import java.util.Set;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class IncompatibilityConstraint extends Rule {

    private Map<Variable, String> variables;

    public IncompatibilityConstraint(Map<Variable, String> premise) {
        super(premise);
    }

}
