package simulation.simulation;

import java.util.Random;

public enum AccidentType {
    FIRE,
    TRAFFIC_ACCIDENT,
    ROBBERY,
    HEALTH_HAZARD;

	static Random rand = new Random();
	private static final AccidentType[] types = AccidentType.values();

public static AccidentType randomAccident() {
    int pick = rand.nextInt(types.length);
    return types[pick];
}
}
