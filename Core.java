package org.Snake;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;

public class Core {
	
	private Snake snake;
	private boolean finish;

	public Core() {
        this.snake = new Snake();
        this.finish = false;
	}
	
	public void KeyboardGestion(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) { // touche flèche droite
            this.snake.setDemand(Direction.RIGHT);
		} else if (event.getKeyCode() == KeyEvent.VK_LEFT) { // touche flèche gauche
            this.snake.setDemand(Direction.LEFT);
		} else if (event.getKeyCode() == KeyEvent.VK_UP) { // touche flèche haut
            this.snake.setDemand(Direction.HIGH);
		} else if (event.getKeyCode() == KeyEvent.VK_DOWN) { // touche flèche bas
            this.snake.setDemand(Direction.LOW);
		}
	}
  
	// le calcul du jeu
	public void calcul() {
		if (!this.finish) {
			// calcul du serpent
            this.snake.calcul();
            if (this.snake.isDead()) {
                  // la partie est perdue car le serpent
                  // a atteint les limites du plateau de jeu
                  this.finish = true;
            }
		}
	}
  
	// le dessin graphique du jeu
	public void display(Graphics g) {
        // affichage du serpent
        this.snake.display(g);
        if (this.finish) {
            String str = "Perdu !";
            g.setColor(Color.RED);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
            FontMetrics fm = g.getFontMetrics();
            int x = (g.getClipBounds().width - fm.stringWidth(str)) / 2;
            int y = (g.getClipBounds().height / 2) + fm.getMaxDescent();
            g.drawString(str, x, y);
        }
	}
      
}