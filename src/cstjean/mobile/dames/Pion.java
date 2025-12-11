package cstjean.mobile.dames;

import java.util.List;
import java.util.Objects;

/**
 * Classe d'un pion dans un jeu de dame.
 */
public class Pion {
    /**
     * La couleur du pion.
     */
    private final CouleurPion couleur;

    /**
     * Constructeur par défaut de la classe pion.
     *
     * @param couleur la couleur du pion(noir ou blanc)
     */
    protected Pion(CouleurPion couleur) {
        this.couleur = couleur;
    }

    /**
     * Constructeur sans argument de la classe pion (met la couleur en blanc).
     */
    protected Pion() {
        this.couleur = CouleurPion.Blanc;
    }

    /**
     * Accèdeur de la couleur du pion.
     *
     * @return La couleur du pion en String.
     */
    public String getCouleur() {
        return couleur.toString();
    }

    /**
     * Accèdeur de la représentation d'un pion dans la console.
     *
     * @return La représentation d'un pion p = blanc P = noir.
     */
    protected String getRepresentation() {
        if (couleur == CouleurPion.Blanc) {
            return "p";
        } else {
            return "P";
        }
    }

    /**
     * Donne la liste de positions possible qui peut être exécuter par le pion.
     * Accède aux positions relatives de déplacement et non la position du pion dans le damier.
     *
     * @return Une liste de position que le pion peut se déplacer.
     */
    protected List<Position> getPosition() {
        if (couleur == CouleurPion.Blanc) {
            return List.of(new Position(-1, 1), new Position(-1, -1));
        } else {
            return List.of(new Position(1, 1), new Position(1, -1));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            return ((Pion) o).getCouleur().equals(this.getCouleur());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(couleur);
    }

    /**
     * Énumération de la couleur de Pion.
     */
    protected enum CouleurPion {
        /**
         * La couleur Blanc pour un pion.
         */
        Blanc {
            @Override
            public String toString() {
                return "blanc";
            }
        },
        /**
         * La couleur Noir pour un pion.
         */
        Noir {
            @Override
            public String toString() {
                return "noir";
            }
        }

    }
}

