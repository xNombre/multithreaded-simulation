package simulation.vehicles;

import java.awt.*;

public class PoliceCar extends Vehicle {
    public PoliceCar(int destX, int destY, Component c) {
        super(destX, destY, c);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(X, Y, OBJECT_WIDTH, OBJECT_HEIGHT);
    }
}
