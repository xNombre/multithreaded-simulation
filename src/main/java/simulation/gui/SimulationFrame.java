package simulation.gui;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {


    public SimulationFrame(final int width, final int height) throws HeadlessException {
        super("Simulation");
        this.setSize(new Dimension(width, height));
        this.initializeProperties();

        var helloLabel = new Label("Hello, simulatjones!");
        helloLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        this.add(helloLabel, BorderLayout.NORTH);
    }

    private void initializeProperties(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
