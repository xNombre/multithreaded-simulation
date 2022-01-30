package simulation.accident;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Accident {

    private static final Random rand = new Random();


    private int X, Y;
    private final AccidentType type;
    private final ImageIcon icon;


    Accident(AccidentType type, ImageIcon icon) {
        this.type = type;
        this.icon = icon;

        X = rand.nextInt(600);
        Y = rand.nextInt(600);
    }

    public void paint(Graphics g) {
        g.drawImage(icon.getImage(), X, Y,30,30, null);
    }

}
