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
     * L'historique du jeu en text.
     */
    private final StringBuilder textLog;

    /**
     * Constructeur par défaut de la classe historique.
     */
    public Historique() {
        textLog = new StringBuilder();
        textLog.append("---- Logs ----");
        historique = new Stack<>();
    }

    /**
     * Sauvegarde le damier dans son historique.
     *
     * @param damier Le damier à sauvegarder.
     * @param moveLog Le movement effectuer selon la notation Manoury.
     */
    protected void save(Damier damier, String moveLog) {
        textLog.append(moveLog);
        historique.push(damier);
    }

    /**
     * Retourne la dernière sauvegarde du damier.
     *
     * @return La dernière sauvegarde.
     */
    protected Damier undo() {
        int lastLine = textLog.lastIndexOf("\n");
        if (lastLine != -1) {
            textLog.delete(lastLine, textLog.length());
        }
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

    /**
     * Donne l'historique text des mouvements selon la notation Manoury.
     *
     * @return L'historique
     */
    protected String getLog() {
        return textLog.toString();
    }
}
