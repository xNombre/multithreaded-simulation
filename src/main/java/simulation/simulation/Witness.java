package simulation.simulation;

import simulation.gui.SimulationFrame;

import java.awt.*;
import java.util.Random;

public class Witness {
    // Delay between each step and accident seek
    private static final int THREAD_SLEEP_TIME = 5;
    private static final int STEP_LENGTH = 1;
    // Range to choose a new destionation on map
    private static final int NEW_DEST_RANGE = 100;
    private static final int OBJECT_WIDTH = 10;
    private static final int OBJECT_HEIGHT = 10;
    private static final int REPORTING_RANGE = 10;

    private static final Random rand = new Random();
    final Runnable witnessRunnable = new Runnable() {
        public void run() {
            witnessAction();
        }
    };

    private static int borderWidth = -1, borderHeight = -1;
    private int X, Y;
    private int destX, destY;
    private boolean threadShouldStop = false;
    private Thread witnessThread;

    public static void setBorder(int borderWidth, int borderHeight) {
        if (borderWidth < 1 || borderHeight < 1)
            throw new IllegalArgumentException();
        Witness.borderWidth = borderWidth - OBJECT_WIDTH;
        Witness.borderHeight = borderHeight - OBJECT_HEIGHT;
    }

    public Witness() {
        if (borderWidth == -1 || borderHeight == -1) {
            throw new IllegalStateException();
        }

        X = destX = rand.nextInt(borderWidth);
        Y = destY = rand.nextInt(borderHeight);

        witnessThread = new Thread(witnessRunnable);
        witnessThread.start();
    }

    void witnessAction() {
        while (!threadShouldStop) {
            if (Y == destY && X == destX) {
                // It's time to choose new destination

                // Offset the step to move in both directions
                destX += rand.nextInt(NEW_DEST_RANGE * 2 + 1) - NEW_DEST_RANGE;
                destY += rand.nextInt(NEW_DEST_RANGE * 2 + 1) - NEW_DEST_RANGE;

                // Check bounds
                if (destY > borderHeight)
                    destY = borderHeight;
                if (destY < 0)
                    destY = 0;
                if (destX > borderWidth)
                    destX = borderWidth;
                if (destX < 0)
                    destX = 0;
            }

            // Randomly choose path to dest
            if (X != destX && rand.nextBoolean()) {
                int distance = X - destX;
                int distanceAbs = Math.abs(distance);
                int step = (distanceAbs < STEP_LENGTH ? distanceAbs : STEP_LENGTH);
                X += (distance > 0 ? -step : step);
            }
            if (Y != destY && rand.nextBoolean()) {
                int distance = Y - destY;
                int distanceAbs = Math.abs(distance);
                int step = (distanceAbs < STEP_LENGTH ? distanceAbs : STEP_LENGTH);
                Y += (distance > 0 ? -step : step);
            }

            SimulationFrame.simPanel.repaint();

            for (Accident accident : SimulationFrame.accidends) {
                if (Math.abs(accident.getX() - this.X) < REPORTING_RANGE
                        && Math.abs(accident.getY() - this.Y) < REPORTING_RANGE) {
                    Helpline.getInstance().getOperator().receiveReport(accident.getX(), accident.getY(),
                            accident.getType());
                }
            }

            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (InterruptedException e) {
            }
        }
    }

    public void paint(Graphics g) {
        g.fillRect(X, Y, OBJECT_WIDTH, OBJECT_HEIGHT);
    }

    public void threadStop() {
        threadShouldStop = true;
    }

    public void threadStart() {
        threadShouldStop = false;
        witnessThread = new Thread(witnessRunnable);
        witnessThread.start();
    }
}
