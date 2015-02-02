package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

import java.util.HashMap;

import model.FieldStats;
import model.Field;

public class MyComponent extends JComponent {
  Slice[] slices = new Slice[4];
   public MyComponent(Field x) {
	   FieldStats fieldStats = new FieldStats();
	   for(int row = 0; row < x.getDepth(); row++) {
           for(int col = 0; col < x.getWidth(); col++) {
               Object animal = x.getObjectAt(row, col);
               if(animal != null) {
            	   fieldStats.incrementCount(animal.getClass());
               }
           }
	   }
	   fieldStats.countFinished();
	   HashMap<String, Integer> statistieken = fieldStats.getDetails(x);
	   System.out.println(x);
	  // System.out.println(statistieken);
	   System.out.println("fuck");
	   System.out.println(statistieken.keySet().size());
	   for(int i=0;i<statistieken.keySet().size();i++){
		   System.out.println("key: "+statistieken.keySet().toArray()[i]);
	   }
	   double fox = statistieken.get("model.Fox").doubleValue();
	   double hunter = statistieken.get("model.Hunter").doubleValue();
	   double deer = statistieken.get("model.Deer").doubleValue();
	   double rabbit = statistieken.get("model.Rabbit").doubleValue();
	   
	   slices[0] = new Slice(fox, Color.blue);
	   slices[1] = new Slice(hunter, Color.red);
	   slices[2] = new Slice(deer, Color.green);
	   slices[3] = new Slice(rabbit, Color.orange);
   }
   public void paint(Graphics g) {
      drawPie((Graphics2D) g, getBounds(), slices);
   }
   void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
      double total = 0.0D;
      for (int i = 0; i < slices.length; i++) {
         total += slices[i].value;
      }
      double curValue = 0.0D;
      int startAngle = 0;
      for (int i = 0; i < slices.length; i++) {
         startAngle = (int) (curValue * 360 / total);
         int arcAngle = (int) (slices[i].value * 360 / total);
         g.setColor(slices[i].color);
         g.fillArc(area.x, area.y, area.width, area.height, 
         startAngle, arcAngle);
         curValue += slices[i].value;
      }
   }
}