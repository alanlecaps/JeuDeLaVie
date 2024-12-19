package modele;

import java.awt.event.MouseListener;
import java.util.Random;

public class Case {
    private static final Random rnd = new Random();
    private boolean current_state, preview_state;
    private int energy; // Pour la règle "Avec Énergie"
    private int generationsVivantes; // Pour la règle "Immortalité Conditionnelle"
    private boolean immortal; // Pour la règle "Immortalité Conditionnelle"

    public boolean getCurrentState()
    {
        return current_state;
    }

    public boolean getPreviewState()
    {
        return preview_state;
    }

    public void setCurrentState(boolean b)
    {
        current_state = b;
    }

    public void setPreview_state(boolean b)
    {
        preview_state = b;
    }

    public void rndState()
    {
        current_state = rnd.nextBoolean();
        preview_state = current_state;
    }

    public Case()
    {
        this.energy = rnd.nextInt(10) + 1; // Initialisation aléatoire de l'énergie entre 1 et 10
    }

    public void nextState(Environnement env)
    {
        int voisinsVivants = 0;

        // Parcourir toutes les directions pour compter les voisins vivants
        for (Direction d : Direction.values()) {
            Case voisine = env.getCase(this, d);
            if (voisine != null && voisine.getPreviewState()) {
                voisinsVivants++;
            }
        }

        // Appliquer les règles du jeu de la vie
        if (this.preview_state) { // La cellule est vivante
            current_state = (voisinsVivants == 2 || voisinsVivants == 3);
        } else { // La cellule est morte
            current_state = (voisinsVivants == 3);
        }

    }


    public int getEnergy() {
        return energy;
    }

    public void reduceEnergy(int amount) {
        energy = Math.max(0, energy - amount);
    }

    public int getGenerationsVivantes() {
        return generationsVivantes;
    }

    public void incrementGenerations() {
        generationsVivantes++;
    }

    public void resetGenerations() {
        generationsVivantes = 0;
    }

    public boolean isImmortal() {
        return immortal;
    }

    public void setImmortal(boolean b) {
        immortal = b;
    }


}


