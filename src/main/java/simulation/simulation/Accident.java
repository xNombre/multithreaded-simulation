package simulation.simulation;
import java.awt.*;
public class Accident {

	int x,y;
	Thread accidentThread;
	boolean threadShouldStop;
	AccidentType type;
	Color color;

	Accident(AccidentType type){
	this.type = type;
	//To ktos mo¿e jakimœ ikonkami zast¹piæ
	if(type == AccidentType.FIRE) {
		color = Color.red;
	}
	else if(type == AccidentType.HEALTH_HAZARD) {
		color = Color.green;
	}
	else if(type == AccidentType.TRAFFIC_ACCIDENT) {
		color = Color.black;
	}
	else {
		color = Color.blue;
	}
    x = Witness.rand.nextInt(600);
    y = Witness.rand.nextInt(600);

	}
	
	
    public void paint(Graphics g) {
    	g.setColor(color);
        g.fillOval(x, y, 30, 30);
        g.setColor(Color.black);
    }

}
