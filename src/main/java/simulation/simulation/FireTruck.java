package simulation.simulation;

import java.awt.*;

public class FireTruck extends Vehicle {
    public FireTruck(){
        this.vehicleType = VehicleType.FIRE_TRUCK;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(X, Y, OBJECT_WIDTH, OBJECT_HEIGHT);
    }
}
