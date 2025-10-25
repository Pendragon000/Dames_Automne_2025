package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Damier qui représente le damier d'un jeu de dame.
 *
 * @author Isaak Fortin
 */
public class Damier {
    /**
     * ArrayList qui contient tous les pions du jeu de dame.
     */
    private final List<Pion> pions = new ArrayList<>(50);

    /**
     * Constructeur par défaut de la classe Damier.
     */
    protected Damier() {
        for (int i = 0; i < 50; i++) {
            pions.add(null);
        }
    }

    /**
     * Initialise le damier avec 40 pion 20 de chaque couleur.
     */
    protected void initialiser() {
        for (int i = 1; i <= 50; i++) {
            if (i < 21) {
                ajoutPion(i, new Pion(Pion.CouleurPion.Noir));
            }
            if (i > 30) {
                ajoutPion(i, new Pion());
            }
        }
    }

    /**
     * Ajoute un pion dans la list pions.
     *
     * @param position La position Manoury du pion.
     * @param pion Le pion à ajouter dans la list.
     */
    protected void ajoutPion(int position, Pion pion) {
        pions.set(position - 1, pion);
    }

    /**
     * Methode qui permet de savoir le nombre de pions dans la list.
     *
     * @return Le nombre de pion en Int
     */
    protected int getNombresPion() {
        int nombresPion = 0;

        for (Pion p : pions) {
            if (p != null) {
                nombresPion++;
            }
        }
        return nombresPion;
    }

    /**
     * Getter de base de la list pions.
     *
     * @param position La position Manoury du pion
     * @return Le pion sur la position Manoury
     */
    protected Pion getPion(int position) {
        return pions.get(position - 1);
    }

    /**
     * Covertie la list de 50 de notation manoury pour un array 2D de pion.
     *
     * @return Un array 2D de pion du damier.
     */
    protected Pion[][] get2dArray() {
        Pion[][] grille = new Pion[10][10];
        int index = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if ((row + col) % 2 == 0) {
                    grille[row][col] = pions.get(index);
                    index++;
                } else {
                    grille[row][col] = null;
                }
            }
        }
        return grille;
    }
}
