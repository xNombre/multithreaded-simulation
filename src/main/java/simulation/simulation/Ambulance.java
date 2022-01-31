package simulation.simulation;

import java.awt.*;

public class Ambulance extends Vehicle {
    public Ambulance(){
        this.vehicleType = VehicleType.AMBULANCE;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(X, Y, OBJECT_WIDTH, OBJECT_HEIGHT);
    }
}
