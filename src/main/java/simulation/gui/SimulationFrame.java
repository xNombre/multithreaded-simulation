package simulation.gui;

import simulation.simulation.Ambulance;
import simulation.simulation.Vehicle;
import simulation.simulation.Witness;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationFrame extends JFrame {

    static ArrayList<Witness> witnesses = new ArrayList<>();
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    Button startButton = new Button("Start");
    Button stopButton = new Button("Stop");
    Button resetButton = new Button("Reset");

    SimulationPanel simPanel = new SimulationPanel();
    JPanel butPanel = new JPanel();


    private static class SimulationPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for(Witness witness : witnesses){
                witness.paint(g);
            }

            for(Vehicle vehicle : vehicles) {
                vehicle.paint(g);
            }
        }
    }

    public SimulationFrame(final int width, final int height) throws Exception {
        super("Simulation");
        this.setSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

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

        addObjectsToPanel();

    }

    void addObjectsToPanel() throws Exception {
        // Change this values later
        Witness.setBorder(600, 600);
        for (int i = 0; i < 10; i++) {
            witnesses.add(new Witness(simPanel));
        }

        for (int i = 0; i < 1; i++) {
            vehicles.add(new Ambulance(100,100, simPanel));
        }
    }

    private void initializeProperties(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private static class Button extends JButton{
        Button(String name){
            this.setSize(200,100);
            this.setVisible(true);
            this.setText(name);
        }
    }

    private void startSimulation() {
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        for (Witness i : witnesses){
            i.threadStart();
        }
        for (Vehicle i : vehicles){
            i.threadStart();
        }
    }

    private void stopSimulation() {
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        for (Witness i : witnesses){
            i.threadStop();
        }
        for (Vehicle i : vehicles){
            i.threadStop();
        }
    }

    private void resetSimulation() {
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        witnesses.removeAll(witnesses);
        vehicles.removeAll(vehicles);
        try {
            addObjectsToPanel();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
