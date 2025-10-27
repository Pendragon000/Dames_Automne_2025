package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class partie qui représente une partie de dame.
 */
public class Partie {
    /**
     * Le damier de la partie.
     */
    private final Damier damier;
    /**
     * L'index du joueur courant.
     */
    private int indexJoueurCourant;
    /**
     * Les joueurs dans la partie.
     */
    private final List<Joueur> joueurs = new ArrayList<>(2);

    /**
     * COnstructeur par défaut pour une partie.
     *
     * @param damier Le damier que la partie doit utiliser.
     */
    protected Partie(Damier damier) {
        this(damier, List.of(new Joueur(Pion.CouleurPion.Blanc), new Joueur(Pion.CouleurPion.Noir)));
    }

    /**
     * Constructeur qui prend une liste de joueurs.
     *
     * @param damier Le damier de la partie.
     * @param joueurs List de joueurs de la partie.
     *                (doit contenir un joueur blanc et un joueur noir)
     * @throws IllegalArgumentException Quand la liste de joueur est incorrecte.
     */
    protected Partie(Damier damier, List<Joueur> joueurs) throws IllegalArgumentException {
        this.damier = damier;

        if (joueurs.size() != 2) {
            throw new IllegalArgumentException("La liste de joueurs données ne contient pas deux joueur");
        }

        int nbBlanc = 0;
        int nbNoir = 0;
        Joueur joueurBlanc = null;
        Joueur joueurNoir = null;
        for (Joueur joueur : joueurs) {
            if (Objects.equals(joueur.getCouleur(), Pion.CouleurPion.Blanc.toString())) {
                nbBlanc++;
                joueurBlanc = joueur;
            } else if (Objects.equals(joueur.getCouleur(), Pion.CouleurPion.Noir.toString())) {
                nbNoir++;
                joueurNoir = joueur;
            } else {
                throw new IllegalArgumentException("Couleur de joueur invalide : " + joueur.getCouleur());
            }
        }

        if (nbBlanc != 1 && nbNoir != 1) {
            throw new IllegalArgumentException("Nombre incorrect de joueurs." +
                    "Nombre de joueur blanc: " + nbBlanc +
                    " Nombre de joueur noir: " + nbNoir);
        }

        this.joueurs.add(0, joueurBlanc);
        this.joueurs.add(1, joueurNoir);
        indexJoueurCourant = 0;
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

    /**
     * Donne le joueur courant.
     *
     * @return Le joueur courant.
     */
    protected Joueur getJoueurCourant() {
        return getJoueur(indexJoueurCourant);
    }

    /**
     * Donne l'index du joueur courant.
     *
     * @return L'index du joueur courant.
     */
    protected int getIndexJoueurCourant() {
        return indexJoueurCourant;
    }

    /**
     * Change le joueur courant pour le prochain.
     */
    protected void prochainJoueur() {
        if (indexJoueurCourant == 0) {
            indexJoueurCourant = 1;
        } else {
            indexJoueurCourant = 0;
        }
    }
}
