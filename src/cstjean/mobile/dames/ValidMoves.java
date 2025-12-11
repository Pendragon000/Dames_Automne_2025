package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.List;

/**
 * Structure de donnée qui contient l'information d'un mouvement de pion.
 */
public class ValidMoves {
    /**
     * Liste qui contient tous les move possible d'un pion.
     */
    private final List<Move> moves;
    /**
     * Représente si le pion peut faire une prise.
     */
    private boolean hasTakenPion = false;

    /**
     * Constructeur par défaut pour la classe ValidMove.
     */
    public ValidMoves() {
        moves = new ArrayList<>(2);
    }

    /**
     * Ajoute l'element donner à la liste.
     *
     * @param move L'élément qu'on veut ajouter à la liste.
     */
    public void add(Move move) {
        if (move.hasTakenPion()) {
            hasTakenPion = true;
        }
        moves.add(move);
    }

    /**
     * Donne l'element à l'index donnée.
     *
     * @param index L'index de l'élément qu'on veut retourner.
     * @return L'élément à l'index.
     */
    public Move get(int index) {
        return moves.get(index);
    }

    /**
     * Indique si l'élément contient une prise.
     *
     * @return Un boolean qui indique si l'élément contient une prise.
     */
    public boolean hasTakenPion() {
        return hasTakenPion;
    }
}
