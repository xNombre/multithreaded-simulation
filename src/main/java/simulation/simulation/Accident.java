package simulation.simulation;

import javax.swing.*;

import simulation.gui.SimulationFrame;

import java.awt.*;
import java.util.Random;

public class Accident {
    private static final int OBJECT_WIDTH = 20;
    private static final int OBJECT_HEIGHT = 20;

    private static final ImageIcon fire = new ImageIcon("src/main/java/simulation/graphics/fire.png");
    private static final ImageIcon healthHazard = new ImageIcon("src/main/java/simulation/graphics/healthHazard.png");
    private static final ImageIcon robbery = new ImageIcon("src/main/java/simulation/graphics/robbery.png");
    private static final ImageIcon traffAcc = new ImageIcon("src/main/java/simulation/graphics/traffAcc.png");

    private static int borderWidth = -1, borderHeight = -1;
    private int X, Y;
    private AccidentType type;

    private final ImageIcon img;

    private static final Random rand = new Random();

    Accident(AccidentType type) {
        if (borderWidth == -1 || borderHeight == -1) {
            throw new IllegalStateException();
        }

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

        X = rand.nextInt(borderWidth);
        Y = rand.nextInt(borderHeight);
    }

    public static void setBorder(int borderWidth, int borderHeight) throws Exception {
        if (borderWidth < 1 || borderHeight < 1)
            throw new IllegalArgumentException();
        Accident.borderWidth = borderWidth - OBJECT_WIDTH;
        Accident.borderHeight = borderHeight - OBJECT_HEIGHT;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public AccidentType getType() {
        return type;
    }

    public void paint(Graphics g) {
        g.drawImage(img.getImage(), X, Y, 30, 30, null);
    }

    public static void removeAccident(int X, int Y) {
        for (Accident accident : SimulationFrame.accidends) {
            if (X == accident.getX() && Y == accident.getY()) {
                SimulationFrame.accidends.remove(accident);
                return;
            }
        }
    }
}
