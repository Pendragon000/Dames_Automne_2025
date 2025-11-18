package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * class qui représente une dame.
 */
public class Dame extends Pion {
    /**
     * Constructeur sans couleur d'une dame (Créer une dame blanche).
     */
    protected Dame() {
        super();
    }

    /**
     * Constructeur par défaut d'une dame.
     *
     * @param couleur la couleur de la dame.
     */
    protected Dame(Pion.CouleurPion couleur) {
        super(couleur);
    }

    @Override
    protected String getRepresentation() {
        if (Objects.equals(this.getCouleur(), "blanc")) {
            return "d";
        } else {
            return "D";
        }
    }

    @Override
    protected List<List<Integer>> getPosition() {
        List<List<Integer>> moves = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            moves.add(List.of(i, i));
            moves.add(List.of(i, -i));
            moves.add(List.of(-i, i));
            moves.add(List.of(-i, -i));
        }

        return moves;
    }
}
