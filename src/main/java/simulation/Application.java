package simulation;

import simulation.gui.SimulationFrame;

import javax.swing.*;

public class Application {

    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            var simulationFrame = new SimulationFrame(800,800);
        });

    }

}
