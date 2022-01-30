package simulation.simulation;

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
                VehicleDispatcher.getInstance().dispatchVehicle(X, Y, VehicleType.FIRE_TRUCK);
                break;
            case HEALTH_HAZARD:
                VehicleDispatcher.getInstance().dispatchVehicle(X, Y, VehicleType.AMBULANCE);
                break;
            case ROBBERY:
                VehicleDispatcher.getInstance().dispatchVehicle(X, Y, VehicleType.POLICE_CAR);
                break;
            case TRAFFIC_ACCIDENT:
                VehicleDispatcher.getInstance().dispatchVehicle(X, Y, VehicleType.FIRE_TRUCK);
                VehicleDispatcher.getInstance().dispatchVehicle(X, Y, VehicleType.AMBULANCE);
                VehicleDispatcher.getInstance().dispatchVehicle(X, Y, VehicleType.POLICE_CAR);
                break;
            default:
                break;
        }
    }
}
