package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * Class used to store information used by SimulatorView's different views, as
 * well as implement methods used by it.
 * 
 */
public class View extends JPanel {
	protected final int GRID_VIEW_SCALING_FACTOR = 6;
	//dimensions of jpanel component
	protected int gridWidth, gridHeight;
	protected int xScale, yScale;
	protected Dimension size;
	protected HashMap<String, Integer> populationInfo;
	//for drawing GUI components
	protected Graphics g;
	protected Image graphImage;
	
	/**
	 * 
	 * @param height The height of the view
	 * @param width The width of the view
	 */
	public View(int height, int width) {
		//setting jpanel form visible
		this.setVisible(true);
		//dimensions of jpanel
		gridHeight = height;
		gridWidth = width;
		size = new Dimension(0, 0);
		//setting bounds of panel
		setBounds(100, 100, width, height);
	}

	/**
	 * Tell the GUI manager how big we would like to be.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR, gridHeight * GRID_VIEW_SCALING_FACTOR);
	}

	/**
	 * Prepare for a new round of painting. Since the component may be resized,
	 * compute the scaling factor again.
	 */
	public void preparePaint() {
		if (!size.equals(getSize())) { // if the size has changed...
			size = getSize();
			//scale factor, which chooses is it should be scaled or not
			xScale = size.width / gridWidth;
			if (xScale < 1) {
				xScale = GRID_VIEW_SCALING_FACTOR;
			}
			yScale = size.height / gridHeight;
			if (yScale < 1) {
			    yScale = GRID_VIEW_SCALING_FACTOR;
			}
		}
	}
}