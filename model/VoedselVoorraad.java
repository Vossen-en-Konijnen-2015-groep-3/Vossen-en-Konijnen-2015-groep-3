package model;

import java.util.Iterator;

import controller.Simulator;

public class VoedselVoorraad {

	private int basisVoorraad;
	
	public VoedselVoorraad(){
		
	}
	
	private int calculateHerbivores(){
		Iterator<Animal> iterator = Simulator.simulator.getAnimals().iterator();
		int herbivoreCount = 0;
		while(iterator.hasNext()){
			Animal animal = iterator.next();
			if(animal instanceof Rabbit || animal instanceof Deer){
				herbivoreCount++;
			}
		}
		
		return herbivoreCount;
	}
	
	public int calculateVoorraad(){
		
		return (basisVoorraad - calculateHerbivores());
	}
}
