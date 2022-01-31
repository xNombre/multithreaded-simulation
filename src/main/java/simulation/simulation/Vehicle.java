package simulation.simulation;

import java.util.Random;

import simulation.gui.SimulationFrame;

import java.awt.*;

public abstract class Vehicle {
    // Delay between each step
    private static final int THREAD_SLEEP_TIME = 10;
    private static final int STEP_LENGTH = 1;
    // Time needed for rescure after reaching the destination
    private static final int RESCUE_TIME = 50;
    private static final int STARTING_X = 50;
    private static final int STARTING_Y = 0;

    protected static final int OBJECT_WIDTH = 10;
    protected static final int OBJECT_HEIGHT = 10;
    protected VehicleType vehicleType;

    private static final Random rand = new Random();
    private final Runnable vehicleRunnable = new Runnable() {
        public void run() {
            vehicleAction();
        }
    };

    private int destX, destY;
    protected int X = STARTING_X, Y = STARTING_Y;
    private boolean isReturning = false;
    private boolean threadShouldStop = false;
    private Thread vehicleThread;
    private boolean isBusy = false;

    Vehicle() {
    }

    void vehicleAction() {
        while (!threadShouldStop) {
            if (Y != destY && rand.nextBoolean()) {
                int distance = Y - destY;
                int distanceAbs = Math.abs(distance);
                int step = (distanceAbs < STEP_LENGTH ? distanceAbs : STEP_LENGTH);
                Y += (distance > 0 ? -step : step);
            }
            if (X != destX && rand.nextBoolean()) {
                int distance = X - destX;
                int distanceAbs = Math.abs(distance);
                int step = (distanceAbs < STEP_LENGTH ? distanceAbs : STEP_LENGTH);
                X += (distance > 0 ? -step : step);
            }

            SimulationFrame.simPanel.repaint();

            if (Y == destY && X == destX) {
                // Am I done?
                if (isReturning)
                    break;

                // Nope, time to rescue
                try {
                    Thread.sleep(RESCUE_TIME);
                } catch (InterruptedException e) {
                }

                // Remove accident
                Accident.removeAccident(X, Y);

                // Time to return, change destination
                destX = STARTING_X;
                destY = STARTING_Y;
                isReturning = true;
                continue;
            }

            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (InterruptedException e) {
            }
        }
        isBusy = false;
        isReturning = false;
    }

    public abstract void paint(Graphics g);

    public void threadStop() {
        threadShouldStop = true;
    }

    public void threadStart() {
        threadShouldStop = false;
        vehicleThread = new Thread(vehicleRunnable);
        vehicleThread.start();
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void newTask(int destX, int destY) {
        this.destX = destX;
        this.destY = destY;
        this.isBusy = true;

        vehicleThread = new Thread(vehicleRunnable);
        vehicleThread.start();
    }
}
