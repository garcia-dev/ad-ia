package planning;

/**
 * Rule's class
 * <p>
 * The class Rule represents a Rule of construction.
 * The effect State cannot be applied if the preconditions State isn't present on the State we are applying it.
 * </p>
 *
 * @author DORANGE Marin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-27
 * @see State
 */
public class Rule {
	private final State preconditions;
	private final State effects;

	public Rule(State preconditions, State effects) {
		this.preconditions = preconditions;
		this.effects = effects;
	}

	State getPreconditions() {
		return preconditions;
	}

	State getEffects() {
		return effects;
	}
}
