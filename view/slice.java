package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;
/**
 * One part of a pie chart.
 * 
* @author Jan-Bert, Marcel, Robert en Jan
* @version 03-02-2015
**/
class Slice {
   double value;
   Color color;
   public Slice(double value, Color color) {  
      this.value = value;
      this.color = color;
   }
}

