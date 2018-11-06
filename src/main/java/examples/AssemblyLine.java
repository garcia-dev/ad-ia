package main.java.examples;

import main.java.planning.Action;
import main.java.planning.State;
import main.java.representations.Variable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class AssemblyLine {
	/* Variables definitions */
	private static final HashMap<Variable, String> CHASSIS_INSTALLATION = new HashMap<>();
	private static final HashMap<Variable, String> FRONT_LEFT_WHEEL_INSTALLATION = new HashMap<>();
	private static final HashMap<Variable, String> FRONT_RIGHT_WHEEL_INSTALLATION = new HashMap<>();
	private static final HashMap<Variable, String> REAR_LEFT_WHEEL_INSTALLATION = new HashMap<>();
	private static final HashMap<Variable, String> REAR_RIGHT_WHEEL_INSTALLATION = new HashMap<>();
	private static final HashMap<Variable, String> BODY_INSTALLATION = new HashMap<>();
	private static final HashMap<Variable, String> LEFT_WHEELS_INSTALLATION = new HashMap<>();
	private static final HashMap<Variable, String> RIGHT_WHEELS_INSTALLATION = new HashMap<>();

	/* Color variables */
	private static final HashMap<Variable, String> REAR_WHEELS_INSTALLATION = new HashMap<>();
	private static final HashMap<Variable, String> FRONT_WHEELS_INSTALLATION = new HashMap<>();
	private static final HashMap<Variable, String> ROOF_PAINTING_GRAY = new HashMap<>();
	private static final HashMap<Variable, String> ROOF_PAINTING_BLACK = new HashMap<>();
	private static final HashMap<Variable, String> ROOF_PAINTING_WHITE = new HashMap<>();
	private static final HashMap<Variable, String> ROOF_PAINTING_RED = new HashMap<>();
	private static final HashMap<Variable, String> ROOF_PAINTING_GREEN = new HashMap<>();
	private static final HashMap<Variable, String> ROOF_PAINTING_BLUE = new HashMap<>();
	private static final HashMap<Variable, String> ROOF_PAINTING_ORANGE = new HashMap<>();
	private static final HashMap<Variable, String> ROOF_PAINTING_YELLOW = new HashMap<>();
	private static final HashMap<Variable, String> FRONT_PAINTING = new HashMap<>();
	private static final HashMap<Variable, String> LEFT_PAINTING = new HashMap<>();
	private static final HashMap<Variable, String> REAR_PAINTING = new HashMap<>();
	private static final HashMap<Variable, String> RIGHT_PAINTING = new HashMap<>();
	private static final Action INSTALL_CHASSIS = new Action(new HashMap<>(), CHASSIS_INSTALLATION);
	/* Simple installation actions */
	public static final Action INSTALL_FRONT_LEFT_WHEEL = new Action(CHASSIS_INSTALLATION, FRONT_LEFT_WHEEL_INSTALLATION);
	public static final Action INSTALL_FRONT_RIGHT_WHEEL = new Action(CHASSIS_INSTALLATION, FRONT_RIGHT_WHEEL_INSTALLATION);
	public static final Action INSTALL_REAR_LEFT_WHEEL = new Action(CHASSIS_INSTALLATION, REAR_LEFT_WHEEL_INSTALLATION);
	public static final Action INSTALL_REAR_RIGHT_WHEEL = new Action(CHASSIS_INSTALLATION, REAR_RIGHT_WHEEL_INSTALLATION);
	private static final Action INSTALL_BODY = new Action(CHASSIS_INSTALLATION, BODY_INSTALLATION);
	/* Parallel installation actions */
	public static final Action INSTALL_LEFT_WHEELS = new Action(CHASSIS_INSTALLATION, LEFT_WHEELS_INSTALLATION);
	public static final Action INSTALL_RIGHT_WHEELS = new Action(CHASSIS_INSTALLATION, RIGHT_WHEELS_INSTALLATION);
	public static final Action INSTALL_REAR_WHEELS = new Action(CHASSIS_INSTALLATION, REAR_WHEELS_INSTALLATION);
	public static final Action INSTALL_FRONT_WHEELS = new Action(CHASSIS_INSTALLATION, FRONT_WHEELS_INSTALLATION);
	/* Precise painting actions */
	public static final Action PAINT_ROOF_GRAY = new Action(BODY_INSTALLATION, ROOF_PAINTING_GRAY);
	public static final Action PAINT_ROOF_BLACK = new Action(BODY_INSTALLATION, ROOF_PAINTING_BLACK);
	public static final Action PAINT_ROOF_WHITE = new Action(BODY_INSTALLATION, ROOF_PAINTING_WHITE);
	public static final Action PAINT_ROOF_RED = new Action(BODY_INSTALLATION, ROOF_PAINTING_RED);
	public static final Action PAINT_ROOF_GREEN = new Action(BODY_INSTALLATION, ROOF_PAINTING_GREEN);
	public static final Action PAINT_ROOF_BLUE = new Action(BODY_INSTALLATION, ROOF_PAINTING_BLUE);
	public static final Action PAINT_ROOF_ORANGE = new Action(BODY_INSTALLATION, ROOF_PAINTING_ORANGE);
	public static final Action PAINT_ROOF_YELLOW = new Action(BODY_INSTALLATION, ROOF_PAINTING_YELLOW);

	/* Actions */
	/* Large effects painting actions */
	public static final Action PAINT_FRONT = new Action(BODY_INSTALLATION, FRONT_PAINTING);
	public static final Action PAINT_LEFT = new Action(BODY_INSTALLATION, LEFT_PAINTING);
	public static final Action PAINT_REAR = new Action(BODY_INSTALLATION, REAR_PAINTING);
	public static final Action PAINT_RIGHT = new Action(BODY_INSTALLATION, RIGHT_PAINTING);

	/* Colors domain */
	private static final Set<String> ALL_COLORS =
			new HashSet<>(Arrays.asList("gray", "black", "white", "red",
					"green", "blue", "orange", "yellow"));

	/* Wheels color */
	private static final Variable FRONT_LEFT_WHEEL_COLOR = new Variable("frontLeftWheelColor", ALL_COLORS);
	private static final Variable FRONT_RIGHT_WHEEL_COLOR = new Variable("frontRightWheelColor", ALL_COLORS);
	private static final Variable REAR_LEFT_WHEEL_COLOR = new Variable("rearLeftWheelColor", ALL_COLORS);
	private static final Variable REAR_RIGHT_WHEEL_COLOR = new Variable("rearRightWheelColor", ALL_COLORS);

	/* Sides color */
	private static final Variable FRONT_COLOR = new Variable("frontColor", ALL_COLORS);
	private static final Variable LEFT_COLOR = new Variable("leftColor", ALL_COLORS);
	private static final Variable REAR_COLOR = new Variable("rearColor", ALL_COLORS);
	private static final Variable RIGHT_COLOR = new Variable("rightColor", ALL_COLORS);
	private static final Variable ROOF_COLOR = new Variable("roofColor", ALL_COLORS);

	/* Boolean domain */
	private static final Set<String> BOOLEANS =
			new HashSet<>(Arrays.asList("true", "false"));

	/* Boolean variables */
	private static final Variable HAS_CHASSIS = new Variable("hasChassis", BOOLEANS);
	private static final Variable HAS_FRONT_LEFT_WHEEL = new Variable("hasFrontLeftWheel", BOOLEANS);
	private static final Variable HAS_FRONT_RIGHT_WHEEL = new Variable("hasFrontRightWheel", BOOLEANS);
	private static final Variable HAS_REAR_LEFT_WHEEL = new Variable("hasRearLeftWheel", BOOLEANS);
	private static final Variable HAS_REAR_RIGHT_WHEEL = new Variable("hasRearRightWheel", BOOLEANS);
	private static final Variable HAS_BODY = new Variable("hasBody", BOOLEANS);

	/* Variables assignments */
	static {
		CHASSIS_INSTALLATION.put(AssemblyLine.HAS_CHASSIS, "true");
		FRONT_LEFT_WHEEL_INSTALLATION.put(AssemblyLine.HAS_FRONT_LEFT_WHEEL, "true");
		FRONT_RIGHT_WHEEL_INSTALLATION.put(AssemblyLine.HAS_FRONT_RIGHT_WHEEL, "true");
		REAR_LEFT_WHEEL_INSTALLATION.put(AssemblyLine.HAS_REAR_LEFT_WHEEL, "true");
		REAR_RIGHT_WHEEL_INSTALLATION.put(AssemblyLine.HAS_REAR_RIGHT_WHEEL, "true");
		BODY_INSTALLATION.put(AssemblyLine.HAS_BODY, "true");

		LEFT_WHEELS_INSTALLATION.put(AssemblyLine.HAS_FRONT_LEFT_WHEEL, "true");
		LEFT_WHEELS_INSTALLATION.put(AssemblyLine.HAS_REAR_LEFT_WHEEL, "true");
		RIGHT_WHEELS_INSTALLATION.put(AssemblyLine.HAS_FRONT_RIGHT_WHEEL, "true");
		RIGHT_WHEELS_INSTALLATION.put(AssemblyLine.HAS_REAR_RIGHT_WHEEL, "true");
		REAR_WHEELS_INSTALLATION.put(AssemblyLine.HAS_REAR_LEFT_WHEEL, "true");
		REAR_WHEELS_INSTALLATION.put(AssemblyLine.HAS_REAR_RIGHT_WHEEL, "true");
		FRONT_WHEELS_INSTALLATION.put(AssemblyLine.HAS_FRONT_LEFT_WHEEL, "true");
		FRONT_WHEELS_INSTALLATION.put(AssemblyLine.HAS_FRONT_RIGHT_WHEEL, "true");

		ROOF_PAINTING_GRAY.put(AssemblyLine.ROOF_COLOR, "gray");
		ROOF_PAINTING_BLACK.put(AssemblyLine.ROOF_COLOR, "black");
		ROOF_PAINTING_WHITE.put(AssemblyLine.ROOF_COLOR, "white");
		ROOF_PAINTING_RED.put(AssemblyLine.ROOF_COLOR, "red");
		ROOF_PAINTING_GREEN.put(AssemblyLine.ROOF_COLOR, "green");
		ROOF_PAINTING_BLUE.put(AssemblyLine.ROOF_COLOR, "blue");
		ROOF_PAINTING_ORANGE.put(AssemblyLine.ROOF_COLOR, "orange");
		ROOF_PAINTING_YELLOW.put(AssemblyLine.ROOF_COLOR, "yellow");

		FRONT_PAINTING.put(AssemblyLine.FRONT_COLOR, "gray");
		FRONT_PAINTING.put(AssemblyLine.ROOF_COLOR, "gray");
		LEFT_PAINTING.put(AssemblyLine.LEFT_COLOR, "gray");
		LEFT_PAINTING.put(AssemblyLine.FRONT_LEFT_WHEEL_COLOR, "gray");
		LEFT_PAINTING.put(AssemblyLine.REAR_LEFT_WHEEL_COLOR, "gray");
		LEFT_PAINTING.put(AssemblyLine.ROOF_COLOR, "gray");
		REAR_PAINTING.put(AssemblyLine.REAR_COLOR, "gray");
		REAR_PAINTING.put(AssemblyLine.ROOF_COLOR, "gray");
		RIGHT_PAINTING.put(AssemblyLine.RIGHT_COLOR, "gray");
		RIGHT_PAINTING.put(AssemblyLine.FRONT_RIGHT_WHEEL_COLOR, "gray");
		RIGHT_PAINTING.put(AssemblyLine.REAR_RIGHT_WHEEL_COLOR, "gray");
		RIGHT_PAINTING.put(AssemblyLine.ROOF_COLOR, "gray");
	}

	public static State generateGoalState() {
		return new State(new HashMap<>())
				.apply(INSTALL_CHASSIS)
				.apply(INSTALL_BODY);
	}

}
