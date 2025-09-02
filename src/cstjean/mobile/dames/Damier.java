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
     * Ajoute un pion dans la list pions.
     *
     * @param position La position Manoury du pion.
     * @param pion Le pion à ajouter dans la list.
     */
    protected void ajoutPion(int position, Pion pion) {
        pions.add(position - 1, pion);
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
}
