package modele;

public class Ordonnanceur extends Thread
{
    private Regle regleCourante;
    private long sleepTime;
    private Environnement env;
    private boolean enPause = false;
  //  private ServiceEnvironnement service; // Référence au service

    public Ordonnanceur(long _sleepTime, Environnement _env)
    {
        sleepTime = _sleepTime;
        env = _env;
    }

    public void togglePause() { // Basculer entre pause et reprise
        enPause = !enPause;
    }

    public boolean getPause()
    {
        return enPause;
    }


    // Méthode pour changer la vitesse (temps de sommeil)
    public void setSleepTime(long newSleepTime) {
        this.sleepTime = newSleepTime;
    }

    public void run()
    {
        while (true)
        {
            if(!enPause)
            {
                env.run();
            }
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
