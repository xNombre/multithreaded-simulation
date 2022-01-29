package simulation.simulation;

public abstract class Vehicle {
    // Delay between each step
    final int THREAD_SLEEP_TIME = 10;
    final int STEP_LENGTH = 5;
    // Time needed for rescure after reaching the destination
    final int RESCUE_TIME = 50;
    final int STARTING_X = 50;
    final int STARTING_Y = 0;

    final Runnable vehicleRunnable = new Runnable() {
        public void run() {
            vehicleAction();
        }
    };

    int destX, destY;
    int X = STARTING_X, Y = STARTING_Y;
    boolean isReturning = false;
    Thread vehicleThread;

    Vehicle(int destX, int destY) {
        this.destX = destX;
        this.destY = destY;

        vehicleThread = new Thread(vehicleRunnable);
        vehicleThread.start();
    }

    private void vehicleAction() {
        while (true) {
            if (Y != destY) {
                Y += (Y < destY) ? STEP_LENGTH : -STEP_LENGTH;
            } else {
                X += (X < destX) ? STEP_LENGTH : -STEP_LENGTH;
            }

            if (Y == destY && X == destX) {
                // Am I done?
                if (isReturning)
                    break;

                // Nope, time to rescue
                try {
                    Thread.sleep(RESCUE_TIME);
                } catch (InterruptedException e) {
                }

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
    }
}
