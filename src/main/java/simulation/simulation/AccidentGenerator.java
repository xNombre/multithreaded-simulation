package simulation.simulation;

import java.util.Random;

import simulation.gui.SimulationFrame;

public class AccidentGenerator {
    private final int THREAD_SLEEP_MIN = 5000;
    private final int THREAD_SLEEP_MAX = 10000;
    private boolean threadShouldStop = false;
    private Thread accidentThread;
    private final AccidentFactory accidentFactory = new RandomAccidentFactory();
    private static final Random rand = new Random();

    final Runnable accidentRunnable = new Runnable() {
        @Override
        public void run() {
            accidentAction();
        }
    };

    public AccidentGenerator() {
        accidentThread = new Thread(accidentRunnable);
        accidentThread.start();
    }

    void accidentAction() {
        try {
            Thread.sleep(THREAD_SLEEP_MIN);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!threadShouldStop) {
            SimulationFrame.accidends.add(accidentFactory.getAccident());
            int sleepTime = rand.nextInt(THREAD_SLEEP_MAX - THREAD_SLEEP_MIN + 1) + THREAD_SLEEP_MIN;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
    }
}