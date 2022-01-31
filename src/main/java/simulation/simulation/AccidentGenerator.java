package simulation.simulation;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import simulation.gui.SimulationFrame;

public class AccidentGenerator {
    private final int THREAD_SLEEP_MIN = 5000;
    private final int THREAD_SLEEP_MAX = 10000;
    private final AccidentFactory accidentFactory = new RandomAccidentFactory();
    private static final Random rand = new Random();
    private Timer timer = new Timer();

    private class Task extends TimerTask {
        @Override
        public void run() {
            SimulationFrame.accidends.add(accidentFactory.getAccident());
            int sleepTime = rand.nextInt(THREAD_SLEEP_MAX - THREAD_SLEEP_MIN + 1) + THREAD_SLEEP_MIN;
            timer.schedule(new Task(), sleepTime);
        }
    }

    public AccidentGenerator() {
        timer.schedule(new Task(), THREAD_SLEEP_MIN);
    }

    public void stopGenerator() {
        timer.cancel();
    }

    public void startGenerator() {
        this.timer = new Timer();
        timer.schedule(new Task(), THREAD_SLEEP_MIN);
    }
}