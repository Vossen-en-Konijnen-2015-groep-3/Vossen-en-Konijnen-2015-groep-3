package model;

import java.util.Iterator;

import controller.Simulator;

/**
 * This class tracks the amount of food used by deers and rabbits.
 * 
 * @author Jan-Bert, Marcel, Robert en Jan
 * @version 03-02-2015
 * 
 */

public class VoedselVoorraad {

	private int basisVoorraad;
	
	public VoedselVoorraad(Field field){
		basisVoorraad = (field.getDepth() * field.getWidth());
	}
	
	private int calculateHerbivores(){
		Iterator<Animal> iterator = Simulator.simulator.getAnimals().iterator();
		int herbivoreCount = 0;
		while(iterator.hasNext()){
			Actor animal = iterator.next();
			if(animal instanceof Rabbit || animal instanceof Deer){
				herbivoreCount++;
			}
		}
		
		return herbivoreCount;
	}
	
	public int calculateVoorraad(){
		
		return (basisVoorraad - calculateHerbivores());
	}
	
	public int getBasisVoorraad(){
		return basisVoorraad;
	}
}
