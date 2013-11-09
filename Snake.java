package org.Snake;

import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Snake {

      private LinkedList<Box> list;
      
      public Snake() {
            this.list = new LinkedList<Box>();
            this.list.add(new Box(14, 15));
            this.list.add(new Box(15, 15));
            this.list.add(new Box(16, 15));
      }
      
      public void calcul() {
            // calcul du serpent
      }
      
      
      public void display(Graphics g) {
    	  // activer l'anti-aliasing du dessin
          Graphics2D g2 = (Graphics2D) g;
          g2.setRenderingHint(
        		  RenderingHints.KEY_ANTIALIASING,
                  RenderingHints.VALUE_ANTIALIAS_ON);
    	  // dessin du serpent
    	  for (Box box : this.list) {
              g.fillOval(box.getX(), box.getY(), box.getLargeur(), box.getHauteur());
        }
      }
      
}