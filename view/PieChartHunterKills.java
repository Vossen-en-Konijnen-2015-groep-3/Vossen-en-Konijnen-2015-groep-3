package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;


public class PieChartHunterKills extends JComponent {
	 Slice[] slices = new Slice[3];
   public PieChartHunterKills(int x, int y, int z) {
	 
	   double xx = (double) x;
	   double yy = (double) y;
	   double zz = (double) z;
	   
	   slices[0] = new Slice(xx, Color.green);
	   slices[1] = new Slice(yy, Color.blue);
	   slices[2] = new Slice(zz, Color.orange);

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