package simulation.simulation;

import java.awt.*;
import java.util.Random;

public class Accident {
    int X, Y;
    AccidentType type;
    Color color;
    private static final Random rand = new Random();

    Accident(AccidentType type) {
        this.type = type;

        switch (type) {
            case FIRE:
                color = Color.red;
                break;
            case HEALTH_HAZARD:
                color = Color.green;
                break;
            case TRAFFIC_ACCIDENT:
                color = Color.black;
                break;
            default:
                color = Color.blue;
                break;
        }
        X = rand.nextInt(600);
        Y = rand.nextInt(600);
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(X, Y, 30, 30);
    }
}
