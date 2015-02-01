package model;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class Grass extends Animal {
	
	//age the grass starts with
	private int age = 0;
	//after this age the grass can be spawned at adjacent locations
	private int breeding_Age = 5;
	//probability at which grass will spawn
	private double new_grass_probability = 0.25;
	//the age at which the grass will die
	private final int max_age = 100;
	
	public Grass (Field field, Location location){
		super(field, location);
	}

	@Override
	public void act(List<Animal> newAnimals) {
		// TODO Auto-generated method stub
		
	}
	
	

}
