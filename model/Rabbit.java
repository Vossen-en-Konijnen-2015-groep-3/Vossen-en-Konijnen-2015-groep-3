package model;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 * 
 * fixed account
 */
public class Rabbit extends Animal
{
    // Characteristics shared by all rabbits (class variables).
	// private static int FOOD_LEVEL = 1000;
    // The age at which a rabbit can start to breed.
    private static int BREEDING_AGE = 1;
    // The age to which a rabbit can live.
    private static int MAX_AGE = 4000;
    // The likelihood of a rabbit breeding.
    private static double BREEDING_PROBABILITY = 0.15;
    // The maximum number of births.
    private static int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // The rabbit's age.
    private int age;
    
    private final double CHANCE_ON_DISEASE = 0.00;
    private static final int MAX_DAYS_diseased = 5;
    private int current_days_diseased = 0;
    
    // true/false is the rabbit diseased?
    private boolean Rabbit_Disease;
    //chance the rabbit is immune to disease
    private static int CHANCE_ON_IMMUNITY = 10;
    //true/false is the rabbit immune to disease?
    private boolean immune;

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        //FOOD_LEVEL = 1000;
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
	/**
	 * Set the rabbit to have the disease if it is not immune
	 */
	public void getDisease() {
		Rabbit_Disease = !immune;
	}

	/**
	 * 
	 * @return true if the rabbit has the disease.
	 * 
	 */
	public boolean diseasedRabbit() {
		return Rabbit_Disease;
	}
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to return newly born rabbits.
     */
    public void act(List<Animal> newRabbits)
    {
    	//incrementHunger();
        incrementAge();
        if(isAlive()) {
            giveBirth(newRabbits);  
            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
            	newLocation = getField().freeAdjacentLocation(getLocation());
            }
            else {
                // Overcrowding.
                setDead();
            }
            
        }
    }

//--------------------------------------------------------------------------------------------------------------------------          
           /** if (diseasedRabbit() == true) {
            	//TODO add Actor class and fix field errors
            	if (getLocation() != null && field.getNearbyRabbits(getLocation()) != null) {
            		Object otherRabbit = field.getObjectAt(field.getNearbyRabbits(getLocation()));
            	    if (otherRabbit != null) {
            	    	if (otherRabbit instanceof Rabbit) {
            	    		Rabbit closebyRabbit = (Rabbit) otherRabbit;
            	    		closebyRabbit.getDisease();
            	    	}
            	    }
            	    
            	    current_days_diseased++;
            	    
            	    }
            	}
            **/
//--------------------------------------------------------------------------------------------------------------------------       


    /**
     * Increase the age.
     * This could result in the rabbit's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    
    /**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newRabbits A list to return newly born rabbits.
     */
    private void giveBirth(List<Animal> newRabbits)
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Rabbit young = new Rabbit(false, field, loc);
            newRabbits.add(young);
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
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
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
