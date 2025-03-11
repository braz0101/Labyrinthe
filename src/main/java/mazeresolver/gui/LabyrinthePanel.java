package mazeresolver.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class LabyrinthePanel extends JPanel {
    private char[][] labyrinthe;
    private boolean modeSombre;
    private boolean modeJoueur;
    private Point joueurPosition;

    public LabyrinthePanel(char[][] labyrinthe) {
        this.labyrinthe = labyrinthe;
        this.modeSombre = false;
        this.modeJoueur = false;
        this.joueurPosition = new Point(1, 1); // Départ en (1,1)
        setPreferredSize(new Dimension(600, 600));

        // Ajout du contrôle clavier
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (modeJoueur) {
                    deplacerJoueur(e.getKeyCode());
                }
            }
        });
    }

    public void setLabyrinthe(char[][] labyrinthe) {
        this.labyrinthe = labyrinthe;
        this.joueurPosition = new Point(1, 1); // Réinitialiser le joueur au départ
        repaint();
    }

    public void setModeSombre(boolean modeSombre) {
        this.modeSombre = modeSombre;
        repaint();
    }

    public void setModeJoueur(boolean modeJoueur) {
        this.modeJoueur = modeJoueur;
        if (modeJoueur) requestFocusInWindow();
        repaint();
    }

    /**
     * Efface uniquement le chemin parcouru par le joueur et les algorithmes DFS/BFS sans réinitialiser le labyrinthe.
     */
    public void effacerChemin() {
        for (int i = 0; i < labyrinthe.length; i++) {
            for (int j = 0; j < labyrinthe[i].length; j++) {
                if (labyrinthe[i][j] == 'P' || labyrinthe[i][j] == 'J' || labyrinthe[i][j] == 'C') {
                    labyrinthe[i][j] = '='; // Remettre un passage normal
                }
            }
        }
        joueurPosition = new Point(1, 1); // Remettre le joueur au départ
        repaint();
    }

    /**
     * Anime le chemin trouvé par DFS ou BFS.
     */
    public void animerChemin(List<Point> chemin, char couleur) {
        new Thread(() -> {
            for (Point p : chemin) {
                if (labyrinthe[p.x][p.y] != 'S' && labyrinthe[p.x][p.y] != 'E') {
                    labyrinthe[p.x][p.y] = couleur;
                    repaint();
                    try {
                        Thread.sleep(50); // Pause de 50ms pour effet visuel
                    } catch (InterruptedException ignored) {}
                }
            }
        }).start();
    }

    /**
     * Déplace le joueur selon la touche pressée (flèches directionnelles).
     */
    private void deplacerJoueur(int keyCode) {
        int x = joueurPosition.x;
        int y = joueurPosition.y;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (labyrinthe[x - 1][y] != '#') joueurPosition.x--;
                break;
            case KeyEvent.VK_DOWN:
                if (labyrinthe[x + 1][y] != '#') joueurPosition.x++;
                break;
            case KeyEvent.VK_LEFT:
                if (labyrinthe[x][y - 1] != '#') joueurPosition.y--;
                break;
            case KeyEvent.VK_RIGHT:
                if (labyrinthe[x][y + 1] != '#') joueurPosition.y++;
                break;
        }

        // Marquer la case visitée (sauf départ et arrivée)
        if (labyrinthe[joueurPosition.x][joueurPosition.y] != 'S' &&
                labyrinthe[joueurPosition.x][joueurPosition.y] != 'E') {
            labyrinthe[joueurPosition.x][joueurPosition.y] = 'P'; // 'P' pour Player
        }

        if (labyrinthe[joueurPosition.x][joueurPosition.y] == 'E') {
            JOptionPane.showMessageDialog(this, "🎉 Bravo, vous avez atteint la sortie !");
            setModeJoueur(false);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (labyrinthe == null) return;

        int rows = labyrinthe.length;
        int cols = labyrinthe[0].length;
        int cellSize = Math.min(getWidth() / cols, getHeight() / rows);

        g.setColor(modeSombre ? Color.BLACK : Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                g.setColor(switch (labyrinthe[i][j]) {
                    case '#' -> modeSombre ? Color.LIGHT_GRAY : Color.DARK_GRAY;
                    case 'S' -> Color.BLUE;   // Départ
                    case 'E' -> Color.RED;    // Arrivée
                    case 'P' -> Color.ORANGE; // Chemin parcouru par le joueur (orange)
                    case 'J' -> Color.YELLOW; // Chemin DFS
                    case 'C' -> Color.CYAN;   // Chemin BFS
                    default -> modeSombre ? Color.GRAY : Color.WHITE;
                });

                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }

        // Dessiner le joueur
        if (modeJoueur) {
            g.setColor(Color.GREEN);
            g.fillOval(joueurPosition.y * cellSize, joueurPosition.x * cellSize, cellSize, cellSize);
        }
    }
}
