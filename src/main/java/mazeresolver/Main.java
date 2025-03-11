package mazeresolver;

import mazeresolver.utils.LabyrintheGenerator;
import mazeresolver.utils.LabyrintheLoader;
import mazeresolver.gui.LabyrinthePanel2;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Bienvenue dans le Résolveur de Labyrinthe !");
        Scanner scanner = new Scanner(System.in);

        char[][] grille;
        int taille = 20;

        System.out.println("\n1. Générer un labyrinthe aléatoire");
        System.out.println("2. Charger le labyrinthe depuis le fichier labyrinthe.txt");
        System.out.print("Choisissez une option (1 ou 2) : ");
        int choix = scanner.nextInt();

        if (choix == 1) {
            System.out.print("Entrez la taille du labyrinthe (entre 5 et 50) : ");
            taille = scanner.nextInt();
            taille = (taille < 5 || taille > 50) ? 20 : taille;
            grille = LabyrintheGenerator.genererLabyrinthe(taille);
        } else {
            try {
                grille = LabyrintheLoader.chargerDepuisFichier("labyrinthe.txt");
                System.out.println("✅ Labyrinthe chargé depuis le fichier !");
            } catch (IOException e) {
                System.err.println("❌ Erreur lors du chargement : " + e.getMessage());
                return;
            }
        }

        Labyrinthe labyrinthe = new Labyrinthe(grille);
        labyrinthe.afficher();

        System.out.println("\n1. Résolution avec DFS");
        System.out.println("2. Résolution avec BFS");
        System.out.println("3. Comparer DFS et BFS");
        System.out.print("Choisissez une option (1, 2 ou 3) : ");
        int algoChoix = scanner.nextInt();

        boolean faireDFS = (algoChoix == 1 || algoChoix == 3);
        boolean faireBFS = (algoChoix == 2 || algoChoix == 3);

        long tempsDFS = 0, tempsBFS = 0;
        int casesDFS = 0, casesBFS = 0;

        char[][] grilleDFS = clonerGrille(grille);
        char[][] grilleBFS = clonerGrille(grille);

        if (faireDFS) {
            tempsDFS = resoudreAvecAlgo(grilleDFS, "DFS");
            casesDFS = countVisitedCases(grilleDFS, '+'); // ✅ CHEMIN FINAL EN VERT
        }

        if (faireBFS) {
            tempsBFS = resoudreAvecAlgo(grilleBFS, "BFS");
            casesBFS = countVisitedCases(grilleBFS, '+'); // ✅ CHEMIN FINAL EN VERT
        }

        if (faireDFS && faireBFS) {
            afficherComparaisonConsole(tempsDFS, casesDFS, tempsBFS, casesBFS);
            afficherComparaisonGraphique(tempsDFS, casesDFS, tempsBFS, casesBFS);
        }

        scanner.close();
    }

    private static long resoudreAvecAlgo(char[][] grille, String algo) {
        System.out.println("\n🔍 Résolution avec " + algo + "...");
        Solveur solveur = new Solveur(grille);

        boolean resolu = (algo.equals("DFS") ? solveur.resoudreDFS() : solveur.resoudreBFS());
        long temps = solveur.getTempsExecution();

        if (resolu) {
            System.out.println("✅ Chemin trouvé avec " + algo);
            marquerChemin(grille, solveur.getChemin());
            System.out.printf("Temps d'exécution : %d ms\n", temps);
            System.out.printf("Nombre de cases visitées : %d\n", solveur.getCasesVisitees());

            afficherLabyrintheGraphique(grille, "Labyrinthe Résolu - " + algo);
            new Labyrinthe(grille).afficher(); // Affichage console
        } else {
            System.out.println("❌ Aucun chemin trouvé avec " + algo);
        }
        return temps;
    }

    private static void marquerChemin(char[][] grille, List<Point> chemin) {
        for (Point p : chemin) {
            if (grille[p.x][p.y] != 'S' && grille[p.x][p.y] != 'E') {
                grille[p.x][p.y] = '+'; // ✅ CHEMIN FINAL EN VERT
            }
        }
    }

    private static char[][] clonerGrille(char[][] source) {
        char[][] copie = new char[source.length][];
        for (int i = 0; i < source.length; i++) {
            copie[i] = source[i].clone();
        }
        return copie;
    }

    private static int countVisitedCases(char[][] grille, char couleur) {
        int count = 0;
        for (char[] row : grille) {
            for (char cell : row) {
                if (cell == couleur) count++;
            }
        }
        return count;
    }

    private static void afficherLabyrintheGraphique(char[][] grille, String titre) {
        JFrame frame = new JFrame(titre);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new LabyrinthePanel2(grille));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void afficherComparaisonConsole(long tempsDFS, int casesDFS, long tempsBFS, int casesBFS) {
        System.out.println("\n==========================");
        System.out.println(" Comparaison DFS vs BFS");
        System.out.println("==========================");
        System.out.printf("| %-10s | %-10s | %-15s |\n", "Algorithme", "Temps (ms)", "Cases visitées");
        System.out.println("|------------|------------|----------------|");
        System.out.printf("| %-10s | %-10d | %-15d |\n", "DFS", tempsDFS, casesDFS);
        System.out.printf("| %-10s | %-10d | %-15d |\n", "BFS", tempsBFS, casesBFS);
        System.out.println("==========================\n");
    }
    private static void afficherComparaisonGraphique(long tempsDFS, int casesDFS, long tempsBFS, int casesBFS) {
        JFrame frame = new JFrame("Comparaison DFS vs BFS");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2));

        frame.add(new JLabel("Algorithme"));
        frame.add(new JLabel("Résultat"));

        frame.add(new JLabel("DFS"));
        frame.add(new JLabel("⏱ " + tempsDFS + " ms, " + casesDFS + " cases visitées"));

        frame.add(new JLabel("BFS"));
        frame.add(new JLabel("⏱ " + tempsBFS + " ms, " + casesBFS + " cases visitées"));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
