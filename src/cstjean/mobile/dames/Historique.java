package cstjean.mobile.dames;

import java.util.Stack;

/**
 * Class qui s'occupe de garder un historique d'un damier.
 */
public class Historique {
    /**
     * Le stack qui contient l'historique du damier.
     */
    private final Stack<Damier> historique;

    /**
     * Constructeur par défaut de la classe historique.
     */
    public Historique() {
        historique = new Stack<>();
    }

    /**
     * Sauvegarde le damier dans son historique.
     *
     * @param damier Le damier à sauvegarder.
     */
    protected void save(Damier damier) {
        historique.push(damier);
    }

    /**
     * Retourne la dernière sauvegarde du damier.
     *
     * @return La dernière sauvegarde.
     */
    protected Damier undo() {
        return historique.pop();
    }

    /**
     * Donne la dernière sauvegarde sans la retirer du stack.
     *
     * @return la dernière sauvegarde.
     */
    protected Damier peek() {
        return historique.peek();
    }
}
