package modele;

public class Motifs
{
    //  Stable

    public static final boolean[][] BLOCK = {
            {true, true},
            {true, true}

    };

    public static final boolean[][] BOAT =
    {
            {true, true, false},
            {true, false, true},
            {false, true, false}

    };

    public static final boolean[][] BARGE =
    {
            {false, true, false, false},
            {true, false, true, false},
            {false, true, false, true},
            {false, false, true, false}
    };

    public static final boolean[][] LONG_BARGE=
    {
        {false, true, false, false, false},
        {true, false, true, false, false},
        {false, true, false, true, false},
        {false, false, true, false, true},
        {false, false, false, true, false}

    };


    public static final boolean[][] AIRCRAFT_CARRIER=
    {
            {true, true, false, false},
            {true, false, false, true},
            {false, false, true, true}

    };

    public static final boolean[][] BEEHIVE =
    {
            {false, true, true, false},
            {true, false, false, true},
            {false, true, true, false}
    };

    public static final boolean[][] LOAF =
    {
            {false, true, true, false},
            {true, false, false, true},
            {true, false, true, false},
            {false, true, false, false}
    };

    public static final boolean[][] BILOAF =
    {
            {false, false, false, false, true, true, false},
            {false, false, false, true, false, false, true},
            {false, false, false, true, false, true, false},
            {false, true, true, false, true, false, false},
            {true, false, false, true, false, false, false},
            {true, false, true, false, false, false, false},
            {false, true, false, false, false, false, false}
    };

    public static final boolean[][] LONG_BOAT=
    {
            {true, true, false, false},
            {true, false, true, false},
            {false, true, false, true},
            {false, false, true, false}
    };

    public static final boolean[][] LONG_SHIP=
    {
        {true, true, false, false},
            {true, false, true, false},
            {false, true, false, true},
            {false, false, true, true}

    };

    public static final boolean[][] POND =
    {
            {false, true, true, false},
            {true, false, false, true},
            {true, false, false, true},
            {false, true, true, false}
    };

    public static final boolean[][] SHIP=
    {
        {true, true, false},
            {true, false, true},
            {false, true, true},
    };

    public static final boolean[][] SNAKE=
    {
            {true, true, false, true},
            {true, false, true, true}

    };

    public static final boolean[][] LONG_SNAKE =
    {
            {true, true, false, false, false},
            {true, false, true, false, true},
            {false, false, false, true, true}

    };

    public static final boolean[][] EATER =
    {
        {false, false, true, true},
            {false, true, false, true},
            {false, true, false, false},
            {true, true, false, false}
    };

    // Oscillateurs

    public static final boolean[][] TOAD =  // crapaud
    {
            {true, true, true, false},
            {false, true, true, true}
    };

    public static final boolean[][] PENTADECATHLON_1 =    //
    {
            {false, false, true, false, false, false, false, true, false, false},
            {true, true, false, true, true, true, true, false, true, true},
            {false, false, true, false, false, false, false, true, false, false}

    };


    public static final boolean[][] PENTADECATHLON_2  = {
            {true, true, true, true, true, true, true, true, true, true}
    };

    public static final boolean[][] BLINKER = // clignotant   Periode 2
    {
            {true, true, true}
    };

    public static final boolean[][] PULSAR ={ // Oscille avec une periode de 3

            {true, false, true, false, true},
            {true, false, false, false, true},
            {true, false, false, false, true},
            {true, false, false, false, true},
            {true, false, false, false, true},

    };

    public static final boolean[][] CTHULHU = {
            {false, false, false, false, false, false, false, false, true, false, false, false, false},
            {false, false, false, false, false, false, true, false, false, true, false, false, false},
            {false, false, true, true, false, true, false, true, false, false, true, true, false},
            {false, false, false, false, false, false, false, true, true, false, false, false, false},
            {true, false, false, false, true, false, false, false, false, false, false, true, false},
            {false, true, false, true, false, true, true, false, false, false, false, false, false},
            {false, false, false, false, false, false, true, false, false, true, false, false, false},
            {false, false, true, true, false, true, false, true, false, false, true, true, false},
            {false, false, false, false, false, false, false, true, true, false, false, false, false}
    };


    public static final boolean[][] BEACON = // phare
    {
            {true, true, false, false},
            {true, true, false, false},
            {false, false, true, true},
            {false, false, true, true}

    };

    public static final boolean[][]  GLIDER = // planeur
    {
            {false, true, false, false},
            {false, false, true, false},
            {true, true, true, false}
    };

    public static final boolean[][] FUMEUR = {
            {false, false, true, false, false},
            {false, true, true, true, false},
            {false, true, false, true, false},
            {false, false, true, false, false}
    };

    // Vaisseaux

    public static final boolean[][] SMALL_SHIP =
    {
            {true, false, false, true, false},
            {false, false, false, false, true},
            {true, false, false, false, true},
            {false, true, true, true, true}
    };

    public static final boolean[][] MEDIUM_SHIP =
    {
            {false, false, true, false, false, false},
            {true, false, false, false, true, false},
            {false, false, false, false, false, true},
            {true, false, false, false, false, true},
            {false, true, true, true, true, true, true}
    };

    public static final boolean[][] LARGE_SHIP =
    {
            {false, false, true, true, false, false, false},
            {true, false, false, false, false, true, false},
            {false, false, false, false, false, false, true},
            {true, false, false, false, false, false, true},
            {false, true, true, true, true, true, true}
    };


    public static final boolean[][] GLIDER_GUN = {
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, true, true, false, false, false, false, true, true, false, false, false, false, false, false, false},
                // (Compléter selon le schéma trouvé)
        };


    public static final boolean[][] GLIDER_GUN_P46 = {
            {false, false, true, false, false},
            {false, true, false, true, false},
            {true, false, false, false, true},
            {false, true, false, true, false},
            {false, false, true, false, false}
    };






    // Oscillateur



    public static final boolean[][] GLIDER_2 = {
            {false, true, false},
            {false, false, true},
            {true, true, true}
    };

    public static final boolean[][] RUCHE_1 ={

            {true, true, true, true }

    };

    public static final boolean[][] RUCHE_2 ={
            {true, true, true, true, true}

    };

    public static final boolean[][] RUCHE_3 =
    {
        {false, true, true},
        {true, false, true},
        {true, false, true},
        {false, true, false}

    };

    public static final boolean [][] ESCALIER = {

            {false, false, true, true},
            {false, true, true, false},
            {true, true, false, false}
    };

    public static final boolean[][] PLANEUR_2 ={

            {false, true, true},
            {true, true, false},
            {false, true, false}

    };




      public static final boolean[][] VaisseauxSpatiaux ={

              {false, true, true, true, true, true, true},
              {true, false, false, false, false, false, true},
              {false, false, false, false, false, false, true},
              {true, false, false, false, false, true, false},
              {false, false, true, true, false, false, false}
      };




}

