package mazeresolver.gui;

import mazeresolver.*;
import mazeresolver.utils.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LabyrintheGUI extends JFrame {
    private char[][] grille;
    private LabyrinthePanel labyrinthePanel;
    private JTextArea resultArea;
    private boolean modeSombre = false;
    private boolean modeJoueur = false;

    public LabyrintheGUI() {
        setTitle("Résolveur de Labyrinthe");
        setSize(800, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(true);
        getContentPane().setBackground(Color.DARK_GRAY);

        // Panel des boutons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(5, 2, 10, 10)); // Ajout d'une ligne pour le nouveau bouton
        controlPanel.setBackground(Color.DARK_GRAY);

        JButton btnGenerer = new JButton("🛠 Générer");
        JButton btnCharger = new JButton("📂 Charger");
        JButton btnDFS = new JButton("🔍 DFS");
        JButton btnBFS = new JButton("🔎 BFS");
        JButton btnComparer = new JButton("⚖ Comparer");
        JButton btnReset = new JButton("🔄 Réinitialiser");
        JButton btnEffacerChemin = new JButton("❌ Effacer Chemin"); // Nouveau bouton
        JButton btnModeSombre = new JButton("🌙 Mode Sombre");
        JButton btnModeJoueur = new JButton("🎮 Mode Joueur");

        styliserBouton(btnGenerer);
        styliserBouton(btnCharger);
        styliserBouton(btnDFS);
        styliserBouton(btnBFS);
        styliserBouton(btnComparer);
        styliserBouton(btnReset);
        styliserBouton(btnEffacerChemin);
        styliserBouton(btnModeSombre);
        styliserBouton(btnModeJoueur);

        controlPanel.add(btnGenerer);
        controlPanel.add(btnCharger);
        controlPanel.add(btnDFS);
        controlPanel.add(btnBFS);
        controlPanel.add(btnComparer);
        controlPanel.add(btnReset);
        controlPanel.add(btnEffacerChemin);
        controlPanel.add(btnModeSombre);
        controlPanel.add(btnModeJoueur);

        add(controlPanel, BorderLayout.NORTH);

        // Panel du labyrinthe
        labyrinthePanel = new LabyrinthePanel(new char[20][20]);
        add(labyrinthePanel, BorderLayout.CENTER);

        // Zone de texte pour les résultats
        resultArea = new JTextArea(5, 40);
        resultArea.setEditable(false);
        resultArea.setBackground(Color.BLACK);
        resultArea.setForeground(new Color(173, 216, 230)); // Bleu marine
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.SOUTH);

        // Actions des boutons
        btnGenerer.addActionListener(e -> genererLabyrinthe());
        btnCharger.addActionListener(e -> chargerLabyrinthe());
        btnDFS.addActionListener(e -> resoudreLabyrinthe("DFS"));
        btnBFS.addActionListener(e -> resoudreLabyrinthe("BFS"));
        btnComparer.addActionListener(e -> comparerAlgorithmes());
        btnReset.addActionListener(e -> resetLabyrinthe());
        btnEffacerChemin.addActionListener(e -> effacerChemin()); // Action du nouveau bouton
        btnModeSombre.addActionListener(e -> toggleModeSombre());
        btnModeJoueur.addActionListener(e -> toggleModeJoueur());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void genererLabyrinthe() {
        String input = JOptionPane.showInputDialog("Entrez la taille (5-30) :", "20");
        int taille = 20;
        try {
            taille = Math.max(5, Math.min(30, Integer.parseInt(input)));
        } catch (NumberFormatException ignored) {}

        grille = LabyrintheGenerator.genererLabyrinthe(taille);
        labyrinthePanel.setLabyrinthe(grille);
        resultArea.setText("✅ Labyrinthe généré " + taille + "x" + taille);
    }

    private void chargerLabyrinthe() {
        try {
            grille = LabyrintheLoader.chargerDepuisFichier("labyrinthe.txt");
            labyrinthePanel.setLabyrinthe(grille);
            resultArea.setText("📂 Labyrinthe chargé !");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resoudreLabyrinthe(String algo) {
        if (grille == null) return;
        char[][] clone = cloneGrille(grille);
        Solveur solveur = new Solveur(clone);
        boolean resolu = algo.equals("DFS") ? solveur.resoudreDFS() : solveur.resoudreBFS();

        if (resolu) {
            labyrinthePanel.animerChemin(solveur.getChemin(), algo.equals("DFS") ? 'J' : 'C');
            resultArea.setText("✅ " + algo + " - " + solveur.getTempsExecution() + "ms, " + solveur.getCasesVisitees() + " cases visitées.");
        } else {
            resultArea.setText("❌ Aucun chemin trouvé.");
        }
    }

    private void comparerAlgorithmes() {
        if (grille == null) return;

        char[][] cloneDFS = cloneGrille(grille);
        char[][] cloneBFS = cloneGrille(grille);

        Solveur solveurDFS = new Solveur(cloneDFS);
        Solveur solveurBFS = new Solveur(cloneBFS);

        boolean resoluDFS = solveurDFS.resoudreDFS();
        boolean resoluBFS = solveurBFS.resoudreBFS();

        if (resoluDFS) labyrinthePanel.animerChemin(solveurDFS.getChemin(), 'J');
        if (resoluBFS) labyrinthePanel.animerChemin(solveurBFS.getChemin(), 'C');

        resultArea.setText("⚖ DFS : " + solveurDFS.getTempsExecution() + "ms, " + solveurDFS.getCasesVisitees() + " cases.\n" +
                "⚖ BFS : " + solveurBFS.getTempsExecution() + "ms, " + solveurBFS.getCasesVisitees() + " cases.");
    }

    private void resetLabyrinthe() {
        grille = new char[20][20];
        labyrinthePanel.setLabyrinthe(grille);
        resultArea.setText("🔄 Réinitialisé !");
    }

    private void effacerChemin() {
        if (grille == null) return;
        labyrinthePanel.effacerChemin();
        resultArea.setText("❌ Chemin effacé !");
    }

    private void toggleModeSombre() {
        modeSombre = !modeSombre;
        labyrinthePanel.setModeSombre(modeSombre);
    }

    private void toggleModeJoueur() {
        modeJoueur = !modeJoueur;
        labyrinthePanel.setModeJoueur(modeJoueur);
        resultArea.setText(modeJoueur ? "🎮 Mode joueur activé !" : "🔍 Mode résolution activé !");
    }

    private char[][] cloneGrille(char[][] source) {
        char[][] copie = new char[source.length][];
        for (int i = 0; i < source.length; i++) copie[i] = source[i].clone();
        return copie;
    }

    private void styliserBouton(JButton button) {
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LabyrintheGUI::new);
    }
}
