
package modele;
import java.util.HashMap;
import java.util.Observable;

public class Environnement extends Observable implements Runnable
{
    private int viewStartX = 0; // Début de la vue partielle
    private int viewStartY = 0;
    private int viewWidth = 70; // Taille de la vue partielle
    private int viewHeight = 30;

    private int sizeX, sizeY;
    public Case[][] tab;
    private Regle regle;
    private HashMap < Case, Coordonnees>  map = new HashMap< Case, Coordonnees>();


    public void setRegle(Regle regle) {
        this.regle = regle;
    }

    public int getSizeX()
    {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }


    public int getViewStartX() {
        return viewStartX;
    }

    public int getViewStartY() {
        return viewStartY;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void moveView(int dx, int dy) {
        viewStartX = (viewStartX + dx + sizeX) % sizeX; // Déplacement horizontal
        viewStartY = (viewStartY + dy + sizeY) % sizeY; // Déplacement vertical
        setChanged();
        notifyObservers(); // Met à jour la vue
    }

    public void setViewSize(int width, int height) {
        viewWidth = Math.min(width, sizeX); // Limite la largeur à la taille globale
        viewHeight = Math.min(height, sizeY); // Limite la hauteur à la taille globale

        setChanged();
        notifyObservers(); // Met à jour la vue
    }


    public boolean getCurrentState(int x, int y) {
        return tab[x][y].getCurrentState();
    }

    public void setState(int x, int y, boolean b)
    {
        tab[x][y].setCurrentState(b);
    }

    public Case getCase(Case source, Direction d)
    {
        //Trouver les coordonnees de la cellule source

        int x = map.get(source).getx() , y = map.get(source).gety();

        // Calculer les nouvelles coordonnées en fonction de la direction
        int newX = x, newY = y;
        switch (d) {
            case h:  newX = (x - 1 + sizeX) % sizeX; break;
            case hd: newX = (x - 1 + sizeX) % sizeX; newY = (y + 1) % sizeY; break;
            case d:  newY = (y + 1) % sizeY; break;
            case db: newX = (x + 1) % sizeX; newY = (y + 1) % sizeY; break;
            case b:  newX = (x + 1) % sizeX; break;
            case bg: newX = (x + 1) % sizeX; newY = (y - 1 + sizeY) % sizeY; break;
            case g:  newY = (y - 1 + sizeY) % sizeY; break;
            case gh: newX = (x - 1 + sizeX) % sizeX; newY = (y - 1 + sizeY) % sizeY; break;
        }

        return tab[newX][newY];
    }

    public Environnement(int _sizeX, int _sizeY)
    {
        sizeX = _sizeX;
        sizeY = _sizeY;

        tab = new Case[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++)
        {
            for (int j = 0; j < sizeY; j++)
            {
                tab[i][j] = new Case();
                tab[i][j].rndState();
                Coordonnees c = new Coordonnees(i,j);
                map.put(tab[i][j], c );
            }

        }

    }

    public void togglecell(int x, int y)
    {
        setState(x, y, !getCurrentState(x, y) );
        setChanged();
        notifyObservers();
    }


    public boolean isZoneActive(Case cell)
    {
        Coordonnees coord = map.get(cell);
        int x = coord.getx();
        int y = coord.gety();

        // Exemple : Activer uniquement les cellules dans le carré central
        int minX = sizeX / 4, maxX = 3 * sizeX / 4;
        int minY = sizeY / 4, maxY = 3 * sizeY / 4;

        return (x >= minX && x <= maxX) && (y >= minY && y <= maxY);
    }


    @Override
    public void run()
    {
        // notification de l'observer

        for(int i = 0; i < sizeX; i++)
        {
            for(int j = 0; j < sizeY; j++)
            {
                tab[i][j].setPreview_state(tab[i][j].getCurrentState())   ;
            }
        }

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (regle != null) {
                    regle.appliquerRegles(tab[i][j], this); // Règle dynamique
                } else {
                    tab[i][j].nextState(this); // Règles classiques
                }
            }
        }

        // Mettre à jour la grille
        setChanged();
        notifyObservers();

    }

    public void markAsChanged() {
        setChanged();
    }

}
