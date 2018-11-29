package example;

import planning.Action;
import planning.Rule;
import planning.State;
import representations.Variable;

import java.util.*;

public class AssemblyLine {
	/* Boolean domain */
	private static final Set<String> BOOLEANS =
			new HashSet<>(Arrays.asList("true", "false"));

	/* Colors domain */
	private static final Set<String> ALL_COLORS =
			new HashSet<>(Arrays.asList("gray", "black", "white", "red", "green", "blue", "orange", "yellow"));

	/* Boolean variables */
	private static final Variable HAS_CHASSIS = new Variable("hasChassis", BOOLEANS);
	private static final Variable HAS_FRONT_LEFT_WHEEL = new Variable("hasFrontLeftWheel", BOOLEANS);
	private static final Variable HAS_FRONT_RIGHT_WHEEL = new Variable("hasFrontRightWheel", BOOLEANS);
	private static final Variable HAS_REAR_LEFT_WHEEL = new Variable("hasRearLeftWheel", BOOLEANS);
	private static final Variable HAS_REAR_RIGHT_WHEEL = new Variable("hasRearRightWheel", BOOLEANS);
	private static final Variable HAS_BODY = new Variable("hasBody", BOOLEANS);

	/* Wheels color variables */
	private static final Variable FRONT_LEFT_WHEEL_COLOR = new Variable("frontLeftWheelColor", ALL_COLORS);
	private static final Variable FRONT_RIGHT_WHEEL_COLOR = new Variable("frontRightWheelColor", ALL_COLORS);
	private static final Variable REAR_LEFT_WHEEL_COLOR = new Variable("rearLeftWheelColor", ALL_COLORS);
	private static final Variable REAR_RIGHT_WHEEL_COLOR = new Variable("rearRightWheelColor", ALL_COLORS);

	/* Bodywork color variables */
	private static final Variable FRONT_COLOR = new Variable("frontColor", ALL_COLORS);
	private static final Variable LEFT_COLOR = new Variable("leftColor", ALL_COLORS);
	private static final Variable REAR_COLOR = new Variable("rearColor", ALL_COLORS);
	private static final Variable RIGHT_COLOR = new Variable("rightColor", ALL_COLORS);
	private static final Variable ROOF_COLOR = new Variable("roofColor", ALL_COLORS);

	/* Unique parts actions */
	private static final Action INSTALL_CHASSIS;
	private static final Action INSTALL_BODY;
	private static final Action INSTALL_FRONT_LEFT_WHEEL;
	private static final Action INSTALL_FRONT_RIGHT_WHEEL;
	private static final Action INSTALL_REAR_LEFT_WHEEL;
	private static final Action INSTALL_REAR_RIGHT_WHEEL;

	/* Parallel parts actions */
	private static final Action INSTALL_FRONT_WHEELS;
	private static final Action INSTALL_REAR_WHEELS;
	private static final Action INSTALL_LEFT_WHEELS;
	private static final Action INSTALL_RIGHT_WHEELS;

	/* Roof painting action */
	private static final Map<String, Action[]> PAINTING_ACTIONS;
	private static final List<Action> ACTION_LIST = new ArrayList<>(Arrays.asList(
			INSTALL_CHASSIS,
			INSTALL_BODY,
			INSTALL_FRONT_WHEELS,
			INSTALL_REAR_WHEELS
	));
	private static final List<Variable> PART_VARIABLE_LIST = new ArrayList<>(Arrays.asList(
			HAS_CHASSIS,
			HAS_BODY,
			HAS_FRONT_LEFT_WHEEL,
			HAS_FRONT_RIGHT_WHEEL,
			HAS_REAR_LEFT_WHEEL,
			HAS_REAR_RIGHT_WHEEL
	));
	private static final List<Variable> COLOR_VARIABLE_LIST = new ArrayList<>(Arrays.asList(
			FRONT_LEFT_WHEEL_COLOR,
			FRONT_RIGHT_WHEEL_COLOR,
			REAR_LEFT_WHEEL_COLOR,
			REAR_RIGHT_WHEEL_COLOR,
			FRONT_COLOR,
			LEFT_COLOR,
			REAR_COLOR,
			RIGHT_COLOR,
			ROOF_COLOR
	));

	static {
		/* UNIQUE PARTS INSTALLATION */
		State chassisState = new State();
		chassisState.put(HAS_CHASSIS, "true");
		Rule chassisRule = new Rule(new State(), chassisState);
		INSTALL_CHASSIS = new Action(Collections.singletonList(chassisRule));

		State bodyState = new State();
		bodyState.put(HAS_BODY, "true");
		Rule bodyRule = new Rule(chassisState, bodyState);
		INSTALL_BODY = new Action(Collections.singletonList(bodyRule));

		State frontLeftWheelState = new State();
		frontLeftWheelState.put(HAS_FRONT_LEFT_WHEEL, "true");
		Rule frontLeftWheelRule = new Rule(chassisState, frontLeftWheelState);
		INSTALL_FRONT_LEFT_WHEEL = new Action(Collections.singletonList(frontLeftWheelRule));

		State frontRightWheelState = new State();
		frontRightWheelState.put(HAS_FRONT_RIGHT_WHEEL, "true");
		Rule frontRightWheelRule = new Rule(chassisState, frontRightWheelState);
		INSTALL_FRONT_RIGHT_WHEEL = new Action(Collections.singletonList(frontRightWheelRule));

		State rearLeftWheelState = new State();
		rearLeftWheelState.put(HAS_REAR_LEFT_WHEEL, "true");
		Rule rearLeftWheelRule = new Rule(chassisState, rearLeftWheelState);
		INSTALL_REAR_LEFT_WHEEL = new Action(Collections.singletonList(rearLeftWheelRule));

		State rearRightWheelState = new State();
		rearRightWheelState.put(HAS_REAR_RIGHT_WHEEL, "true");
		Rule rearRightWheelRule = new Rule(chassisState, rearRightWheelState);
		INSTALL_REAR_RIGHT_WHEEL = new Action(Collections.singletonList(rearRightWheelRule));

		/* PARALLEL PARTS INSTALLATION */
		INSTALL_FRONT_WHEELS = new Action(Arrays.asList(frontLeftWheelRule, frontRightWheelRule));
		INSTALL_REAR_WHEELS = new Action(Arrays.asList(rearLeftWheelRule, rearRightWheelRule));
		INSTALL_LEFT_WHEELS = new Action(Arrays.asList(frontLeftWheelRule, rearLeftWheelRule));
		INSTALL_RIGHT_WHEELS = new Action(Arrays.asList(frontRightWheelRule, rearRightWheelRule));

		PAINTING_ACTIONS = new HashMap<>();

		ALL_COLORS.forEach(color -> {
			Action[] actions = new Action[5];

			/* ROOF PAINTING ACTION */
			State roofPaintState = new State();
			roofPaintState.put(ROOF_COLOR, color);
			Rule roofPaintRule = new Rule(bodyState, roofPaintState);
			actions[0] = new Action(Collections.singletonList(roofPaintRule));

			/* BODY PAINTING ACTIONS */
			State leftPaintState = new State();
			leftPaintState.put(LEFT_COLOR, color);
			leftPaintState.put(FRONT_LEFT_WHEEL_COLOR, color);
			leftPaintState.put(REAR_LEFT_WHEEL_COLOR, color);
			Rule leftPaintRule = new Rule(bodyState, leftPaintState);
			actions[1] = new Action(Arrays.asList(leftPaintRule, roofPaintRule));

			State rightPaintState = new State();
			rightPaintState.put(RIGHT_COLOR, color);
			rightPaintState.put(FRONT_RIGHT_WHEEL_COLOR, color);
			rightPaintState.put(REAR_RIGHT_WHEEL_COLOR, color);
			Rule rightPaintRule = new Rule(bodyState, rightPaintState);
			actions[2] = new Action(Arrays.asList(rightPaintRule, roofPaintRule));

			State frontPaintState = new State();
			frontPaintState.put(FRONT_COLOR, color);
			Rule frontPaintRule = new Rule(bodyState, frontPaintState);
			actions[3] = new Action(Arrays.asList(frontPaintRule, roofPaintRule));

			State rearPaintState = new State();
			rearPaintState.put(REAR_COLOR, color);
			Rule rearPaintRule = new Rule(bodyState, rearPaintState);
			actions[4] = new Action(Arrays.asList(rearPaintRule, roofPaintRule));

			PAINTING_ACTIONS.put(color, actions);
		});
	}

	private State initialState;

	public AssemblyLine() {
		PAINTING_ACTIONS.keySet().forEach(color -> ACTION_LIST.addAll(Arrays.asList(PAINTING_ACTIONS.get(color))));
	}

	public static List<Action> getActionList() {
		return ACTION_LIST;
	}

	public State generateRandomGoalState() {
		Random random = new Random();
		initialState = new State();

		PART_VARIABLE_LIST.forEach(variable -> initialState.put(variable, "false"));
		COLOR_VARIABLE_LIST.forEach(variable -> initialState.put(variable, "gray"));

		for (int i = 0; i < 4; i++)
			initialState = ACTION_LIST.get(i).apply(initialState);

		COLOR_VARIABLE_LIST.forEach(variable -> initialState.put(variable,
				new ArrayList<>(ALL_COLORS).get(random.nextInt(ALL_COLORS.size()))));

		initialState.put(FRONT_LEFT_WHEEL_COLOR, initialState.get(LEFT_COLOR));
		initialState.put(REAR_LEFT_WHEEL_COLOR, initialState.get(LEFT_COLOR));
		initialState.put(FRONT_RIGHT_WHEEL_COLOR, initialState.get(RIGHT_COLOR));
		initialState.put(REAR_RIGHT_WHEEL_COLOR, initialState.get(RIGHT_COLOR));
		initialState.put(ROOF_COLOR, initialState.get(RIGHT_COLOR));

		return initialState;
	}
}