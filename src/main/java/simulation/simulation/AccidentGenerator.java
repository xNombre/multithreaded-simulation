package simulation.simulation;

import java.awt.Component;
import java.util.ArrayList;
import java.awt.event.*;

import java.util.Timer;
import java.util.TimerTask;

import simulation.gui.SimulationFrame;

public class AccidentGenerator{
	
	private static final int ACCIDENT_RATE = 3000;
	private static final int TIME_OF_FIRST_INCIDENT = 1000;


	
	Timer timer;
	TimerTask task;
	
	public AccidentGenerator(){
		timer = new Timer();
		task =  new TimerTask() {
			@Override
			public void run() {
				SimulationFrame.accidends.add(new Accident(AccidentType.randomAccident()));
			}
		};
		timer.scheduleAtFixedRate(task,TIME_OF_FIRST_INCIDENT,ACCIDENT_RATE);
	}
}