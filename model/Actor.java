package model;

import java.util.List;

public interface Actor {

	/**
	 * Make this animal act - that is: make it do
	 * whatever it wants/needs to do.
	 * @param newAnimals A list to receive newly born animals.
	 */
	abstract public void act(List<Animal> newAnimals);

}