🏆 Résolveur de Labyrinthe
Un programme Java permettant de générer, charger et résoudre des labyrinthes en utilisant les algorithmes DFS (Depth-First Search) et BFS (Breadth-First Search).
L'application est disponible en mode console et en interface graphique (Swing).

📌 Fonctionnalités
✅ Génération de labyrinthes aléatoires
✅ Chargement d'un labyrinthe à partir d'un fichier
✅ Résolution avec DFS (recherche en profondeur)
✅ Résolution avec BFS (recherche en largeur)
✅ Comparaison des performances des deux algorithmes
✅ Interface graphique avec mode sombre et mode joueur 🎮
✅ Affichage du chemin résolu dans la console et en mode graphique

🛠 Technologies utilisées
🟢 Java (JDK 17+)
🟢 Swing pour l'interface graphique
🟢 Algorithmes de parcours de graphes (DFS & BFS)

📥 Installation et Exécution
1️⃣ Cloner le projet

git clone https://github.com/ton-repo/labyrinthe-solver.git
cd labyrinthe-solver

2️⃣ Compilation et exécution
Mode Console

javac -d bin -sourcepath src src/mazeresolver/Main.java
java -cp bin mazeresolver.Main

Mode Graphique

javac -d bin -sourcepath src src/mazeresolver/gui/LabyrintheGUI.java
java -cp bin mazeresolver.gui.LabyrintheGUI


🎮 Menu Console
Lorsque vous exécutez le programme en mode console, un menu interactif s'affiche :

Bienvenue dans le Résolveur de Labyrinthe !
1. Générer un labyrinthe aléatoire
2. Charger le labyrinthe depuis le fichier labyrinthe.txt
Choisissez une option (1 ou 2) :

- Génération d'un labyrinthe : entrez une taille entre 5 et 50
- Chargement depuis un fichier : lit un fichier texte contenant la structure d'un labyrinthe

🔎 Résolution du labyrinthe
Après avoir généré ou chargé un labyrinthe, le programme propose trois options :

1. Résolution avec DFS
2. Résolution avec BFS
3. Comparer DFS et BFS
Choisissez une option (1, 2 ou 3) :

- DFS (Depth-First Search) 🟡
- BFS (Breadth-First Search) 🔵
- Comparaison des deux algorithmes ⚖

