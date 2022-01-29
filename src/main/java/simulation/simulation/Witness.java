package simulation.simulation;

import java.awt.*;
import java.util.Random;

public class Witness {
    // Delay between each step and accident seek
    final int THREAD_SLEEP_TIME = 10;
    final int STEP_LENGTH = 1;
    // Range to choose a new destionation on map
    final int NEW_DEST_RANGE = 100;
    final int OBJECT_WIDTH = 10;
    final int OBJECT_HEIGHT = 10;

    // Component where witness is going to be drawn at
    Component c;

    static final Random rand = new Random();
    final Runnable witnessRunnable = new Runnable() {
        public void run() {
            witnessAction();
        }
    };

    static int borderWidth = -1, borderHeight = -1;
    int X, Y;
    int destX, destY;
    boolean threadShouldStop = false;
    Thread witnessThread;

    public static void setBorder(int borderWidth, int borderHeight) throws Exception {
        if (borderWidth < 1 || borderHeight < 1)
            throw new Exception("Border values must be greater");
        Witness.borderWidth = borderWidth;
        Witness.borderHeight = borderHeight;
    }

    public Witness(Component c) throws Exception {
        if (borderWidth == -1 || borderHeight == -1) {
            throw new Exception("Border not specified, use setBorder()");
        }

        X = destX = rand.nextInt(borderWidth);
        Y = destY = rand.nextInt(borderHeight);

        witnessThread = new Thread(witnessRunnable);
        witnessThread.start();

        this.c = c;
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

            c.repaint();

            // TODO: check for accidents and report to Operator

            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (InterruptedException e) {
            }
        }
    }

    public void paint(Graphics g) {
        g.fillRect(X, Y, OBJECT_WIDTH, OBJECT_HEIGHT);
    }

    void threadStop() {
        threadShouldStop = true;
    }
}
