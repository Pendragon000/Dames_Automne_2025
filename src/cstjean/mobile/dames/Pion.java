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
    public Pion(String couleur) {
        this.couleur = couleur;
    }

    /**
     * Constructeur sans argument de la classe pion (met la couleur en blanc).
     */
    public Pion() {
        this.couleur = "blanc";
    }

    public String getCouleur() {
        return couleur;
    }
}
