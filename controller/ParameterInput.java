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

import model.Fox;
import model.Rabbit;
import model.Deer;

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
	private JLabel imageLabel;
	private JTextField BAText = new JTextField();
	private JTextField MAText = new JTextField();
	private JTextField BPText = new JTextField();
	private JTextField LSText = new JTextField();
	
	
	public ParameterInput(){
		//omitted
	}
	
	/**
	 * no idea of what this should be and do
	 * TODO ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	 * @param animal
	 */
	public ParameterInput(final String animal) {
		//TODO fix all this BS;
		//
		setVariables(animal);
		setText();
		//TODO textfields etc.;
		frame = new JFrame();
		
		JButton OK = new JButton("OK");
		JButton reset = new JButton("Reset");
		OK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//TODO get text, filter text, update vars;
				String BAString = BAText.getText();
				String MAString = MAText.getText();
				String BPString = BPText.getText();
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
				
				frame.dispose();
				}
				catch(NumberFormatException except){
					JOptionPane.showMessageDialog(null, "De ingevoerde getallen zijn niet van het juiste formaat.");
					
				}
				
			}
		});
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setText();
				
				///////////
			}
		});
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
		labels.add(imageLabel);
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
	
	private void setText(){
		BAText.setText(new String().valueOf(breedingAge));
		MAText.setText(new String().valueOf(maxAge));
		BPText.setText(new String().valueOf(breedingProbability));
		LSText.setText(new String().valueOf(litterSize));
	}
	
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
			//Chicken BS
			nameLabel = new JLabel("Herten");
			breedingAge = Deer.getBREEDING_AGE();
			maxAge = Deer.getMAX_AGE();
			breedingProbability = Deer.getBREEDING_PROBABILITY();
			litterSize = Deer.getMAX_LITTER_SIZE();
		}
	}
	
}
