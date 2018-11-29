package planning;

import java.util.List;

/**
 * Action's class
 * <p>
 * The class Action represents an action that can be applied to a State.
 * </p>
 *
 * @author DORANGE Marin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-27
 * @see Rule
 */
public class Action {
	private final List<Rule> ruleList;

	/**
	 * Action's constructor initalizing its ruleList.
	 *
	 * @param ruleList the ruleList to affiliate to the Action
	 */
	public Action(List<Rule> ruleList) {
		this.ruleList = ruleList;
	}

	/**
	 * Getter of the ruleList.
	 *
	 * @return the ruleList
	 */
	List<Rule> getRuleList() {
		return ruleList;
	}

	/**
	 * Method allowing to add a new Rule to the Action.
	 *
	 * @param rule the Rule to add to the Action
	 */
	public void add(Rule rule) {
		ruleList.add(rule);
	}

	/**
	 * Method checking if the Action can be applied on a given State.
	 *
	 * @param state the State on which we are checking the Action application
	 * @return True if the Action can be applied on the State, False if it can't
	 */
	boolean isApplicable(State state) {
		for (Rule rule : getRuleList())
			if (state.satisfies(rule.getPreconditions()))
				return true;

		return false;
	}

	/**
	 * Method applying the Action on a given State.
	 *
	 * @param state the State on which we are applying the Action
	 * @return a new State based on the given one with the Action applied
	 */
	public State apply(State state) {
		State temp = (State) state.clone();

		if (isApplicable(state))
			getRuleList().forEach(rule -> {
				if (state.satisfies(rule.getPreconditions()))
					temp.putAll(rule.getEffects());
			});

		return temp;
	}
}
