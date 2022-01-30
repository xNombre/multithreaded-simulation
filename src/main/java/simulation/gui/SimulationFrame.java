package simulation.gui;

import simulation.simulation.Ambulance;
import simulation.simulation.Vehicle;
import simulation.simulation.Witness;
import simulation.simulation.Accident;
import simulation.simulation.AccidentGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationFrame extends JFrame {

    static ArrayList<Witness> witnesses = new ArrayList<>();
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    public static ArrayList<Accident> accidents = new ArrayList<>();
    AccidentGenerator generator;

    private static class SimulationPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Accident accident : accidents) {
                accident.paint(g);
            }

            for (Witness witness : witnesses) {
                witness.paint(g);
            }

            for (Vehicle vehicle : vehicles) {
                vehicle.paint(g);
            }
        }
    }

    public SimulationFrame(final int width, final int height) throws Exception {
        super("Simulation");
        this.setSize(new Dimension(width, height));

        this.initializeProperties();

        SimulationPanel panel = new SimulationPanel();
        panel.setBackground(Color.gray);
        this.add(panel);

        // Change this values later
        Witness.setBorder(600, 600);
        for (int i = 0; i < 50; i++) {
            witnesses.add(new Witness(panel, accidents));
        }

        for (int i = 0; i < 1; i++) {
            vehicles.add(new Ambulance(100, 100, panel));
        }

        generator = new AccidentGenerator();
    }

    private void initializeProperties() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
