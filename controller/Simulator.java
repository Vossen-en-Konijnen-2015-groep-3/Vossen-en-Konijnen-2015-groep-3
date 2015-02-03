package controller;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

import model.Animal;
import model.Field;
import model.Fox;
import model.Deer;
import model.Hunter;
import model.Location;
import model.Rabbit;
import model.Randomizer;
import model.VoedselVoorraad;
import view.SimulatorView;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author Jan-Bert, Marcel, Robert en Jan
 * @version 03-02-2015
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.06;
    // The probability that a deer will be created in any given grid position.
    private static final double DEER_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.16; 
    // The probability that a rabbit will be created in any given grid position.
    private static final double HUNTER_CREATION_PROBABILITY = 0.001; 

    private int deerKills = 0;
    private int foxKills = 0;
    private int rabbitKills = 0;

    // List of animals in the field.
    private List<Animal> animals;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    
    //De simulator
    public static Simulator simulator;
    
    //De voedselvoorraad
    private VoedselVoorraad voedselVoorraad;
    
    private double foodBreedingModifier = 0.0;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        animals = new ArrayList<Animal>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(Rabbit.class, Color.orange);
        view.setColor(Fox.class, Color.blue);
        view.setColor(Deer.class, Color.green);
        view.setColor(Hunter.class, Color.red);
        
        // Setup a valid starting point.
        reset();
        
        //voeg toe aan de variabel simulator.
        simulator = this;
        voedselVoorraad = new VoedselVoorraad(field);
    }
    
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public synchronized void simulateOneStep()
    {
        step++;

        // Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<Animal>();        
        // Let all rabbits act.
        for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = it.next();
            animal.act(newAnimals);
            if(! animal.isAlive()) {
                it.remove();
            }
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        animals.addAll(newAnimals);

        view.showStatus(step, field);
        setFoodBreedingModifier(((1.0/ new Integer(Simulator.simulator.getVoedselVoorraad().getBasisVoorraad()).doubleValue())* new Integer(Simulator.simulator.getVoedselVoorraad().calculateVoorraad()).doubleValue())-0.50);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        animals.clear();
        populate();
        
        // Show the starting state in the view.
        view.showStatus(step, field);
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
    	int huntersCount = 0;
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
            	Location location = new Location(row, col);
            	if(rand.nextDouble() <= HUNTER_CREATION_PROBABILITY && huntersCount <= 9) {
            		//Location location = new Location(row, col);
                    Hunter hunter = new Hunter(field, location);
                    animals.add(hunter);
                    huntersCount ++;
                    
                }else if(rand.nextDouble() <= DEER_CREATION_PROBABILITY) {
            		//Location location = new Location(row, col);
            		Deer deer = new Deer(true, field, location);
            		animals.add(deer);
                }else if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    //Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    animals.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                   // Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    animals.add(rabbit);
                } 
            	
                // else leave the location empty.
            }
        }
    }
    
    public void startDecease(){
    	
    	int ziek = 0;
    	
    		for(Animal x : getAnimals()){
    			if(x instanceof Rabbit && ziek <= 10){
    				((Rabbit) x).setDisease();
    				ziek ++;
    				
    			}
    		}
    	
    }
    
    public void updateKills(){
    	for(Animal x : getAnimals()){
    		if(x instanceof Hunter){
    			deerKills += ((Hunter) x).getDeerKills();
    			foxKills += ((Hunter) x).getFoxKills();
    			rabbitKills += ((Hunter) x).getRabbitKills();	
    		}
    	}
    }
    
    public int getDeerKills(){
    	if (deerKills > 0){
    		return deerKills;
    	}else{ 
    		return 0;
    	}
    }
    
    public int getFoxKills(){
    	if (foxKills > 0){
    		return foxKills;
    	}else{ 
    		return 0;
    	}
    }
    
    public int getRabbitKills(){
    	if (rabbitKills > 0){
	    	return rabbitKills;
    	}else{ 
    		return 0;
    	}
    } 

    public List<Animal> getAnimals(){
    	return animals;
    }
    
    public VoedselVoorraad getVoedselVoorraad(){
    	return voedselVoorraad;
    }
    
    public Field getField(){
    	return field;
    }

	public double getFoodBreedingModifier() {
		return foodBreedingModifier;
	}

	public void setFoodBreedingModifier(double foodBreedingModifier) {
		this.foodBreedingModifier = foodBreedingModifier;
	}
    
}
