package simulation;

import simulation.gui.SimulationFrame;

import javax.swing.*;

public class Application {

    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            try {
                var simulationFrame = new SimulationFrame(800,800);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
