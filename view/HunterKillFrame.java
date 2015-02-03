package view;

import java.awt.Container;

import javax.swing.JFrame;

import controller.Simulator;

/**
 * The frame in which the pie chart is to respresent the hunter kills.
 * 
 * @author Jan-Bert, Marcel, Robert en Jan
 * @version 03-02-2015
 * 
 */

public class HunterKillFrame extends JFrame {

	public HunterKillFrame(){
		Container contentPane = getContentPane();
		PieChartHunterKills component2 = new PieChartHunterKills(Simulator.simulator.getDeerKills(), Simulator.simulator.getFoxKills(), Simulator.simulator.getRabbitKills());
		contentPane.add(component2);
		pack();
		setVisible(true);
	}
}
