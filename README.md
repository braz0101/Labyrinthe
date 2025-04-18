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
│   │       ├── LabyrintheGenerator.java  # Génération aléatoire de labyrinthe
│   │       ├── LabyrintheLoader.java  # Chargement depuis un fichier (labyrinthe.txt par exemple)
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

## 📜 Rôles des fichiers

| Fichier                     | Rôle |
|-----------------------------|-------------------------------------------------------------|
| **Main.java**               | Programme principal en mode console (génération, chargement, résolution). |
| **Labyrinthe.java**         | Représentation et affichage du labyrinthe en console. |
| **Solveur.java**            | Implémentation des algorithmes DFS et BFS pour résoudre le labyrinthe. |
| **LabyrintheGenerator.java** | Génération automatique de labyrinthes aléatoires. |
| **LabyrintheLoader.java**   | Chargement d’un labyrinthe à partir d’un fichier texte. |
| **Chrono.java**             | Mesure du temps d’exécution des algorithmes. |

## 🖥️ Interface Graphique

| Fichier                     | Rôle |
|-----------------------------|-------------------------------------------------------------|
| **LabyrintheGUI.java**       | Gestion complète de l'interface utilisateur avec Swing. |
| **LabyrinthePanel.java**     | Affichage et interaction avec le labyrinthe en mode graphique. |
| **LabyrinthePanel2.java**    | Affichage du labyrinthe en mode console avec Swing. |

## 📂 Ressources

| Fichier            | Rôle |
|--------------------|-------------------------------------------|
| **labyrinthe.txt** | Exemple de labyrinthe au format texte, utilisable pour le chargement. |

## ⚙️ Paramétrage du Labyrinthe

Le taux de **murs** dans le labyrinthe est défini par un paramètre probabiliste dans `LabyrintheGenerator.java` :
```java
// Génération d'un labyrinthe avec environ 25% de murs
labyrinthe[i][j] = (rand.nextDouble() < 0.25) ? '#' : '=';
```
Vous pouvez ajuster cette valeur (`0.25`) pour modifier la densité des murs :
- **0.20** → Labyrinthe plus ouvert avec plus de chemins.
- **0.25** → Équilibre entre passages et obstacles (valeur par défaut).
- **0.30** → Labyrinthe plus complexe avec plus de murs.

Augmenter cette valeur rend la **résolution plus difficile**, tandis que la diminuer facilite le passage.

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

<img width="475" alt="1" src="https://github.com/user-attachments/assets/a119f9ca-57a5-4866-9ce2-b97e272f5297" />


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

<img width="364" alt="2" src="https://github.com/user-attachments/assets/e1657fe5-894d-4feb-9874-4511a3c19b90" />


### 📊 Comparaison DFS vs BFS

En choisissant **la comparaison**, un **tableau récapitulatif** des performances s'affiche dans la console comme ceci:

```
===========================================
 Comparaison DFS vs BFS
===========================================
| Algorithme | Temps (ms) | Cases visitées |
|------------|------------|----------------|
| DFS        |      0     |        7       |
| BFS        |      0     |        3       |
===========================================
```

<img width="611" alt="3" src="https://github.com/user-attachments/assets/9068c41d-832e-439b-a95e-c495b6ae22f4" />

Le labyrinthe **résolu** est affiché avec le **chemin final en vert (+)**.

(Exemple du fichier labyrinthe.txt)

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

<img width="446" alt="4" src="https://github.com/user-attachments/assets/dced65a4-5d1a-4c83-9219-eb0094e59210" />

<img width="449" alt="5" src="https://github.com/user-attachments/assets/b7be6344-b1ab-41d3-a764-f0ba539af8d5" />

<img width="443" alt="6" src="https://github.com/user-attachments/assets/e63bca11-a0dc-4a08-93b9-73dec43eac9f" />

<img width="447" alt="7" src="https://github.com/user-attachments/assets/c7e1989a-bc8a-4a84-a5cf-bbbaae1c9889" />

---

## 📝 Exemple de syntaxe de fichier **labyrinthe.txt**

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

