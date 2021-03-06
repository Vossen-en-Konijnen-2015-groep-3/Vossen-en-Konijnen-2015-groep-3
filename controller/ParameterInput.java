package controller;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;

import model.Fox;
import model.Rabbit;
import model.Deer;
import model.Hunter;
import model.Field;
import model.FieldStats;

import view.MyComponent;
import view.PieChartHunterKills;

/**
 * Parameter Input class
 * In this class you can edit values of foxes, rabbits and deers through pop-ups.
 * 
 * @author Jan-Bert, Marcel, Robert en Jan
 * @version 1.0
 * 
 */

public class ParameterInput {
	//vars
	private JFrame frame;
	private int breedingAge;
	private int maxAge;
	private	double breedingProbability;
	private int litterSize;
	private JLabel nameLabel;
	private JLabel BALabel = new JLabel("Vul de leeftijd in waarop de dieren beginnen met voortplanten [e.g. 5]");
	private JLabel MALabel = new JLabel("Vul de leeftijd in waarop de dieren sterven [e.g. 18]");
	private JLabel BPLabel = new JLabel("Vul de kans van voortplanting in [e.g. 0.36]");
	private JLabel LSLabel = new JLabel("Vul de het maximum aantal geboortes per dier per stap in [e.g. 3]");
	private JLabel AuthorLabel1 = new JLabel("<html>De simulatie applicatie is gemaakt door:"
			+ "<br>Jan-Bert van Slochteren"
			+ "<br>Robert van Timmeren"
			+ "<br>Jan Osinga"
			+ "<br>Marcel Oostebring"
			+ "</html>");

	private JLabel imageLabel;
	private JTextField BAText = new JTextField();
	private JTextField MAText = new JTextField();
	private JTextField BPText = new JTextField();
	private JTextField LSText = new JTextField();
	private MyComponent component;
	private PieChartHunterKills component2;
	
	
	public ParameterInput(){
		//omitted
	}
	
	/**
	 * Creates a JFrame in which various parameters of the animals can be adjusted.
	 * @param animal
	 */
	public ParameterInput(final String animal) {
		//TODO fix all this BS;
		setVariables(animal);
		setText();
		//TODO textfields etc.;
		frame = new JFrame();
		//variable for max age
		JButton OK = new JButton("OK");
		JButton reset = new JButton("Reset");
		
		
		OK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//TODO get text, filter text, update vars;
				String BAString = BAText.getText();
				//variable for breeding age 
				String MAString = MAText.getText();
				//variable for breeding probability
				String BPString = BPText.getText();
				//variable for litter size
				String LSString = LSText.getText();
				//Set Strings to usable types;
				try{
				Integer BAInteger = new Integer(BAString);
				Integer MAInteger = new Integer(MAString);
				Double BPInteger = new Double(BPString);
				Integer LSInteger = new Integer(LSString);
				
				System.out.println(BAInteger.intValue());
				System.out.println(MAInteger.intValue());
				System.out.println(BPInteger.doubleValue());
				System.out.println(LSInteger.intValue());
				
				if(animal.equals("Fox")){
					//Fox BS
					Fox.setBREEDING_AGE(BAInteger.intValue());
					Fox.setMAX_AGE(MAInteger.intValue());
					Fox.setBREEDING_PROBABILITY(BPInteger.doubleValue());
					Fox.setMAX_LITTER_SIZE(LSInteger.intValue());
					System.out.println("FOX aangepast");
				}
				if(animal.equals("Rabbit")){
					//Rabbit BS
					Rabbit.setBREEDING_AGE(BAInteger.intValue());
					Rabbit.setMAX_AGE(MAInteger.intValue());
					Rabbit.setBREEDING_PROBABILITY(BPInteger.doubleValue());
					Rabbit.setMAX_LITTER_SIZE(LSInteger.intValue());
					System.out.println("rabbit aangepast");
				}
				if(animal.equals("Deer")){
					//Chicken BS
					Deer.setBREEDING_AGE(BAInteger.intValue());
					Deer.setMAX_AGE(MAInteger.intValue());
					Deer.setBREEDING_PROBABILITY(BPInteger.doubleValue());
					Deer.setMAX_LITTER_SIZE(LSInteger.intValue());
					System.out.println("chicken aangepast");
				}
				
				//closes the frame
				frame.dispose();
				}
				catch(NumberFormatException except){
					JOptionPane.showMessageDialog(null, "De ingevoerde getallen zijn niet van het juiste formaat.");
					
				}
				
			}
		});
		//reset the parameters of the animal if they haven't been saved yet.
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setText();
			}
		});
		//adding everything to the frame
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
		labels.add(nameLabel);
		labels.add(BALabel);
		labels.add(BAText);
		labels.add(MALabel);
		labels.add(MAText);
		labels.add(BPLabel);
		labels.add(BPText);
		labels.add(LSLabel);
		labels.add(LSText);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS) );
		buttons.add(OK);
		buttons.add(Box.createHorizontalStrut(10));
		buttons.add(reset);
		contentPane.add(labels, BorderLayout.WEST);
		contentPane.add(buttons, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	/**
	 * Sets the current parameters of the animal in the textfields
	 */
	private void setText(){
		BAText.setText(new String().valueOf(breedingAge));
		MAText.setText(new String().valueOf(maxAge));
		BPText.setText(new String().valueOf(breedingProbability));
		LSText.setText(new String().valueOf(litterSize));
	}
	
	/**
	 * Sets the variables for the animal that is spicified.
	 * @param animal
	 */
	private void setVariables(String animal){
		if(animal.equals("Fox")){
			//Fox BS
			nameLabel = new JLabel("Vossen");
	        breedingAge = Fox.getBREEDING_AGE();
			maxAge = Fox.getMAX_AGE();
			breedingProbability = Fox.getBREEDING_PROBABILITY();
			litterSize = Fox.getMAX_LITTER_SIZE();
		}
		if(animal.equals("Rabbit")){
			//Rabbit BS
			nameLabel = new JLabel("Konijnen");
			breedingAge = Rabbit.getBREEDING_AGE();
			maxAge = Rabbit.getMAX_AGE();
			breedingProbability = Rabbit.getBREEDING_PROBABILITY();
			litterSize = Rabbit.getMAX_LITTER_SIZE();
		}
		if(animal.equals("Deer")){
			//Deers BS
			nameLabel = new JLabel("Herten");
			breedingAge = Deer.getBREEDING_AGE();
			maxAge = Deer.getMAX_AGE();
			breedingProbability = Deer.getBREEDING_PROBABILITY();
			litterSize = Deer.getMAX_LITTER_SIZE();
		}
		if(animal.equals("piechartPopulation")){
			JFrame test = new JFrame();
			Container contentPane = test.getContentPane();
			Field field = Simulator.simulator.getField();
			component = new MyComponent(field);
			contentPane.add(component);
			test.pack();
			test.setVisible(true);
		}
		
		if(animal.equals("piechartHunterKills")){
			JFrame test = new JFrame();
			Container contentPane = test.getContentPane();
			component2 = new PieChartHunterKills(Simulator.simulator.getDeerKills(), Simulator.simulator.getFoxKills(), Simulator.simulator.getRabbitKills());
			contentPane.add(component2);
			test.pack();
			test.setVisible(true);
		}
		if(animal.equals("barchart")){
			
		}
		
		if(animal.equals("information")){
			JFrame test = new JFrame();
			Container contentPane = test.getContentPane();
			contentPane.add(AuthorLabel1);
			test.pack();
			test.setVisible(true);
		}
	}
	
}
