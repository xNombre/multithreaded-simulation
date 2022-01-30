package simulation.simulation;

import java.util.Random;

public enum AccidentType {
    FIRE,
    TRAFFIC_ACCIDENT,
    ROBBERY,
    HEALTH_HAZARD;

    private static final Random rand = new Random();

    public static <T extends Enum<?>> T getRandomAccident(Class<T> clazz) {
        int x = rand.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
