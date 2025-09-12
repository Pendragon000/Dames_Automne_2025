package cstjean.mobile.dames;

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

    protected String getCouleur() {
        return couleur.toString();
    }

    protected String getRepresentation() {
        if (couleur == CouleurPion.Blanc) {
            return "p";
        } else {
            return "P";
        }
    }


    protected enum CouleurPion {
        Blanc {
            @Override
            public String toString() {
                return "blanc";
            }
        }, Noir {
            @Override
            public String toString() {
                return "noir";
            }
        }
    }
}

