package org.Snake;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JWindow extends JFrame implements Const {
	
	private Core modele;

	public JWindow() {
        // titre de la fen�tre
        super("Snake 0.1");
        // cr�er le mod�le du jeu
        this.modele = new Core();
        // fermeture de l'application lorsque la fen�tre est ferm�e
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // pas de redimensionnement possible de la fen�tre
        setResizable(false);
        // cr�er un conteneur qui affichera le jeu
        final JPanel content = new JPanel() {
              @Override
              protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // affichage du mod�le du jeu
                    JWindow.this.modele.display(g);
              }
        };
     // dimension de ce conteneur 
        content.setPreferredSize(new Dimension(
        		NB_COLONNES * CASE_PIXELS,
        		NB_LIGNES * CASE_PIXELS));
        // ajouter le conteneur � la fen�tre
        setContentPane(content);
        // Cr�er un thread infini
        Thread thread = new Thread(new Runnable() {
              @Override
              public void run() {
                    while (true) { // boucle infinie
                    	// � chaque fois que la boucle est ex�cut�e, la
                        // m�thode de calcul du jeu est appel�e.
                        // Comme la boucle est infinie, la m�thode de calcul
                        // sera appel�e en cycle perp�tuel.
                        JWindow.this.modele.calcul();
                        // demander � l'EDT (Event Dispatch Thread) de redessiner le conteneur
                        content.repaint();
                        // temporisation pour une bonne perseption (calcul+affichage toute les demi-secondes)
                        try {
                              Thread.sleep(500);
                        } catch (InterruptedException e){}
                    
                    }
              }
        });
        // lancer le thread
        thread.start();
  }
  
  // Lancement du jeu 
  public static void main(String[] args) {
        // cr�ation de la fen�tre
        JWindow window = new JWindow();
        // dimensionnement de la fen�re "au plus juste" suivant
        // la taille des composants qu'elle contient
        window.pack();
        // centrage sur l'�cran
        window.setLocationRelativeTo(null);
        // affichage
        window.setVisible(true);
  }

}
