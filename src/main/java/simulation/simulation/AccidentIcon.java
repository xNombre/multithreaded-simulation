package simulation.simulation;

import javax.swing.*;
import java.util.AbstractMap;
import java.util.Map;

public class AccidentIcon {

     static final ImageIcon fireIcon = new ImageIcon(AccidentIcon.class.getResource("/fire.png"));
     static final ImageIcon healthHazardIcon = new ImageIcon(AccidentIcon.class.getResource("/healthHazard.png"));
     static final ImageIcon robberyIcon = new ImageIcon(AccidentIcon.class.getResource("/robbery.png"));
     static final ImageIcon trafficAccidentIcon = new ImageIcon(AccidentIcon.class.getResource("/traffAcc.png"));
     static final Map<AccidentType, ImageIcon> icons =
            Map.ofEntries(new AbstractMap.SimpleEntry<>(AccidentType.FIRE, fireIcon),
                    new AbstractMap.SimpleEntry<>(AccidentType.HEALTH_HAZARD, healthHazardIcon),
                    new AbstractMap.SimpleEntry<>(AccidentType.ROBBERY, robberyIcon),
                    new AbstractMap.SimpleEntry<>(AccidentType.TRAFFIC_ACCIDENT, trafficAccidentIcon));

    public static ImageIcon getAssociatedAccidentIcon(AccidentType accidentType){
        return icons.get(accidentType);
    }
    public static int getImageIconsCount(){
        return icons.size();
    }

}
