package simulation.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.awt.Component;

public class VehicleDispatcher {
    private static VehicleDispatcher INSTANCE;
    private static final int FIRE_TRUCK_COUNT = 10;
    private static final int AMBULANCE_COUNT = 10;
    private static final int POLICE_CAR_COUNT = 10;

    private static Component component;
    public List<PoliceCar> policecars = new ArrayList<PoliceCar>();
    public List<Ambulance> ambulances = new ArrayList<Ambulance>();
    public List<FireTruck> firetrucks = new ArrayList<FireTruck>();

    public static VehicleDispatcher getInstance() {
        if (INSTANCE == null)
            INSTANCE = new VehicleDispatcher();
        return INSTANCE;
    }

    private VehicleDispatcher() {
        if (component == null)
            ;//throw new Exception("component is null");

        for (int i = 0; i < FIRE_TRUCK_COUNT; i++) {
            firetrucks.add(new FireTruck(component));
        }
        for (int i = 0; i < AMBULANCE_COUNT; i++) {
            ambulances.add(new Ambulance(component));
        }
        for (int i = 0; i < POLICE_CAR_COUNT; i++) {
            policecars.add(new PoliceCar(component));
        }
    }

    public static void setComponent(Component component) {
        VehicleDispatcher.component = component;
    }

    public synchronized void dispatchVehicle(int X, int Y, VehicleType vehicle) {
        switch (vehicle) {
            case AMBULANCE:
                for (Ambulance ambulance : ambulances) {
                    if (ambulance.isBusy())
                        continue;
                    ambulance.newTask(X, Y);
                    return;
                }
                break;
            case FIRE_TRUCK:
                for (FireTruck firetruck : firetrucks) {
                    if (firetruck.isBusy())
                        continue;
                    firetruck.newTask(X, Y);
                    return;
                }
                break;
            case POLICE_CAR:
                for (PoliceCar policecar : policecars) {
                    if (policecar.isBusy())
                        continue;
                    policecar.newTask(X, Y);
                    return;
                }
                break;
        }
    }
}
