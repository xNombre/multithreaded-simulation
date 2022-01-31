package simulation.gui;

import simulation.simulation.Ambulance;
import simulation.simulation.Vehicle;
import simulation.simulation.VehicleDispatcher;
import simulation.simulation.Witness;
import simulation.simulation.Accident;
import simulation.simulation.AccidentGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationFrame extends JFrame {
    static List<Witness> witnesses = Collections.synchronizedList(new ArrayList<Witness>());
    public static List<Accident> accidends = Collections.synchronizedList(new ArrayList<Accident>());
    AccidentGenerator generator;

    Button startButton = new Button("Start");
    Button stopButton = new Button("Stop");
    Button resetButton = new Button("Reset");

    public static SimulationPanel simPanel = new SimulationPanel();
    JPanel butPanel = new JPanel();

    public static class SimulationPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Accident accident : accidends) {
                accident.paint(g);
            }

            for (Witness witness : witnesses) {
                witness.paint(g);
            }

            for(Vehicle veh : VehicleDispatcher.getInstance().vehicles) {
                veh.paint(g);
            }
        }
    }

    public SimulationFrame(final int width, final int height) throws Exception {
        super("Simulation");
        this.setSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        this.initializeProperties();

        simPanel.setBackground(Color.gray);

        startButton.setEnabled(false);
        startButton.addActionListener(e -> startSimulation());
        stopButton.addActionListener(e -> stopSimulation());
        resetButton.addActionListener(e -> resetSimulation());

        butPanel.add(startButton);
        butPanel.add(stopButton);
        butPanel.add(resetButton);

        this.add(butPanel, BorderLayout.NORTH);
        this.add(simPanel, BorderLayout.CENTER);

        // Hack height, should be calculated dynamically
        Witness.setBorder(800, 728);
        Accident.setBorder(800, 728);

        addObjectsToPanel();

    }

    void addObjectsToPanel() throws Exception {
        // Change this values later
        for (int i = 0; i < 50; i++) {
            witnesses.add(new Witness(simPanel));
        }

        generator = new AccidentGenerator();
    }

    private void initializeProperties() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private static class Button extends JButton {
        Button(String name) {
            this.setSize(200, 100);
            this.setVisible(true);
            this.setText(name);
        }
    }

    private void startSimulation() {
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        generator.startGenerator();
        for (Witness i : witnesses) {
            i.threadStart();
        }
        for (Vehicle i : VehicleDispatcher.getInstance().vehicles) {
            i.threadStart();
        }
    }

    private void stopSimulation() {
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        generator.stopGenerator();
        for (Witness i : witnesses) {
            i.threadStop();
        }
        for (Vehicle i : VehicleDispatcher.getInstance().vehicles) {
            i.threadStop();
        }
    }

    private void resetSimulation() {
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        witnesses.removeAll(witnesses);
        generator.startGenerator();
        VehicleDispatcher.getInstance().vehicles.clear();
        try {
            addObjectsToPanel();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
