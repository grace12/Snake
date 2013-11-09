package org.Snake;

import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Snake {

      private LinkedList<Box> list;
      private Direction direction;
      private Direction demand;
      private boolean isDead;
      
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
      
      public void setDemand(Direction demand) {
          this.demand = demand;
      }
      
      private void turn() {
          if (this.demand != null) { // une touche à été pressée
                // le serpent va vers le haut ou le bas
                if (this.direction == Direction.HIGH
                            || this.direction == Direction.LOW) {
                      if (this.demand == Direction.RIGHT) { // la touche droite à été pressée
                           	// le serpent tourne à droite
                            this.direction = Direction.RIGHT;
                      } else if (this.demand == Direction.LEFT) { // la touche gauche à été pressée
                            // le serpent tourne à gauche
                            this.direction = Direction.LEFT;
                      }
                } else { // le serpent va vers la droite ou la gauche
                      if (this.demand == Direction.HIGH) { // la touche haut à été pressée
                            // le serpent tourne vers le haut
                            this.direction = Direction.HIGH;
                      } else if (this.demand == Direction.LOW) { // la touche bas à été pressée
                            // le serpent tourne vers le bas
                            this.direction = Direction.LOW;
                      }
                }
                // nous avons tenu compte du clavier, nous le vidons afin de
                // forcer le joueur a réappuyé sur une touche pour demander
                // une autre direction
                this.demand = null;
          }
    }
      
      private boolean OKAvancer() {
          return getNextBox().isValid();
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
    	  turn();
    	  if (OKAvancer()) {
              avance();
    	  } else {
              // la partie est perdue car le serpent
              // a atteint les limites du plateau de jeu
    		  this.isDead = true;
    	  }
      }
      
      public boolean isDead() {
          return this.isDead;
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