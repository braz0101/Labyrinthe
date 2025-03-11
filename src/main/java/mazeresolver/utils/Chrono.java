package mazeresolver.utils;

public class Chrono {
    private long startTime;

    // Démarrer le chronomètre
    public void start() {
        startTime = System.currentTimeMillis();
    }

    // Arrêter le chrono et renvoyer le temps écoulé en millisecondes
    public long stop() {
        return System.currentTimeMillis() - startTime;
    }
}
