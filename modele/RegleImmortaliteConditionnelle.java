package modele;

public class RegleImmortaliteConditionnelle implements Regle {
    @Override
    public void appliquerRegles(Case cellule , Environnement env) {
        int voisinsVivants = 0;

        for (Direction d : Direction.values()) {
            Case voisine = env.getCase(cellule , d);
            if (voisine.getCurrentState()) {
                voisinsVivants++;
            }
        }

        if (cellule.isImmortal()) {
            return; // Ne change pas si immortelle
        }

        if (cellule.getCurrentState()) {
            if (voisinsVivants < 2 || voisinsVivants > 3) {
                cellule.setCurrentState(false);
            } else {
                cellule.incrementGenerations(); // Augmente le compteur
                if (cellule.getGenerationsVivantes() >= 5) {
                    cellule.setImmortal(true); // Devient immortelle
                }
            }
        } else {
            if (voisinsVivants == 3) {
                cellule.setCurrentState(true);
                cellule.resetGenerations(); // Reset du compteur
            }
        }
    }
}
