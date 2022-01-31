package simulation.simulation;

import java.awt.*;

public class PoliceCar extends Vehicle {
    public PoliceCar() {
        this.vehicleType = VehicleType.POLICE_CAR;
    }

    @Override
    public void paint(Graphics g) {
        if (isBusy) {
            g.setColor(Color.BLUE);
            g.fillRect(X, Y, OBJECT_WIDTH, OBJECT_HEIGHT);
        }
    }
}
