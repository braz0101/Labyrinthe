package mazeresolver;

public class Labyrinthe {
    private char[][] grille;

    public Labyrinthe(char[][] grille) {
        this.grille = grille;
    }

    public void afficher() {
        for (char[] ligne : grille) {
            for (char caseLab : ligne) {
                System.out.print(caseLab);
            }
            System.out.println();
        }
    }

    public char[][] getGrille() {
        return grille;
    }
}
