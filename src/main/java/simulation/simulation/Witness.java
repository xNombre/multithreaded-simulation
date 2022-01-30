package simulation.simulation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class Witness {
    // Delay between each step and accident seek
    private static final int THREAD_SLEEP_TIME = 10;
    private static final int STEP_LENGTH = 1;
    // Range to choose a new destionation on map
    private static final int NEW_DEST_RANGE = 100;
    private static final int OBJECT_WIDTH = 10;
    private static final int OBJECT_HEIGHT = 10;
    private Shape shape;
    private AffineTransform aft;
    private Area area;
    private ArrayList<Accident> accidents;
    // Component where witness is going to be drawn at
    Component c;

    private static final Random rand = new Random();
    final Runnable witnessRunnable = new Runnable() {
        public void run() {
            witnessAction();
        }
    };

    private static int borderWidth = -1, borderHeight = -1;
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

    public Witness(Component c, ArrayList<Accident> accidents) throws Exception {
        this.accidents = accidents;

        if (borderWidth == -1 || borderHeight == -1) {
            throw new Exception("Border not specified, use setBorder()");
        }

        X = destX = rand.nextInt(borderWidth);
        Y = destY = rand.nextInt(borderHeight);
        shape = new Rectangle2D.Float(X, Y, OBJECT_WIDTH, OBJECT_HEIGHT);
        area = new Area(shape);

        witnessThread = new Thread(witnessRunnable);
        witnessThread.start();

        this.c = c;
    }

    void witnessAction() {
        while (!threadShouldStop) {
            aft = new AffineTransform();
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
                aft.translate((distance > 0 ? -step : step), 0);
            }
            if (Y != destY && rand.nextBoolean()) {
                int distance = Y - destY;
                int distanceAbs = Math.abs(distance);
                int step = (distanceAbs < STEP_LENGTH ? distanceAbs : STEP_LENGTH);
                Y += (distance > 0 ? -step : step);
                aft.translate(0, (distance > 0 ? -step : step));
            }
            area.transform(aft);
            this.checkAccidents();

            c.repaint();
//            System.out.println("x = " + X + "y = " + Y);
//            System.out.println(area.getBounds());

            // TODO: check for accidents and report to Operator

            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (InterruptedException e) {
            }
        }
    }

    private void checkAccidents() {
        for(Accident a : accidents) {
            if (this.area.intersects(a.getArea())) {
                a.report();
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
