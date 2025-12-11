package cstjean.mobile.dames;

import java.util.List;

/**
 * Le singleton pour la classe Partie.
 */
public class SingletonPartie {
    /**
     * La partie du Singleton.
     */
    private Partie partie;

    /**
     * L'instance du Singleton.
     */
    private static SingletonPartie instance = null;

    /**
     * Le constructeur par défaut du Singleton.
     */
    private SingletonPartie() {
        partie = new Partie(new Damier());
    }

    /**
     * Donne l'instance du Singleton.
     *
     * @return L'instance du Singleton.
     */
    public static SingletonPartie getInstance() {
        if (instance == null) {
            instance = new SingletonPartie();
        }
        return instance;
    }

    /**
     * Créer une nouvelle partie dans le singleton.
     *
     * @param damier Le damier de la partie.
     * @param joueurs Les joueurs de la partie
     */
    public void creerNewPartie(Damier damier, List<Joueur> joueurs) {
        partie = new Partie(damier, joueurs);
    }

    /**
     * Accédeur par défaut des joueurs dans la partie selon l'index.
     *
     * @param index l'index du joueur rechercher.
     * @return Le joueur à l'index demandé.
     */
    public Joueur getJoueur(int index) {
        return partie.getJoueur(index);
    }

    /**
     * Accédeur par défaut des joueurs dans la partie selon le nom du joueur.
     *
     * @param nomJoueur Le nom du joueur rechercher.
     * @return Le joueur trouvé (null si introuvable)
     */
    public Joueur getJoueur(String nomJoueur) {
        return partie.getJoueur(nomJoueur);
    }

    /**
     * Donne le joueur courant.
     *
     * @return Le joueur courant.
     */
    public Joueur getJoueurCourant() {
        return partie.getJoueurCourant();
    }

    /**
     * Donne l'index du joueur courant.
     *
     * @return L'index du joueur courant.
     */
    public int getIndexJoueurCourant() {
        return partie.getIndexJoueurCourant();
    }

    /**
     * Change le joueur courant pour le prochain.
     */
    public void prochainJoueur() {
        partie.prochainJoueur();
    }

    /**
     * Sauvegarde le damier de la partie dans l'historique.
     *
     * @param manouryLog Le text du mouvement effectuer annoté en notation Manoury.
     */
    public void save(String manouryLog) {
        partie.save(manouryLog);
    }

    /**
     * Retourne à la dernière instance de l'historique.
     */
    public void undo() {
        partie.undo();
    }

    /**
     * Donne la dernière instance sauvegardée du damier dans l'historique.
     *
     * @return L'instance de damier le plus récent.
     */
    public Damier peekHistorique() {
        return partie.peekHistorique();
    }

    /**
     * Initialise la partie.
     */
    public void initialiser() {
        partie.initialiser();
    }

    /**
     * Donne le pion à la position manoury donnée.
     *
     * @param indexManoury La position manoury.
     * @return Le pion à la position manoury.
     */
    public Pion getPion(int indexManoury) {
        return partie.getPion(indexManoury);
    }

    /**
     * Donne un array de pion 2D représentant le damier selon la liste notée en notation manoury.
     *
     * @return Un array 2D de pion représentant le damier.
     */
    public Pion[][] get2dArray() {
        return partie.get2dArray();
    }

    /**
     * Convertie une position 2D d'un pion en position dans la notation manoury.
     *
     * @param x La position x du pion.
     * @param y La position y du pion
     * @return La position manoury du pion selon les position x, y données.
     */
    public int getManouryFrom2dPosition(int x, int y) {
        return partie.getManouryFrom2dPosition(x, y);
    }

    /**
     * Déplace un pion à la position donner à la target position donner.
     *
     * @param pionPos La position du pion dont on veut déplacer.
     * @param targetPos La position ou on veut déplacer le pion.
     * @throws IllegalStateException Erreur lors du déplacement undo le mouvement retourne à l'état du damier avant.
     * @throws IllegalArgumentException Le pion choisi n'est pas le pion du joueur courant.
     */
    public void deplacer(Position pionPos, Position targetPos) throws IllegalArgumentException, IllegalStateException {
        partie.deplacer(pionPos, targetPos);
    }

    /**
     * Donne la liste de déplacement possible pour un pion.
     *
     * @param pionPos La position du pion dont on veut les déplacements valides.
     * @return Une liste de position dont le pion peut se déplacer à
     */
    public List<List<Integer>> getValidMoves(Position pionPos) {
        return partie.getValidMoves(pionPos);
    }

    /**
     * Donne l'historique des mouvements en text.
     *
     * @return L'historique en text selon la notation Manoury.
     */
    public String getTextLog() {
        return partie.getTextLog();
    }

    /**
     * Indique si la partie est terminé.
     *
     * @return Un boolean si la partie est terminé.
     */
    public boolean estTerminer() {
        return partie.estTerminer();
    }
}
