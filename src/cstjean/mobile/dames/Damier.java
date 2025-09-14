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
     * Le saut de ligne par défaut du system.
     */
    protected static final String SAUT_LIGNE = System.lineSeparator();

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
     * Représentation du damier en String.
     *
     * @return La String représentant le damier.
     */
    protected String getRepresentation() {
        StringBuilder builder = new StringBuilder();
        boolean videPair = false;
        int positionManoury = 1;
        for (int i = 1; i <= 100; i++) {
            if ((i % 2 == 0) == !videPair) {
                String rep = "-";
                if (getPion(positionManoury) != null) {
                    rep = getPion(positionManoury).getRepresentation();
                }
                builder.append(rep);
                positionManoury++;
            } else {
                builder.append("-");
            }
            if (i % 10 == 0) {
                videPair = !videPair;
                builder.append(SAUT_LIGNE);
            }
        }
        return builder.toString();
    }
}
