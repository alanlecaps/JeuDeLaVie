package vue_controleur;

import modele.Motifs;

import modele.RegleAvecEnergie;
import modele.RegleImmortaliteConditionnelle;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import modele.Environnement;

import java.awt.*;


import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author frederic
 */
public class FenetrePrincipale extends JFrame implements Observer
{


    private boolean[][] currentMotif;
    private JPanel[][] tab;
    public Controleur controleur;

    public FenetrePrincipale(Controleur _c)
    {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controleur = _c;
        build();
    }

    public void build()
    {

        setTitle("Jeu de la Vie - Vue partielle");
        setSize(3000, 1000);
        Environnement env = controleur.getEnv();
        int viewWidth = env.getViewWidth();
        int viewHeight = env.getViewHeight();
        // Panneau principal
        JPanel pan = new JPanel(new BorderLayout()); //  Ce gestionnaire de disposition permet d'organiser les composants dans 5 zones :
                                                        // NORTH, SOUTH, EAST, WEST, et CENTER.

        // Panneau central
        JComponent pan1 = new JPanel(new GridLayout( viewHeight , viewWidth )); // Utilise un GridLayout, qui organise les composants sous forme de grille
                                                                                        // (ici de taille env.getSizeX() x env.getSizeY()).
        tab = new JPanel[viewHeight][viewWidth];


        for (int i = 0; i < viewHeight; i++)
        {
            for (int j = 0; j <  viewWidth; j++)
            {

                tab[i][j] = new JPanel();
                tab[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 1));

                final int x = i;
                final int y = j;

                tab[i][j].addMouseListener(new MouseAdapter() {
                    private Timer clickTimer;
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if ( e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
                            // Clic gauche simple : Modifier l'état de la cellule
                            clickTimer = new Timer(200, evt -> {
                                controleur.toggleCell(x, y); // Action différée
                                clickTimer.stop();
                            });
                            clickTimer.setRepeats(false);
                            clickTimer.start();
                        }
                        else if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1)
                        {
                            // Double clic gauche : Générer un environnement vide
                            if (clickTimer != null) {
                                clickTimer.stop(); // Annuler l'action du clic simple
                            }
                            controleur.environnement_vide();
                        }
                        else if(e.getButton() == MouseEvent.BUTTON3) {
                            // Clique droit : Place un motif
                            controleur.placerMotif(x, y, currentMotif);
                        }

                    }
                });
                pan1.add(tab[i][j]); // Ajouter le panneau à la grille
            }
        }

        // Panneau pour les boutons
        JPanel pan2 = new JPanel();//Dispose les composants en ligne (grâce à FlowLayout).
        pan2.setLayout(new BoxLayout(pan2, BoxLayout.Y_AXIS));

        pan.add(pan1, BorderLayout.CENTER);
        pan.add(pan2, BorderLayout.EAST);
        JPanel pan3 = new JPanel(new FlowLayout());
        pan.add(pan3, BorderLayout.SOUTH);
        setContentPane(pan);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Sélectionnez une regle :", SwingConstants.CENTER);

        JComboBox<String> regleSelector = new JComboBox<>(new String[]{
                "Jeu de la Vie", "Avec Énergie",
                "Immortalité",
        } );

        regleSelector.addActionListener(e -> {
            String selected = (String) regleSelector.getSelectedItem();
            switch (selected)
            {
                case "Jeu de la Vie" -> controleur.setRegle(null);
                case "Avec Énergie" -> controleur.setRegle(new RegleAvecEnergie());
                case "Immortalité" -> controleur.setRegle(new RegleImmortaliteConditionnelle());

            }
        });
    panel.add(regleSelector, BorderLayout.CENTER);
    panel.add(label, BorderLayout.NORTH);
        JMenuBar jm = new JMenuBar();
       // jm.add(regleSelector);
        jm.add(panel);

        // Ajouter le JSlider pour contrôler la vitesse du jeu
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 50); // Min = 1, Max = 100, par défaut 50
        speedSlider.setMajorTickSpacing(20); // Spacing for major ticks
        speedSlider.setMinorTickSpacing(5);  // Spacing for minor ticks
        speedSlider.setPaintTicks(true);     // Afficher les repères
        speedSlider.setPaintLabels(true);    // Afficher les labels de valeur

        // Ajouter un label pour la vitesse
        JLabel speedLabel = new JLabel("Vitesse du jeu (ms)", SwingConstants.CENTER);

        // Mettre à jour la vitesse de l'ordonnanceur lorsque l'utilisateur déplace le slider
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int speed = speedSlider.getValue(); // Vitesse entre 1 et 100
                long newSleepTime = 101 - speed;    // Plus la valeur est basse, plus le jeu est rapide
                controleur.setVitesse(newSleepTime); // Mettre à jour sleepTime de l'ordonnanceur
                System.out.println("Vitesse actuelle : " + speed + " (temps de pause : " + newSleepTime + " ms)");
            }
        });

        JButton enval = new JButton("Environnement aleatoire");
        pan3.add(enval, BorderLayout.WEST);
        enval.addActionListener(e->{  controleur.environnement_aleatoire();   });

        pan3.add(speedLabel, BorderLayout.NORTH);
        pan3.add(speedSlider, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(5, 1));
        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");

        upButton.addActionListener(e -> {
            controleur.moveView(-1, 0); // Déplace vers le haut

        });

        downButton.addActionListener(e -> {
            controleur.moveView(1, 0); // Déplace vers le bas

        });

        leftButton.addActionListener(e -> {
            controleur.moveView(0, -1); // Déplace à gauche

        });

        rightButton.addActionListener(e -> {
            controleur.moveView(0, 1); // Déplace à droite

        });

        controlPanel.add(upButton);
        controlPanel.add(downButton);
        controlPanel.add(leftButton);
        controlPanel.add(rightButton);

        pan2.add(controlPanel);

        JPanel zoomPanel = new JPanel(new GridLayout(2, 1));
        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");

        zoomInButton.addActionListener(e -> {
            controleur.zoomIn(); // Réduit la taille de la vue
            // updateView(); // Rafraîchit l'affichage
        });

        zoomOutButton.addActionListener(e -> {
            controleur.zoomOut(); // Augmente la taille de la vue
            //update(controleur.getEnv(), this);
        });

        zoomPanel.add(zoomInButton);
        zoomPanel.add(zoomOutButton);

        pan2.add(zoomPanel);

        // Gérer la sélection de motif

        JComboBox<String> motifSelector = new JComboBox<>(new String[]{"Pentadecathlon_2", "Pulsar", "RUCHE_3", "RUCHE_2", "Blinker", "Bloc", "Boat", "Barge", "Long barge", "Aircraft Carrier", "Beehive", "Loaf",
                "Biloaf", "Long Boat", "Long ship", "Pond", "Ship", "Snake", "Long snake", "Eater", "Toad", "Pentadecathlon_1",
                 "CTHULHU", "Beacon", "Glider", "Fumeur", "Small ship", "Medium ship", "Large ship", "Glider gun", "Glider gun p46", "GLIDER_2", "RUCHE_1",
                "ESCALIER", "PLANEUR_2", "VaisseauxSpatiaux"
        });

        motifSelector.addActionListener(e -> {
            String selected = (String) motifSelector.getSelectedItem();
            switch (selected) {
                case "RUCHE_1":
                    currentMotif = Motifs.RUCHE_1;
                    break;

                case "RUCHE_2":
                    currentMotif = Motifs.RUCHE_2;
                    break;

                case "RUCHE_3":
                    currentMotif = Motifs.RUCHE_3;
                    break;

                case "ESCALIER":
                    currentMotif = Motifs.ESCALIER;
                    break;

                case "PLANEUR_2":
                    currentMotif = Motifs.PLANEUR_2;
                    break;

                case  "VaisseauxSpatiaux":
                    currentMotif = Motifs.VaisseauxSpatiaux;
                    break;

                case "Bloc":
                    currentMotif = Motifs.BLOCK;
                    break;

                    case "Boat":
                    currentMotif = Motifs.BOAT;
                    break;
                case "Barge":
                    currentMotif = Motifs.BARGE;
                    break;
                case "Long barge":
                    currentMotif = Motifs.LONG_BARGE;
                    break;
                case "Aircraft Carrier":
                    currentMotif = Motifs.AIRCRAFT_CARRIER;
                    break;
                case "Beehive":
                    currentMotif = Motifs.BEEHIVE ;
                    break;
                case "Loaf":
                    currentMotif = Motifs.LOAF;
                    break;
                case "Biloaf":
                    currentMotif = Motifs.BILOAF;
                    break;
                case "Long Boat":
                    currentMotif = Motifs. LONG_BOAT;
                    break;
                case "Long ship":
                    currentMotif = Motifs.LONG_SHIP;
                    break;

                case "Pond":
                    currentMotif = Motifs.POND ;
                    break;

                case "Ship":
                    currentMotif = Motifs.SHIP;
                    break;

                case "Snake":
                    currentMotif = Motifs. SNAKE;
                    break;

                case "Long snake":
                    currentMotif = Motifs.LONG_SNAKE;
                    break;

                case "Eater":
                    currentMotif = Motifs.EATER;
                    break;

                case "Toad":
                    currentMotif = Motifs.TOAD;
                    break;

                case "Pentadecathlon_1":
                    currentMotif = Motifs.PENTADECATHLON_1 ;
                    break;

                case "Pentadecathlon_2":
                    currentMotif = Motifs.PENTADECATHLON_2 ;
                    break;

                case "Blinker":
                    currentMotif = Motifs.BLINKER;
                    break;

                case "Pulsar":
                    currentMotif = Motifs.PULSAR;
                    break;

                case "CTHULHU":
                    currentMotif = Motifs.CTHULHU;
                    break;

                case "Beacon":
                    currentMotif = Motifs.BEACON;
                    break;

                case "Glider":
                    currentMotif = Motifs.GLIDER ;
                    break;

                case "Fumeur":
                    currentMotif = Motifs.FUMEUR;
                    break;

                case "Small ship":
                    currentMotif = Motifs.SMALL_SHIP;
                    break;

                case "Medium ship":
                    currentMotif = Motifs.MEDIUM_SHIP;
                    break;

                case "Large ship":
                    currentMotif = Motifs.LARGE_SHIP;
                    break;

                case "Glider gun":
                    currentMotif = Motifs.GLIDER_GUN;
                    break;

                case "Glider gun p46":
                    currentMotif = Motifs.GLIDER_GUN_P46;
                    break;

            }
        });

        setJMenuBar(jm);

        jm.add(motifSelector); // Ajouter le menu déroulant au panneau latéral

        // Set break and resume

       JButton toggleButton = new JButton("Pause"); // Par défaut, le jeu est actif
        toggleButton.addActionListener(e ->
        {
            controleur.togglePause(); // Basculer l'état de pause
            toggleButton.setText(controleur.getPause() ? "Reprendre" : "Pause");
        });
        pan3.add(toggleButton, BorderLayout.WEST);

    JButton playJeu = new JButton("play");
    pan3.add(playJeu, BorderLayout.SOUTH);

        // Listener pour le bouton Play
        playJeu.addActionListener(e -> {
            System.out.println("Jeu démarré !");
            controleur.getOrd().start(); // Démarre l'Ordonnanceur
            playJeu.setEnabled(false); // Désactive le bouton Play
            toggleButton.setEnabled(true); // Active le bouton Pause
        });




    }

    @Override
    public void update(Observable ob, Object args)
    {
        Environnement env = controleur.getEnv();
        int startX = env.getViewStartX();
        int startY = env.getViewStartY();
        int viewWidth = env.getViewWidth();
        int viewHeight = env.getViewHeight();

        for (int i = 0; i < viewHeight; i++) {
            for (int j = 0; j < viewWidth; j++) {
                int x_global = (startX + i) % env.getSizeX();
                int y_global = (startY + j) % env.getSizeY();
                boolean state = env.getCurrentState(x_global, y_global);

                tab[i][j].setBackground(state ? Color.BLACK : Color.WHITE);
            }
        }
    }


}
