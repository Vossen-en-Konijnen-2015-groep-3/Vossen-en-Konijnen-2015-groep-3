package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import java.util.HashMap;

import model.Counter;
import model.FieldStats;
import model.Field;

public class MyComponent extends JComponent {
  Slice[] slices = new Slice[4];
  // = { new Slice(5, Color.black), 
  // new Slice(33, Color.green),
  // new Slice(20, Color.yellow), 
  // new Slice(15, Color.red) 
  // };
   public MyComponent(Field x) {
	   FieldStats fieldStats = new FieldStats();
	   HashMap<String, Integer> statistieken = fieldStats.getDetails(x);
	   int fox = statistieken.get("Fox");
	   int hunter = statistieken.get("Hunter");
	   int deer = statistieken.get("Deer");
	   int rabbit = statistieken.get("Rabbit");
	   
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