package simulation.simulation;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Accident {
    private static final ImageIcon fire = new ImageIcon("src/main/java/simulation/graphics/fire.png");
    private static final ImageIcon healthHazard = new ImageIcon("src/main/java/simulation/graphics/healthHazard.png");
    private static final ImageIcon robbery = new ImageIcon("src/main/java/simulation/graphics/robbery.png");
    private static final ImageIcon traffAcc = new ImageIcon("src/main/java/simulation/graphics/traffAcc.png");

    private int X, Y;
    private AccidentType type;

    final ImageIcon img;

    private static final Random rand = new Random();

    Accident(AccidentType type) {
        this.type = type;

        switch (type) {
            case FIRE:
                img = fire;
                break;
            case HEALTH_HAZARD:
                img = healthHazard;
                break;
            case TRAFFIC_ACCIDENT:
                img = traffAcc;
                break;
            default:
                img = robbery;
                break;
        }
        X = rand.nextInt(600);
        Y = rand.nextInt(600);
    }
    public int getX(){
        return X;
    }

    public int getY() {
        return Y;
    }

    public AccidentType getType() {
        return type;
    }

    public void paint(Graphics g) {
        g.drawImage(img.getImage(), X, Y,30,30, null);
    }
}
