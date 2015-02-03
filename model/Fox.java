package model;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 * 
 * @author Jan-Bert, Marcel, Robert en Jan
 * @version 30-01-2015
 */
public class Fox extends Animal
{
    // Characteristics shared by all foxes (class variables).
    
    // The age at which a fox can start to breed.
    private static int BREEDING_AGE = 15;
    // The age to which a fox can live.
    private static int MAX_AGE = 150;
    // The likelihood of a fox breeding.
    private static double BREEDING_PROBABILITY = 0.08;
    // The maximum number of births.
    private static int MAX_LITTER_SIZE = 2;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    private static int RABBIT_FOOD_VALUE = 9;
    
    // The food value of a single deer. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    private static final int DEER_FOOD_VALUE = 7;
    
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The fox's age.
    private int age;
    // The fox's food level, which is increased by eating rabbits.
    private int foodLevel;

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Fox(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
        }
        else {
            age = 0;
            foodLevel = RABBIT_FOOD_VALUE;
        }
    }
    
    /**
     * This is what the fox does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newFoxes A list to return newly born foxes.
     */
    public void act(List<Animal> newFoxes)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newFoxes);            
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
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
     * Increase the age. This could result in the fox's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            // if animal is a rabbit, eat it.
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }else if(animal instanceof Deer) {
             Deer deer = (Deer) animal;
             
             if(deer.isAlive()) {
	              
	            deer.setDead();
	            foodLevel = DEER_FOOD_VALUE;
	            return where;
	              
              
             }
            
            }
        }
        return null;
    }
    
    /**
     * Check whether or not this fox is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newFoxes A list to return newly born foxes.
     */
    private void giveBirth(List<Animal> newFoxes)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Fox young = new Fox(false, field, loc);
            newFoxes.add(young);
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
     * A fox can breed if it has reached the breeding age.
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