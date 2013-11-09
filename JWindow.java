package org.Snake;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JWindow extends JFrame implements Const {
	
	private Core modele;

	public JWindow() {
        // titre de la fenêtre
        super("Snake 0.1");
        // créer le modèle du jeu
        this.modele = new Core();
        // fermeture de l'application lorsque la fenêtre est fermée
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // pas de redimensionnement possible de la fenêtre
        setResizable(false);
        // créer un conteneur qui affichera le jeu
        final JPanel content = new JPanel() {
              @Override
              protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // affichage du modèle du jeu
                    JWindow.this.modele.display(g);
              }
        };
     // dimension de ce conteneur 
        content.setPreferredSize(new Dimension(
        		NB_COLONNES * CASE_PIXELS,
        		NB_LIGNES * CASE_PIXELS));
        // ajouter le conteneur à la fenêtre
        setContentPane(content);
        // Créer un thread infini
        Thread thread = new Thread(new Runnable() {
              @Override
              public void run() {
                    while (true) { // boucle infinie
                    	// à chaque fois que la boucle est exécutée, la
                        // méthode de calcul du jeu est appelée.
                        // Comme la boucle est infinie, la méthode de calcul
                        // sera appelée en cycle perpétuel.
                        JWindow.this.modele.calcul();
                        // demander à l'EDT (Event Dispatch Thread) de redessiner le conteneur
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
        // création de la fenêtre
        JWindow window = new JWindow();
        // dimensionnement de la fenêre "au plus juste" suivant
        // la taille des composants qu'elle contient
        window.pack();
        // centrage sur l'écran
        window.setLocationRelativeTo(null);
        // affichage
        window.setVisible(true);
  }

}
