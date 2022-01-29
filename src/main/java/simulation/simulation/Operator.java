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
        // TODO: Implement dispatching
        switch (type) {
            case FIRE:
                break;
            case HEALTH_HAZARD:
                break;
            case ROBBERY:
                break;
            case TRAFFIC_ACCIDENT:
                break;
            default:
                break;
        }
    }
}
