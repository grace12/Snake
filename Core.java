package org.Snake;

import java.awt.Graphics;

public class Core {
	
	private Snake snake;

	public Core() {
        this.snake = new Snake();
	}
  
	// le calcul du jeu
	public void calcul() {
        // calcul du serpent
        this.snake.calcul();
	}
  
	// le dessin graphique du jeu
	public void display(Graphics g) {
        // affichage du serpent
        this.snake.display(g);
	}
      
}