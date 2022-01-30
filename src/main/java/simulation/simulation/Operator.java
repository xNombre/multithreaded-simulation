package simulation.simulation;

import simulation.accident.AccidentType;
import simulation.vehicles.VehicleDispatcher;
import simulation.vehicles.VehicleType;

public class Operator {
    private int id;

    Operator(int id) {
        this.id = id;
    }

    int getOperatorId() {
        return id;
    }

    void receiveReport(int X, int Y, AccidentType type) {
        switch (type) {
            case FIRE:
                VehicleDispatcher.dispatchVehicle(X, Y, VehicleType.FIRE_TRUCK);
                break;
            case HEALTH_HAZARD:
                VehicleDispatcher.dispatchVehicle(X, Y, VehicleType.AMBULANCE);
                break;
            case ROBBERY:
                VehicleDispatcher.dispatchVehicle(X, Y, VehicleType.POLICE_CAR);
                break;
            case TRAFFIC_ACCIDENT:
                VehicleDispatcher.dispatchVehicle(X, Y, VehicleType.FIRE_TRUCK);
                VehicleDispatcher.dispatchVehicle(X, Y, VehicleType.AMBULANCE);
                VehicleDispatcher.dispatchVehicle(X, Y, VehicleType.POLICE_CAR);
                break;
            default:
                break;
        }
    }
}
