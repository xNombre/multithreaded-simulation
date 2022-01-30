package simulation.simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Accident {
    private static final ImageIcon fire = new ImageIcon("src/main/java/simulation/graphics/fire.png");
    private static final ImageIcon healthHazard = new ImageIcon("src/main/java/simulation/graphics/healthHazard.png");
    private static final ImageIcon robbery = new ImageIcon("src/main/java/simulation/graphics/robbery.png");
    private static final ImageIcon traffAcc = new ImageIcon("src/main/java/simulation/graphics/traffAcc.png");

    int X, Y;
    AccidentType type;

    final ImageIcon img;

    private static final Random rand = new Random();

    private Shape shape;
    private Area area;
    private static final int OBJECT_WIDTH = 20;
    private static final int OBJECT_HEIGHT = 20;
    private boolean isReported = false;

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
        shape = new Rectangle2D.Float(X, Y, OBJECT_WIDTH, OBJECT_HEIGHT);
        area = new Area(shape);
    }

    public void paint(Graphics g) {
        g.drawImage(img.getImage(), X, Y,30,30, null);
    }
    public Rectangle2D getArea() {
        return area.getBounds2D();
    }
    public void report() {
        if (!isReported) {
            isReported = true;
            System.out.println("report");
        }
    }
}
