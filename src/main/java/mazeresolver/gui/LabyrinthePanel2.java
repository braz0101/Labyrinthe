package mazeresolver.gui;

import javax.swing.*;
import java.awt.*;

public class LabyrinthePanel2 extends JPanel {
    private char[][] labyrinthe;

    public LabyrinthePanel2(char[][] labyrinthe) {
        this.labyrinthe = labyrinthe;
        setPreferredSize(new Dimension(600, 600));
    }

    public void setLabyrinthe(char[][] labyrinthe) {
        this.labyrinthe = labyrinthe;
        repaint(); // 🔄 Met à jour l'affichage après modification du labyrinthe
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (labyrinthe == null) return;

        int rows = labyrinthe.length;
        int cols = labyrinthe[0].length;
        int cellSize = Math.min(getWidth() / cols, getHeight() / rows);

        // Dessin du fond
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dessin du labyrinthe avec le chemin en vert
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                g.setColor(switch (labyrinthe[i][j]) {
                    case '#' -> Color.BLACK;  // Murs
                    case 'S' -> Color.BLUE;   // Départ
                    case 'E' -> Color.RED;    // Arrivée
                    case 'J' -> Color.YELLOW; // DFS (jaune)
                    case 'C' -> Color.CYAN;   // BFS (cyan)
                    case '+' -> Color.GREEN;  // ✅ CHEMIN FINAL EN VERT
                    default -> Color.WHITE;   // Espaces vides
                });

                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }
}
