package view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import model.Field;
import controller.Simulator;
/**
* This class shows the pie charts.
* @author Jan-Bert, Marcel, Robert en Jan
* @version 03-02-2015
**/

public class PopulationFrame extends JFrame {

	public PopulationFrame(){
		
		Container contentPane = getContentPane();
		Field field = Simulator.simulator.getField();
		MyComponent component = new MyComponent(field);
		contentPane.add(component);
		setPreferredSize(new Dimension(400, 400));
		pack();
		setVisible(true);
	}
}
