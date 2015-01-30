package model;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a deer.
 * deers age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Deer extends Animal
{
    // Characteristics shared by all deers (class variables).

    // The age at which a deer can start to breed.
    private static int BREEDING_AGE = 10;
    // The age to which a deer can live.
    private static int MAX_AGE = 60;
    // The likelihood of a deer breeding.
    private static double BREEDING_PROBABILITY = 0.05;
    // The maximum number of births.
    private static int MAX_LITTER_SIZE = 2;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // The deer's age.
    private int age;

    /**
     * Create a new deer. A deer may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the deer will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Deer(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
    /**
     * This is what the deer does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newdeers A list to return newly born deers.
     */
    public void act(List<Animal> newdeers)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newdeers);            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Increase the age.
     * This could result in the deer's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Check whether or not this deer is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newdeers A list to return newly born deers.
     */
    private void giveBirth(List<Animal> newdeers)
    {
        // New deers are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Deer young = new Deer(false, field, loc);
            newdeers.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A deer can breed if it has reached the breeding age.
     * @return true if the deer can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    
    public static int getBREEDING_AGE() {
		// TODO Auto-generated method stub
		return BREEDING_AGE;
	}

	public static int getMAX_AGE() {
		// TODO Auto-generated method stub
		return MAX_AGE;
	}

	public static double getBREEDING_PROBABILITY() {
		// TODO Auto-generated method stub
		return BREEDING_PROBABILITY;
	}

	public static int getMAX_LITTER_SIZE() {
		// TODO Auto-generated method stub
		return MAX_LITTER_SIZE;
	}
	
	public static void setBREEDING_AGE(int bREEDING_AGE) {
		BREEDING_AGE = bREEDING_AGE;
	}

	public static void setMAX_AGE(int mAX_AGE) {
		MAX_AGE = mAX_AGE;
	}

	public static void setBREEDING_PROBABILITY(double bREEDING_PROBABILITY) {
		BREEDING_PROBABILITY = bREEDING_PROBABILITY;
	}

	public static void setMAX_LITTER_SIZE(int mAX_LITTER_SIZE) {
		MAX_LITTER_SIZE = mAX_LITTER_SIZE;
	}
}
