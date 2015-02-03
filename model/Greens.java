package model;

import java.util.List;

/**
 * 
 * @author Jan-Bert, Marcel, Robert en Jan
 * @version 03-02-2015
 * 
 */

public class Greens implements Actor {

	private int size = 5;
	private int maxSize = 5;
	
	@Override
	public void act(List<Animal> newAnimals) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Gras groeit terug.
	 * @param growBack
	 */
	private void Growback(int growBack){
		size = size + growBack;
		if(size>maxSize){
			size = maxSize;
		}
	}
	
	/**
	 * 
	 * @param eaten
	 * @return true als er genoeg eten is andes false
	 */
	public boolean eaten(int eaten){
		if(size - eaten>=0){
			size = size - eaten;
			return true;
		}
		else{
			return false;
		}
	}

}
