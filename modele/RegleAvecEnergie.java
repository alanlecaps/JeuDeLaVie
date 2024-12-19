package modele;

public class RegleAvecEnergie implements Regle
{
    @Override
    public void appliquerRegles(Case cellule, Environnement env) {
        int voisinsVivants = 0;

        for (Direction d : Direction.values()) {
            Case voisine = env.getCase(cellule, d);
            if (voisine.getCurrentState()) {
                voisinsVivants++;
            }
        }

        if (cellule.getCurrentState()) {
            if (voisinsVivants < 2 || voisinsVivants > 3) {
                cellule.setCurrentState(false);
            } else {
                cellule.reduceEnergy(1); // Réduit l'énergie
            }
        } else {
            if (voisinsVivants == 3 && cellule.getEnergy() > 4) {
                cellule.setCurrentState(true);
                cellule.reduceEnergy(2); // Renaît en perdant de l'énergie
            }
        }
    }
}

