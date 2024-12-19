

import modele.Environnement;
import modele.Ordonnanceur;
import vue_controleur.Controleur;
import vue_controleur.FenetrePrincipale;

import javax.swing.SwingUtilities;

/**
 *
 * @author frederic
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {

                Environnement e = new Environnement(50,    100);
                Ordonnanceur o = new Ordonnanceur(500, e);
                Controleur controleur = new Controleur(e, o);
                FenetrePrincipale fenetre = new FenetrePrincipale(controleur);
                fenetre.setVisible(true);

                e.addObserver(fenetre);

            }
        });

    }

}
