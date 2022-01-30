package simulation.simulation;

import java.util.Random;

import simulation.gui.SimulationFrame;

public class AccidentGenerator {
    private static final int THREAD_SLEEP_MIN = 5000;
    private static final int THREAD_SLEEP_MAX = 10000;
    private static final Random rand = new Random();
    boolean threadShouldStop = false;
    Thread accidentThread;

    final Runnable accidentRunnable = new Runnable() {
        @Override
        public void run() {
            accidentAction();
        }
    };

    void accidentAction() {
        try {
            Thread.sleep(THREAD_SLEEP_MIN);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!threadShouldStop) {
            SimulationFrame.accidends.add(new Accident(AccidentType.getRandomAccident(AccidentType.class)));
            int sleepTime = rand.nextInt(THREAD_SLEEP_MAX - THREAD_SLEEP_MIN + 1) + THREAD_SLEEP_MIN;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
    }

    public AccidentGenerator() {
        accidentThread = new Thread(accidentRunnable);
        accidentThread.start();
    }
}