package cstjean.mobile.dames;

public class Joueur {
    private String nom;
    private Pion.CouleurPion couleur;
    protected Joueur(Pion.CouleurPion couleur) {
        this.couleur = couleur;
        this.nom = couleur.toString();
    }
    protected Joueur(Pion.CouleurPion couleur, String nom) {
        this.couleur = couleur;
        this.nom = nom;
    }
    protected String getNom() {
        return nom;
    }
    protected String getCouleur() {
        return couleur.toString();
    }
}
