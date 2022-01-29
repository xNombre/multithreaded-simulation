package simulation.gui;

import simulation.simulation.Witness;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationFrame extends JFrame {

    static ArrayList<Witness> witnesses = new ArrayList<>();

    private static class SimulationPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for(Witness witness : witnesses){
                witness.paint(g);
            }
        }
    }

    public SimulationFrame(final int width, final int height) throws Exception {
        super("Simulation");
        this.setSize(new Dimension(width, height));

        this.initializeProperties();

        SimulationPanel panel = new SimulationPanel();
        this.add(panel);

        Witness.setBorder(600, 600);
        for (int i = 0; i < 20; i++) {
            witnesses.add(new Witness(panel));
        }

    }

    private void initializeProperties(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
