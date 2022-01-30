package simulation.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.awt.Component;

public class VehicleDispatcher {
    private static VehicleDispatcher INSTANCE = new VehicleDispatcher();
    private static final int FIRE_TRUCK_COUNT = 10;
    private static final int AMBULANCE_COUNT = 10;
    private static final int POLICE_CAR_COUNT = 10;

    public List<Vehicle> vehicles = new ArrayList<Vehicle>();
    private List<EmergencyAction> emergencyList = new ArrayList<EmergencyAction>();

    private class EmergencyAction {
        private int X, Y;
        private VehicleType type;

        public int getX() {
            return X;
        }

        public int getY() {
            return Y;
        }

        public VehicleType getVehicleType() {
            return type;
        }

        public EmergencyAction(int X, int Y, VehicleType type) {
            this.X = X;
            this.Y = Y;
            this.type = type;
        }
    }

    public static VehicleDispatcher getInstance() {
        return INSTANCE;
    }

    private VehicleDispatcher() {
        for (int i = 0; i < FIRE_TRUCK_COUNT; i++) {
            vehicles.add(new FireTruck());
        }
        for (int i = 0; i < AMBULANCE_COUNT; i++) {
            vehicles.add(new Ambulance());
        }
        for (int i = 0; i < POLICE_CAR_COUNT; i++) {
            vehicles.add(new PoliceCar());
        }
    }

    public synchronized void dispatchVehicle(int X, int Y, VehicleType vehicle) {
        for (EmergencyAction action : emergencyList) {
            if (X == action.getX() && Y == action.getY() && vehicle == action.getVehicleType())
                return;
        }

        for (Vehicle veh : vehicles) {
            if (veh.vehicleType == vehicle) {
                if (veh.isBusy())
                    continue;

                veh.newTask(X, Y);
                emergencyList.add(new EmergencyAction(X, Y, vehicle));
                return;
            }

        }
    }
}
