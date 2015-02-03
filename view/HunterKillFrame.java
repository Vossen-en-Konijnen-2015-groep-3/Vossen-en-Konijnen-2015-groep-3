package view;

import java.awt.Container;

import javax.swing.JFrame;

import controller.Simulator;

public class HunterKillFrame extends JFrame {

	public HunterKillFrame(){
		Container contentPane = getContentPane();
		PieChartHunterKills component2 = new PieChartHunterKills(Simulator.simulator.getDeerKills(), Simulator.simulator.getFoxKills(), Simulator.simulator.getRabbitKills());
		contentPane.add(component2);
		pack();
		setVisible(true);
	}
}
