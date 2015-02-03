package view;

import java.awt.Container;

import javax.swing.JFrame;

import model.Field;
import controller.Simulator;

public class PopulationFrame extends JFrame {

	public PopulationFrame(){
		
		Container contentPane = getContentPane();
		Field field = Simulator.simulator.getField();
		MyComponent component = new MyComponent(field);
		contentPane.add(component);
		pack();
		setVisible(true);
	}
}
