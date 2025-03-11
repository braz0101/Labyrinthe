# 🏆 Résolveur de Labyrinthe

Un programme Java permettant de **générer, charger et résoudre des labyrinthes** en utilisant les algorithmes **DFS (Depth-First Search)** et **BFS (Breadth-First Search)**.  
L'application est disponible en **mode console** et en **interface graphique (Swing)**.

---

## 📌 Fonctionnalités

✔️ Génération de labyrinthes aléatoires  
✔️ Chargement d'un labyrinthe à partir d'un fichier  
✔️ Résolution avec **DFS** (recherche en profondeur)  
✔️ Résolution avec **BFS** (recherche en largeur)  
✔️ Comparaison des performances des deux algorithmes  
✔️ Interface graphique avec **mode sombre** et **mode joueur 🎮**  
✔️ Affichage du **chemin résolu** dans la **console et en mode graphique**  

---

## 🛠 Technologies utilisées

| Technologie | Utilisation |
|------------|------------|
| 🟢 **Java (JDK 17+)** | Langage principal |
| 🖥️ **Swing** | Interface graphique |
| 📂 **Fichiers texte** | Chargement de labyrinthes |
| 🔎 **DFS & BFS** | Algorithmes de parcours de graphes |

---

## 📝 Installation et Exécution

### 1⃣ Cloner le projet

```sh
git clone https://github.com/braz0101/Labyrinthe.git
cd Labyrinthe
```

### 2⃣ Compilation et exécution

#### 🔢 **Mode Console**
```sh
javac -d bin -sourcepath src src/mazeresolver/Main.java
java -cp bin mazeresolver.Main
```

#### 🖥️ **Mode Graphique**
```sh
javac -d bin -sourcepath src src/mazeresolver/gui/LabyrintheGUI.java
java -cp bin mazeresolver.gui.LabyrintheGUI
```
---

## 📂 Structure du projet

```
Labyrinthe/
├── src/
│   ├── mazeresolver/
│   │   ├── Main.java  # Mode console
│   │   ├── Labyrinthe.java  # Représentation du labyrinthe
│   │   ├── Solveur.java  # Algorithmes DFS & BFS
│   │   └── utils/
│   │       ├── Chrono.java  # Chronomètre
│   │       ├── LabyrintheGenerator.java  # Génération aléatoire
│   │       ├── LabyrintheLoader.java  # Chargement depuis un fichier
│   ├── mazeresolver/gui/
│   │   ├── LabyrintheGUI.java  # Interface graphique principale
│   │   ├── LabyrinthePanel.java  # Affichage graphique du labyrinthe
│   │   ├── LabyrinthePanel2.java  # Affichage en console
│   └── ressources/
│       ├── labyrinthe.txt  # Exemple de fichier labyrinthe
├── bin/  # Compilation
├── README.md  # Documentation
└── .gitignore  # Fichiers à ignorer
```
---

## 🎮 Menu Console

Lorsque vous exécutez le programme en mode **console**, un menu interactif s'affiche :

```
Bienvenue dans le Résolveur de Labyrinthe !

1. Générer un labyrinthe aléatoire
2. Charger le labyrinthe depuis le fichier labyrinthe.txt
Choisissez une option (1 ou 2) :
```

- **Génération d'un labyrinthe** : entrez une taille entre **5 et 50**  
- **Chargement depuis un fichier** : lit un fichier texte contenant la structure d'un labyrinthe  

### 🔎 Résolution du labyrinthe

Après avoir généré ou chargé un labyrinthe, le programme propose **trois options** :

```
1. Résolution avec DFS
2. Résolution avec BFS
3. Comparer DFS et BFS
Choisissez une option (1, 2 ou 3) :
```

- **DFS (Depth-First Search)** 🏡  
- **BFS (Breadth-First Search)** 🔵  
- **Comparaison des deux algorithmes** ⚖  

### 📊 Comparaison DFS vs BFS

En choisissant **la comparaison**, un **tableau récapitulatif** des performances s'affiche dans la console :

```
==========================
 Comparaison DFS vs BFS
==========================
| Algorithme | Temps (ms) | Cases visitées |
|------------|------------|----------------|
| DFS        |     12     |      150       |
| BFS        |      9     |      120       |
==========================
```

Le labyrinthe **résolu** est affiché avec le **chemin final en vert (+)**.

---

## 🖥️ Interface Graphique

L'interface graphique permet de :  
🛠 **Générer** un labyrinthe de taille configurable  
📂 **Charger** un labyrinthe depuis un fichier  
🔍 **Résoudre avec DFS** ou **BFS**  
⚖ **Comparer** les performances des deux algorithmes  
🌙 **Passer en mode sombre**  
🎮 **Activer le mode joueur** et naviguer avec les flèches  

### 🖱️ Boutons disponibles

| Icône | Fonction |
|-------|----------|
| 🛠 | Générer un nouveau labyrinthe |
| 📂 | Charger un labyrinthe depuis un fichier |
| 🔍 | Résoudre avec **DFS** |
| 🔎 | Résoudre avec **BFS** |
| ⚖ | Comparer **DFS et BFS** |
| ❌ | Effacer le chemin trouvé |
| 🔄 | Réinitialiser le labyrinthe |
| 🌙 | Activer/désactiver le **mode sombre** |
| 🎮 | Activer/désactiver le **mode joueur** |

---

## 📝 Exemple de fichier **labyrinthe.txt**

Si vous choisissez de **charger un labyrinthe** depuis un fichier, son format doit être :  

```
#######
#S===E#
#=###=#
#=====#
#######
```

| Symbole | Signification |
|---------|--------------|
| `#` | Mur |
| `S` | Point de départ |
| `E` | Sortie |
| `=` | Passage libre |

---

## 📝 Licence

📄 **Ce projet est open-source sous [licence MIT](https://opensource.org/licenses/MIT)**.  
🚀 **N’hésitez pas à contribuer !**

