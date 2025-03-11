package mazeresolver.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LabyrintheLoader {

    public static char[][] chargerDepuisFichier(String fichier) throws IOException {
        InputStream is = LabyrintheLoader.class.getClassLoader().getResourceAsStream(fichier);
        if (is == null) {
            throw new IOException("Fichier non trouvé dans resources : " + fichier);
        }

        List<char[]> lignes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                lignes.add(ligne.toCharArray());
            }
        }
        return lignes.toArray(new char[0][]);
    }
}
