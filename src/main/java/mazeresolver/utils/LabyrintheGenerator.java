package mazeresolver.utils;

import java.util.*;

public class LabyrintheGenerator {

    // Génération d'un labyrinthe aléatoire avec un remplissage aléatoire (20% de murs)
    public static char[][] genererLabyrinthe(int taille) {
        char[][] labyrinthe = new char[taille][taille];

        // Remplissage avec des murs et des passages
        Random rand = new Random();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                labyrinthe[i][j] = (rand.nextDouble() < 0.25) ? '#' : '=';
            }
        }

        // Définir le point de départ et d'arrivée
        labyrinthe[1][1] = 'S'; // Départ
        labyrinthe[taille - 2][taille - 2] = 'E'; // Arrivée
        return labyrinthe;
    }
}
