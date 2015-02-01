package model;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a hunter.
 * 
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Hunter extends Animal
{
    // the chance a hunter gets hit by a falling tree. (do not increase this by too much....)
    private static double FALLING_TREE_PROBABILITY = 0.001;
    
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    // The hunter gains a kill for ever kill (quite obvious).
    private int kills;

    /**
     * Create a hunter. Hunters are not hungry and cannot die or be eaten by foxes.
     * Also, hunters can actually die by a tree falling on them.
     * 
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Hunter(Field field, Location location)
    {
        super(field, location);
        
    }
    
    /**
     * A hunter hunts rabbits, foxes and deers. 
     * 
     * 
     * @param field The field currently occupied.
     * @param newHunters A list to return newly born foxes.
     */
    public void act(List<Animal> newHunters)
    {

        if(isAlive()) {           
            // Move towards an animal if it can be found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
                fallingTree();
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
                fallingTree();
            }
            else if(rand.nextDouble() <= FALLING_TREE_PROBABILITY)
            	// a tree fell on the hunter
            	fallingTree();
            }
        }
    

    private void fallingTree()
    {
    	if(rand.nextDouble() <= FALLING_TREE_PROBABILITY){
    		setDead();
    	}
    }
    
    /**
     * @return Where an animal was found, or null if it wasn't an animal or the location was empty.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
           
            // if animal is a rabbit, shoot it.
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    // Remove the dead rabbit from the field.
                    kills ++;
                    return where;
                }
             // else if animal is a deer, shoot it aswell.
            }else if(animal instanceof Deer) {
             Deer deer = (Deer) animal;
             
	             if(deer.isAlive()) {
		              
		            deer.setDead();
		            kills ++;
		            return where;
	             }
            }else if(animal instanceof Fox) {
                Fox fox = (Fox) animal;
                
	             if(fox.isAlive()) {
		              
		            fox.setDead();
		            kills ++;
		            return where;
	             }
           }else if(animal instanceof Hunter) {
		         return null;
	             
          }
        }
        return null;
    }
    

	public static double getFALLING_TREE_PROBABILITY() {
		return FALLING_TREE_PROBABILITY;
	}
	
	public int getKills() {
		return kills;
	}
	

}