package cstjean.mobile.dames;

import java.util.Objects;

/**
 * Class représentant un joueur dans une partie.
 */
public class Joueur {
    /**
     * Le nom du joueur.
     */
    private final String nom;
    /**
     * La couleur de Pion que le joueur joue.
     */
    private final Pion.CouleurPion couleur;
    /**
     * La partie dans laquel le joueur joue.
     */
    private final Partie partie;

    /**
     * Construteur pour un joueur sans nom (nom automatique la couleur qu'il joue).
     *
     * @param couleur La couleur que le joueur joue.
     * @param partie La partie dans laquel le joueur joue.
     */
    protected Joueur(Pion.CouleurPion couleur, Partie partie) {
        this.couleur = couleur;
        this.nom = couleur.toString();
        this.partie = partie;
    }

    /**
     * Construteur pour un joueur par défault.
     *
     * @param couleur La couleur que le joueur joue.
     * @param partie La partie dans laquel le joueur joue.
     * @param nom Le nom du joueur.
     */
    protected Joueur(Pion.CouleurPion couleur, Partie partie, String nom) {
        this.couleur = couleur;
        this.nom = nom;
        this.partie = partie;
    }

    /**
     * L'accédeur par défaut du nom du joueur.
     *
     * @return Le nom du joueur.
     */
    protected String getNom() {
        return nom;
    }

    /**
     * L'accédeur par défaut de la couleur que le joueur joue.
     *
     * @return La couleur en string que le joueur joue.
     */
    protected String getCouleur() {
        return couleur.toString();
    }
    // TODO : deplacementPossible(positionPion, PositionTarget) : bool
    // TODO : deplacement(positionPion, positionTargeted) : bool

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Joueur joueur = (Joueur) o;
        return Objects.equals(nom, joueur.nom) && couleur == joueur.couleur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, couleur);
    }
}
