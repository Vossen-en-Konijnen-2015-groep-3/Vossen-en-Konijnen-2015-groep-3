package view;

import controller.ParameterInput;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sounds.Boom;
import model.Field;
import model.FieldStats;
import controller.Simulator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A graphical view of the simulation grid.
 * The view displays a colored rectangle for each location 
 * representing its contents. It uses a default background color.
 * Colors for each type of species can be defined using the
 * setColor method.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class SimulatorView extends JFrame
{
    // Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";
    private JLabel stepLabel, population;
    private FieldView fieldView;
    
    // A map for storing colors for participants in the simulation
    private Map<Class, Color> colors;
    // A statistics object computing and storing simulation information
    private FieldStats stats;
    
    

    /**
     * Create a view of the given width and height.
     * @param height The simulation's height.
     * @param width  The simulation's width.
     */
    public SimulatorView(int height, int width)
    {
    	//JPanels voor de inhoud van het frame.
    	JPanel north = new JPanel();
    	JPanel south = new JPanel();
    	JPanel middle = new JPanel();
    	JPanel west = new JPanel();
    	JPanel east = new JPanel();
    	
        stats = new FieldStats();
        colors = new LinkedHashMap<Class, Color>();

        setTitle("Fox and Rabbit Simulation");
        stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
        
        setLocation(100, 50);
        
        fieldView = new FieldView(height, width);

        Container contents = getContentPane();
        contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
        
        //maak de buttons
        
        JButton stap1 = new JButton("1 Stap");
        stap1.setPreferredSize(new Dimension(40, 40));
        JButton stap100 = new JButton("100 Stappen");
        JButton reset = new JButton("Reset");
        JButton ziek = new JButton("Release HELL!");
        JButton runLong = new JButton("Run long");
        
        // Maak de leggenda en resize deze 
        ImageIcon legenda_img = new ImageIcon(SimulatorView.class.getResource("/legenda.png"));
        Image img_legenda = legenda_img.getImage();
        Image newimg = img_legenda.getScaledInstance(100, 110,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon legenda = new ImageIcon(newimg);
        
        
        runLong.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Thread t = new Thread(){
					@Override
					public void run(){
						Simulator.simulator.runLongSimulation();
					}
				};
				t.start();
				
			}
		});
        
        ziek.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Simulator.simulator.startDecease();
			}
		});
        
        reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Simulator.simulator.reset();
			}
		});
        
        //actionlistener aan button1 toevoegen
		stap1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Thread t = new Thread(){
					@Override
					public void run(){
						Simulator.simulator.simulateOneStep();
					}
				};
				
				t.start();
			}
		});
		
		//actionlistener aan button100 toevoegen
		stap100.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Thread t = new Thread() {
					@Override
					public void run() {
						for (int i = 0; i < 100; i++) {
							Simulator.simulator.simulateOneStep();
							// fieldView.update(fieldView.getGraphics());
						}
					}
				};
				t.start();
			}
		});
		
		
        
        //vul north
        north.add(stepLabel);
        
        //vul middle
        middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));
        middle.add(west);
        middle.add(east);
        
        //vul west
        //TODO buttons;
        west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
        west.add(stap1);
        west.add(Box.createRigidArea(new Dimension(0,5)));
        west.add(stap100);
        west.add(ziek);
        west.add(reset);
        west.add(new JLabel(legenda));
        
        //vul east
        east.add(fieldView);
        
        //vul south
        south.add(population);
        
        
        //vul contents
        contents.add(north);
        contents.add(middle);
        contents.add(south);
        
        //maak JMenuBar
        JMenuBar menu = new JMenuBar();
        
        //maak menu 1
        JMenu edit = new JMenu("Edit");
        //maak menu 2
        JMenu view = new JMenu("View");
        //maak menu 3
        JMenu info = new JMenu("Info");
        
        //maak opties edit
        JMenuItem changeFox = new JMenuItem("Change Fox");
        JMenuItem changeRabbit = new JMenuItem("Change Rabbit");
        JMenuItem changeDeer = new JMenuItem("Change Deer");
        
        //maak opties view
        JMenuItem PiechartHunterKills = new JMenuItem("piechartHunterKills");
        JMenuItem PiechartPopulation = new JMenuItem("piechartPopulation");
        JMenuItem Barchart = new JMenuItem("Barchart");
        
        //maak opties Info
        JMenuItem information = new JMenuItem("Over de applicatie");
        
        //actionListeners view
        
        PiechartHunterKills.addActionListener(new ActionListener(){
        	public void actionPerformed (ActionEvent e) {
        		new view.HunterKillFrame();
        	}
        });
 
        PiechartPopulation.addActionListener(new ActionListener(){
        	public void actionPerformed (ActionEvent e) {
        		new view.PopulationFrame();
        	}
        });
        
        Barchart.addActionListener(new ActionListener(){
        	public void actionPerformed (ActionEvent e) {
        		new controller.ParameterInput("barchart");
        	}
        });
        	
        
        //actionListeners edit
        changeFox.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				new controller.ParameterInput("Fox");
			}
		});
        
        changeRabbit.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				new controller.ParameterInput("Rabbit");
			}
		});
        
        changeDeer.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				new controller.ParameterInput("Deer");
			}
		});
        
        //actionListeners info
        information.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				new controller.ParameterInput("information");
			}
		});
        
        //add items to edit
        edit.add(changeFox);
        edit.add(changeRabbit);
        edit.add(changeDeer);
        
        //add edit to menu bar
        menu.add(edit);
        
        //add items to view
        view.add(PiechartHunterKills);
        view.add(PiechartPopulation);
        view.add(Barchart);
        
        
        //add view to menu bar
        menu.add(view);
        
        //add items to info
        info.add(information);
        //add info to menu bar
        menu.add(info);
        //set menu bar
        setJMenuBar(menu);
        
        
        /* old layout
        contents.add(stepLabel, BorderLayout.NORTH);
        contents.add(fieldView, BorderLayout.CENTER);
        contents.add(population, BorderLayout.SOUTH);
        */
        
        //defaultCloseOperation
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        pack();
        setVisible(true);
    }
    
    /**
     * Define a color to be used for a given class of animal.
     * @param animalClass The animal's Class object.
     * @param color The color to be used for the given class.
     */
    public void setColor(Class animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    private Color getColor(Class animalClass)
    {
        Color col = colors.get(animalClass);
        if(col == null) {
            // no color defined for this class
            return UNKNOWN_COLOR;
        }
        else {
            return col;
        }
    }

    /**
     * Show the current status of the field.
     * @param step Which iteration step it is.
     * @param field The field whose status is to be displayed.
     */
    public void showStatus(int step, Field field)
    {
        if(!isVisible()) {
            setVisible(true);
        }
            
        stepLabel.setText(STEP_PREFIX + step);
        stats.reset();
        
        fieldView.preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    stats.incrementCount(animal.getClass());
                    fieldView.drawMark(col, row, getColor(animal.getClass()));
                }
                else {
                    fieldView.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
        stats.countFinished();

        population.setText(POPULATION_PREFIX + stats.getPopulationDetails(field));
        fieldView.repaint();
    }

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }
 
    
    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    private class FieldView extends JPanel
    {
        private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;

        /**
         * Create a new FieldView component.
         */
        public FieldView(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
        }

        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint()
        {
            if(! size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                fieldImage = fieldView.createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if(yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }
        
        /**
         * Paint on grid location on this field in a given color.
         */
        public void drawMark(int x, int y, Color color)
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
        }

        /**
         * The field view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g)
        {
            if(fieldImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(fieldImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
}
