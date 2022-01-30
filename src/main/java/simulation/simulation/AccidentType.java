package simulation.simulation;

import java.util.Random;

public enum AccidentType {
    FIRE,
    TRAFFIC_ACCIDENT,
    ROBBERY,
    HEALTH_HAZARD;

    private static final Random rand = new Random();

    public static AccidentType getRandomAccident() {
        int x = rand.nextInt(AccidentType.values().length);
        return AccidentType.values()[x];
    }
}
