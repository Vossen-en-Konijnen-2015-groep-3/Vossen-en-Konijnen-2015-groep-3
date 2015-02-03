package model;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
/**
 * 
 * @author Jan-Bert, Marcel, Robert en Jan
 * @version 03-02-2015
 * 
 * This class is not used.
 * 
 * 
 */

public class Grass extends Animal {
	
	//age the grass starts with
	private int breeding_age;
	//after this age the grass can be spawned at adjacent locations
	private int breeding_Age = 5;
	//probability at which grass will spawn
	private double new_grass_probability = 0.25;
	//the age at which the grass will die
	private final int max_age = 100;
    // The maximum number of new grass
    private static int max_newGrass = 5;
    
    //age of the grass
    private int age;
	
    private static final Random rand = Randomizer.getRandom();
	
	public Grass (boolean randomAge, Field field, Location location){
		super(field, location);
		age = 0;
		if(randomAge) {
			age = rand.nextInt(max_age);
		}
	}
    
    public void act(List<Animal> newGrass)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newGrass);            
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation == null) { 
            	setLocation(newLocation);
            }
            else {
                setDead();
            }
        }
    }
    
    private void giveBirth(List<Animal> newGrass)
    {
        // New grass grows into adjacent locations
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Fox young = new Fox(false, field, loc);
            newGrass.add(young);
        }
    }
    
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= new_grass_probability) {
            births = rand.nextInt(max_newGrass) + 1;
        }
        return births;
    }
    
    private boolean canBreed()
    {
        return age >= breeding_age;
    }
        

    private void incrementAge()
    {
        age++;
        if(age > max_age) {
            setDead();
        }
    }
    
    
    //TODO NOT FINISHED, PROBABLY SOME BUGS
	
	

}
