package vue_controleur;

import modele.Environnement;
import modele.Ordonnanceur;
import modele.Regle;

public class Controleur {
    private final Environnement env;
    private final Ordonnanceur o;

    public Controleur(Environnement env, Ordonnanceur ord) {
        this.env = env;
        this.o = ord;
    }



    public void zoomIn() {
        int newWidth = Math.max(env.getViewWidth() - 1, 1); // Réduit la largeur
        int newHeight = Math.max(env.getViewHeight() - 1, 1); // Réduit la hauteur
        env.setViewSize(newWidth, newHeight);

    }

    public void zoomOut() {
        int newWidth = Math.min(env.getViewWidth() + 1, env.getSizeX()); // Augmente la largeur
        int newHeight = Math.min(env.getViewHeight() + 1, env.getSizeY()); // Augmente la hauteur
        env.setViewSize(newWidth, newHeight);

    }


    // Déplacement de la vue dans les quatre directions
    public void moveView(int dx, int dy) {
        env.moveView(dx, dy); // Appelle la méthode de l'environnement
    }


    // Change la règle appliquée dans l'ordonnanceur
    public void setRegle(Regle regle) {
        env.setRegle(regle);
    }

    public Environnement getEnv() {
        return env;
    }
    public Ordonnanceur getOrd(){ return o ;}

    public void environnement_vide()
    {
        for(int i = 0; i < env.getSizeX(); i++)
        {
            for(int j = 0; j < env.getSizeY(); j++ )
            {
                env.tab[i][j].setCurrentState(false);
            }
        }
    }

    public void environnement_aleatoire()
    {
        for(int i = 0; i < env.getSizeX(); i++)
        {
            for(int j = 0; j < env.getSizeY(); j++ )
            {
                env.tab[i][j].rndState();
            }
        }
    }

    // Place un motif sur la grille
    public void placerMotif(int x, int y, boolean[][] motif) {
    /*    for(int i = 0; i < env.getSizeX(); i++)
        {
            for(int j = 0; j < env.getSizeY(); j++ )
            {
                env.tab[i][j].setCurrentState(false);
            }
        } */

        if (motif == null) return;

        int x_global = (env.getViewStartX() + x) % env.getSizeX();
        int y_global = (env.getViewStartY() + y) % env.getSizeY();

        for (int i = 0; i < motif.length; i++) {
            for (int j = 0; j < motif[0].length; j++) {

               int n_x = (x_global + i) % env.getSizeX();
               int n_y = (y_global + j) % env.getSizeY();
               env.setState(n_x, n_y, motif[i][j]);

            }
        }
        env.markAsChanged();
        env.notifyObservers();
    }

    // Met à jour la vitesse de l'ordonnanceur
    public void setVitesse(long vitesse) {
        o.setSleepTime(vitesse);
    }

    // Pause ou reprise du jeu
    public void togglePause() {
        o.togglePause();
    }

    public void toggleCell(int x, int y) {
        Environnement env = getEnv();

        // Convertir coordonnées locales (vue partielle) en globales (grille globale)
        int x_global = (env.getViewStartX() + x) % env.getSizeX();
        int y_global = (env.getViewStartY() + y) % env.getSizeY();

        // Inverser l'état de la cellule
        env.togglecell(x_global, y_global);

        // Mettre à jour la vue
        env.markAsChanged();
        env.notifyObservers();
    }


    public boolean getPause()
    {
        return o.getPause();
    }
}
