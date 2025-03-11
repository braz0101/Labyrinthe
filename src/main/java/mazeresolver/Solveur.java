package mazeresolver;

import java.awt.Point;
import java.util.*;

public class Solveur {

    private char[][] labyrinthe;
    private boolean[][] visite;
    private List<Point> chemin;
    private int casesVisitees;
    private long tempsExecution;

    public Solveur(char[][] labyrinthe) {
        this.labyrinthe = labyrinthe;
        this.visite = new boolean[labyrinthe.length][labyrinthe[0].length];
        this.chemin = new ArrayList<>();
    }

    public List<Point> getChemin() {
        return chemin;
    }

    public int getCasesVisitees() {
        return casesVisitees;
    }

    public long getTempsExecution() {
        return tempsExecution;
    }

    private Point trouverPoint(char cible) {
        for (int i = 0; i < labyrinthe.length; i++) {
            for (int j = 0; j < labyrinthe[i].length; j++) {
                if (labyrinthe[i][j] == cible) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    private List<Point> voisins(Point p) {
        List<Point> liste = new ArrayList<>();
        // On impose un ordre de visite pour assurer un chemin unique
        liste.add(new Point(p.x - 1, p.y)); // Haut
        liste.add(new Point(p.x, p.y + 1)); // Droite
        liste.add(new Point(p.x + 1, p.y)); // Bas
        liste.add(new Point(p.x, p.y - 1)); // Gauche
        return liste;
    }

    private boolean estValide(Point p) {
        return !(p.x < 0 || p.x >= labyrinthe.length || p.y < 0 || p.y >= labyrinthe[0].length)
                && labyrinthe[p.x][p.y] != '#'
                && !visite[p.x][p.y];
    }

    // ============================
    // ✅ DFS CORRIGÉ
    // ============================
    public boolean resoudreDFS() {
        long debut = System.currentTimeMillis();

        Point depart = trouverPoint('S');
        Point arrivee = trouverPoint('E');

        if (depart == null || arrivee == null) {
            System.out.println("Départ ou arrivée introuvable.");
            return false;
        }

        Stack<Point> pile = new Stack<>();
        Map<Point, Point> parents = new HashMap<>(); // Pour reconstruire le chemin

        pile.push(depart);
        visite[depart.x][depart.y] = true;
        parents.put(depart, null);
        casesVisitees = 1;
        chemin.clear(); // ✅ On vide la liste pour éviter plusieurs chemins

        while (!pile.isEmpty()) {
            Point courant = pile.pop();

            if (courant.equals(arrivee)) {
                reconstruireChemin(parents, arrivee);
                tempsExecution = System.currentTimeMillis() - debut;
                return true;
            }

            for (Point voisin : voisins(courant)) {
                if (estValide(voisin)) {
                    visite[voisin.x][voisin.y] = true;
                    pile.push(voisin);
                    parents.put(voisin, courant);
                    casesVisitees++;
                }
            }
        }

        tempsExecution = System.currentTimeMillis() - debut;
        return false;
    }

    // ============================
    // ✅ BFS (inchangé)
    // ============================
    public boolean resoudreBFS() {
        long debut = System.currentTimeMillis();

        Point depart = trouverPoint('S');
        Point arrivee = trouverPoint('E');

        if (depart == null || arrivee == null) {
            System.out.println("Départ ou arrivée introuvable.");
            return false;
        }

        visite = new boolean[labyrinthe.length][labyrinthe[0].length];
        chemin.clear();
        casesVisitees = 1;

        Queue<Point> file = new LinkedList<>();
        Map<Point, Point> parents = new HashMap<>();

        file.add(depart);
        visite[depart.x][depart.y] = true;
        parents.put(depart, null);

        while (!file.isEmpty()) {
            Point courant = file.poll();

            if (courant.equals(arrivee)) {
                reconstruireChemin(parents, arrivee);
                tempsExecution = System.currentTimeMillis() - debut;
                return true;
            }

            for (Point voisin : voisins(courant)) {
                if (estValide(voisin)) {
                    visite[voisin.x][voisin.y] = true;
                    file.add(voisin);
                    parents.put(voisin, courant);
                    casesVisitees++;
                }
            }
        }

        tempsExecution = System.currentTimeMillis() - debut;
        return false;
    }

    // ============================
    // ✅ Reconstruction du chemin unique
    // ============================
    private void reconstruireChemin(Map<Point, Point> parents, Point arrivee) {
        Point courant = arrivee;
        while (courant != null) {
            chemin.add(courant);
            courant = parents.get(courant);
        }
        Collections.reverse(chemin);
    }
}
