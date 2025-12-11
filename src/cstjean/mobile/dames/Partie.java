package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;

/**
 * Class partie qui représente une partie de dame.
 */
public class Partie {
    /**
     * Le damier de la partie.
     */
    private Damier damier;
    /**
     * L'index du joueur courant.
     */
    private int indexJoueurCourant;
    /**
     * Les joueurs dans la partie.
     */
    private final List<Joueur> joueurs = new ArrayList<>(2);

    /**
     * L'historique du damier pour retourner en arrière.
     */
    private Historique historique;

    /**
     * Boolean qui représente si la partie est terminer.
     */
    private boolean estTerminer;

    /**
     * Constructeur par défaut pour une partie.
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
            } else {
                nbNoir++;
                joueurNoir = joueur;
            }
        }

        if (nbBlanc != 1 && nbNoir != 1) {
            throw new IllegalArgumentException("Nombre incorrect de joueurs." +
                    "Nombre de joueur blanc: " + nbBlanc +
                    " Nombre de joueur noir: " + nbNoir);
        }

        this.historique = new Historique();
        this.joueurs.add(0, joueurBlanc);
        this.joueurs.add(1, joueurNoir);
        this.indexJoueurCourant = 0;
        this.estTerminer = false;
        save("");
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

    /**
     * Sauvegarde le damier de la partie dans l'historique.
     *
     * @param manouryLog Le text du mouvement effectuer annoté en notation Manoury.
     */
    protected void save(String manouryLog) {
        historique.save(damier.instantiate(), manouryLog);
    }

    /**
     * Retourne à la dernière instance de l'historique.
     */
    protected void undo() {
        Damier ancienDamier = historique.undo();
        try {
            this.damier = historique.peek();
        } catch (EmptyStackException e) {
            this.damier = ancienDamier;
        }

        prochainJoueur();
        if (estTerminer) {
            estTerminer = false;
        }
    }

    /**
     * Donne la dernière instance sauvegardée du damier dans l'historique.
     *
     * @return L'instance de damier le plus récent.
     */
    protected Damier peekHistorique() {
        return historique.peek();
    }

    /**
     * Initialise la partie.
     */
    protected void initialiser() {
        damier.initialiser();
        indexJoueurCourant = 0;
        estTerminer = false;
        historique = new Historique();
        save("");
    }

    /**
     * Donne le pion à la position manoury donnée.
     *
     * @param indexManoury La position manoury.
     * @return Le pion à la position manoury.
     */
    protected Pion getPion(int indexManoury) {
        return damier.getPion(indexManoury);
    }

    /**
     * Donne un array de pion 2D représentant le damier selon la liste notée en notation manoury.
     *
     * @return Un array 2D de pion représentant le damier.
     */
    protected Pion[][] get2dArray() {
        return damier.get2dArray();
    }

    /**
     * Convertie une position 2D d'un pion en position dans la notation manoury.
     *
     * @param x La position x du pion.
     * @param y La position y du pion
     * @return La position manoury du pion selon les position x, y données.
     */
    protected int getManouryFrom2dPosition(int x, int y) {
        return damier.getManouryFrom2dPosition(x, y);
    }

    /**
     * Déplace un pion à la position donner à la target position donner.
     *
     * @param pionPos La position du pion dont on veut déplacer.
     * @param targetPos La position ou on veut déplacer le pion.
     * @throws IllegalStateException Erreur lors du déplacement undo le mouvement retourne à l'état du damier avant.
     * @throws IllegalArgumentException Le pion choisi n'est pas le pion du joueur courant.
     */
    protected void deplacer(Position pionPos, Position targetPos) throws IllegalArgumentException, IllegalStateException {

        // Vérifie que la partie n'est pas terminée.
        if (estTerminer) {
            throw new IllegalStateException("La partie est terminer");
        }

        // Vérifie que le pion est de bonne couleur
        if (get2dArray()[pionPos.x()][pionPos.y()] != null) {
            if (!get2dArray()[pionPos.x()][pionPos.y()].getCouleur().equals(getJoueurCourant().getCouleur())) {
                throw new IllegalArgumentException("Le pion choisie n'est pas de la bonne couleur (Attendue: " +
                        getJoueurCourant().getCouleur() + ")");
            }
        }

        // Stock le nombre pion avant le déplacement.
        Pion.CouleurPion couleurAdv = (getJoueurCourant().getCouleur().equals(Pion.CouleurPion.Blanc.toString())) ?
                Pion.CouleurPion.Noir : Pion.CouleurPion.Blanc;
        final int pionNbAvantPrise = damier.getNombresPionParCouleur(couleurAdv);

        try {
            damier.deplacer(pionPos, targetPos);
        } catch (Exception e) {
            undo();
            throw new IllegalStateException("Mouvement impossible: " + e.getMessage());
        }

        StringBuilder logEntry = new StringBuilder();
        logEntry.append("\n");
        if (Objects.equals(getJoueurCourant().getCouleur(), Pion.CouleurPion.Blanc.toString())) {
            logEntry.append("(");
        }
        logEntry.append(getManouryFrom2dPosition(pionPos.x(), pionPos.y()));
        if (pionNbAvantPrise != damier.getNombresPionParCouleur(couleurAdv)) {
            logEntry.append("x");
        } else {
            logEntry.append("-");
        }
        logEntry.append(getManouryFrom2dPosition(targetPos.x(), targetPos.y()));
        if (Objects.equals(getJoueurCourant().getCouleur(), Pion.CouleurPion.Blanc.toString())) {
            logEntry.append(")");
        }

        // Garde en historique le damier avant le mouvement.
        save(logEntry.toString());

        // Donne les points au joueur courants si une prise a pris place.
        if (pionNbAvantPrise != damier.getNombresPionParCouleur(couleurAdv)) {
            getJoueurCourant().setPoints(getJoueurCourant().getPoints() +
                    (pionNbAvantPrise - damier.getNombresPionParCouleur(couleurAdv)));
        }

        // Vérifie si la partie est terminée.
        if (getJoueurCourant().getPoints() >= 20) {
            estTerminer = true;
        }

        Pion[][] board = get2dArray();
        boolean advPeutJouer = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boolean tileIsWhite = (i + j) % 2 == 0;
                if (!tileIsWhite && board[i][j] != null) {
                    if (Objects.equals(board[i][j].getCouleur(), couleurAdv.toString())) {
                        if (!getValidMoves(new Position(i, j)).isEmpty()) {
                            advPeutJouer = true;
                            break;
                        }
                    }
                }
            }
        }

        if (!advPeutJouer) {
            estTerminer = true;
        }

        // Vérifie si on a besoin de promouvoir un pion.
        Pion[][] arr2d = get2dArray();
        for (int i = 0; i < 10; i++) {

            // Vérifie promotion pour les blancs
            if (arr2d[0][i] != null) {
                if (arr2d[0][i].getCouleur().equals(Pion.CouleurPion.Blanc.toString())) {
                    damier.ajoutPion(getManouryFrom2dPosition(0, i), new Dame(Pion.CouleurPion.Blanc));
                }
            }

            // Vérifie promotion pour les noirs
            if (arr2d[9][i] != null) {
                if (arr2d[9][i].getCouleur().equals(Pion.CouleurPion.Noir.toString())) {
                    damier.ajoutPion(getManouryFrom2dPosition(9, i), new Dame(Pion.CouleurPion.Noir));
                }
            }
        }

        prochainJoueur();
    }

    /**
     * Donne la liste de déplacement possible pour un pion.
     *
     * @param pionPos La position du pion dont on veut les déplacements valides.
     * @return Une liste de position dont le pion peut se déplacer à
     */
    protected List<List<Integer>> getValidMoves(Position pionPos) {
        return damier.getValidMoves(pionPos);
    }

    /**
     * Donne l'historique des mouvements en text.
     *
     * @return L'historique en text selon la notation Manoury.
     */
    protected String getTextLog() {
        return historique.getLog();
    }

    /**
     * Indique si la partie est terminé.
     *
     * @return Un boolean si la partie est terminé.
     */
    protected boolean estTerminer() {
        return estTerminer;
    }
}
