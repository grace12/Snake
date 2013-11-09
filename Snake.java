package org.Snake;

import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Snake {

      private LinkedList<Box> list;
      private Direction direction;
      
      public Snake() {
            this.list = new LinkedList<Box>();
            this.list.add(new Box(14, 15));
            this.list.add(new Box(15, 15));
            this.list.add(new Box(16, 15));
            this.direction = Direction.LEFT;
      }
      
      private Box getNextBox() {
          Box head = this.list.getFirst();
          switch (this.direction) {
          		case HIGH:
                      return new Box(head.getIndiceX(), head.getIndiceY() - 1);
                case RIGHT:
                      return new Box(head.getIndiceX() + 1, head.getIndiceY());
                case LOW:
                      return new Box(head.getIndiceX(), head.getIndiceY() + 1);
                case LEFT:
                      return new Box(head.getIndiceX() - 1, head.getIndiceY());
          }
          return null;
      }
      
      private void avance() {
          // ajoute en tête de liste la case sur laquelle
          // le serpent doit se déplacer
          this.list.addFirst(getNextBox());
          // supprime le dernier élément de la liste
          this.list.removeLast();
      }
      
      public void calcul() {
          // calcul du serpent
    	  avance();
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