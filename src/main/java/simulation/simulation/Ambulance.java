package simulation.simulation;

import java.awt.*;

public class Ambulance extends Vehicle {
    public Ambulance(int destX, int destY, Component c) {
        super(destX, destY, c);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(X, Y, OBJECT_WIDTH, OBJECT_HEIGHT);
    }
}
