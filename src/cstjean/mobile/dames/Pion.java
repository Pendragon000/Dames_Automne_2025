package cstjean.mobile.dames;

/**
 * Classe d'un pion dans un jeu de dame.
 */
public class Pion {
    /**
     * La couleur du pion.
     */
    private final String couleur;

    /**
     * Constructeur par défaut de la classe pion.
     *
     * @param couleur la couleur du pion(noir ou blanc)
     */
    protected Pion(String couleur) {
        this.couleur = couleur;
    }

    /**
     * Constructeur sans argument de la classe pion (met la couleur en blanc).
     */
    protected Pion() {
        this.couleur = "blanc";
    }

    protected String getCouleur() {
        return couleur;
    }
}
