package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.List;

/**
 * Class partie qui représente une partie de dame.
 */
public class Partie {
    /**
     * Le damier de la partie.
     */
    private final Damier damier;
    /**
     * Les joueurs dans la partie.
     */
    private final List<Joueur> joueurs = new ArrayList<>(2);

    /**
     * Constructeur par défaut pour une partie.
     *
     * @param damier Le damier de la partie.
     */
    protected Partie(Damier damier) {
        this.damier = damier;
        joueurs.add(0, new Joueur(Pion.CouleurPion.Blanc, this));
        joueurs.add(1, new Joueur(Pion.CouleurPion.Noir, this));
    }
    // TODO : constructeur avec joueurs dans param

    /**
     * Accédeur par défaut pour le damier de la partie.
     *
     * @return Le damier de la partie.
     */
    protected Damier getDamier() {
        return damier;
    }

    /**
     * Accédeur par défaut des joueurs dans la partie selon l'index.
     *
     * @param index l'index du joueur rechercher.
     * @return Le joueur à l'index demandé.
     */
    protected Joueur getJoueur(int index) {
        return joueurs.get(index);
    }

    /**
     * Accédeur par défaut des joueurs dans la partie selon le nom du joueur.
     *
     * @param nomJoueur Le nom du joueur rechercher.
     * @return Le joueur trouvé (null si introuvable)
     */
    protected Joueur getJoueur(String nomJoueur) {
        for (Joueur joueur : joueurs) {
            if (joueur.getNom().equals(nomJoueur)) {
                return joueur;
            }
        }
        return null;
    }
}
