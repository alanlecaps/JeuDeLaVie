package modele;
import modele.Regle;


public class RegleJeuDeLaVie implements Regle
{
    @Override
    public void appliquerRegles(Case cellule, Environnement env)
    {
        int voisinsVivants = 0;

        for (Direction d : Direction.values())
        {
            Case voisine = env.getCase(cellule, d);
            if (voisine != null && voisine.getCurrentState()) {
                voisinsVivants++;
            }
        }

        if (cellule.getCurrentState()) {
            cellule.setCurrentState(voisinsVivants == 2 || voisinsVivants == 3);
        } else {
            cellule.setCurrentState(voisinsVivants == 3);
        }
    }
}
