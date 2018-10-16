package examples;

import representations.Variable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AssemblyLine {

	/* Colors domain */
	private static final Set<String> ALL_COLORS =
			new HashSet<>(Arrays.asList("gray", "black", "white", "red",
										"green", "blue", "orange", "yellow"));

	/* Boolean domain */
	private static final Set<String> BOOLEANS =
			new HashSet<>(Arrays.asList("true", "false"));

	/* Boolean variables */
	public static final Variable HAS_CHASSIS = new Variable("hasChassis", BOOLEANS);
	public static final Variable HAS_FRONT_LEFT_WHEEL = new Variable("hasFrontLeftWheel", BOOLEANS);
	public static final Variable HAS_FRONT_RIGHT_WHEEL = new Variable("hasFrontRightWheel", BOOLEANS);
	public static final Variable HAS_REAR_LEFT_WHEEL = new Variable("hasRearLeftWheel", BOOLEANS);
	public static final Variable HAS_REAR_RIGHT_WHEEL = new Variable("hasRearRightWheel", BOOLEANS);
	public static final Variable HAS_BODY = new Variable("hasBody", BOOLEANS);

	/* Color variables */

	/* Wheels color */
	public static final Variable FRONT_LEFT_WHEEL_COLOR = new Variable("frontLeftWheelColor", ALL_COLORS);
	public static final Variable FRONT_RIGHT_WHEEL_COLOR = new Variable("frontRightWheelColor", ALL_COLORS);
	public static final Variable REAR_LEFT_WHEEL_COLOR = new Variable("rearLeftWheelColor", ALL_COLORS);
	public static final Variable REAR_RIGHT_WHEEL_COLOR = new Variable("rearRightWheelColor", ALL_COLORS);

	/* Sides color */
	public static final Variable FRONT_COLOR = new Variable("frontColor", ALL_COLORS);
	public static final Variable LEFT_COLOR = new Variable("leftColor", ALL_COLORS);
	public static final Variable REAR_COLOR = new Variable("rearColor", ALL_COLORS);
	public static final Variable RIGHT_COLOR = new Variable("rightColor", ALL_COLORS);

}
