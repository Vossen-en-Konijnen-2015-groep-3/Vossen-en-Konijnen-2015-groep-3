package model;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

import sounds.Boom;
import view.SimulatorView;

/**
 * A simple model of a hunter.
 * The hunter hunts all animals and can die from a random chance
 * of having a tree fall on his head. If a tree does fall on him
 * a sound from sounds.Boom will play.
 * 
 * 
 * @author Jan-Bert, Marcel, Robert en Jan
 * @version 03-02-2015
 */
public class Hunter extends Animal
{
    // the chance a hunter gets hit by a falling tree. (do not increase this by too much....)
    private static double FALLING_TREE_PROBABILITY = 0.001;
    
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    // The hunter gains a kill for ever kill (quite obvious).
    private int rabbitKills;
    private int deerKills;
    private int foxKills;
    

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
                //fallingTree();
            }
            // See if it was possible to move.
            if(newLocation != null && rand.nextDouble() > FALLING_TREE_PROBABILITY) {
                setLocation(newLocation);
            }else {
                fallingTree();
                }
            
        }else{
            	// a tree fell on the hunter
            	fallingTree();
            }
        }
    
    /**
     * A method to kill the hunter (a tree falls on him)
     * and play sound of a tree falling.
     */
    private void fallingTree()
    {
    		setDead();
    		try {
				sounds.Boom.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}

    }
    
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
                    rabbitKills ++;
                    return where;
                }
             // else if animal is a deer, shoot it aswell.
            }else if(animal instanceof Deer) {
             Deer deer = (Deer) animal;
             
	             if(deer.isAlive()) {
		              
		            deer.setDead();
		            deerKills ++;
		            return where;
	             }
            }else if(animal instanceof Fox) {
                Fox fox = (Fox) animal;
                
	             if(fox.isAlive()) {
		              
		            fox.setDead();
		            foxKills ++;
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
	
	public int getDeerKills() {
		return deerKills;
	}
	public int getFoxKills() {
		return foxKills;
	}
	public int getRabbitKills() {
		return rabbitKills;
	}
	

}