package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * La class de test pour la class dame.
 */
public class TestDame extends TestPion {
    /**
     * La représentation de la dame blanche dans la console.
     */
    protected static final String REPRESENTATION_DAME_BLANCHE = "d";
    /**
     * Représentation de la dame noire dans la console.
     */
    protected static final String REPRESENTATION_DAME_NOIRE = "D";

    @Override
    protected Pion creerPion() {
        return new Dame();
    }

    @Override
    protected Pion creerPion(Pion.CouleurPion couleur) {
        return new Dame(couleur);
    }

    @Override
    protected String getExpectedRepresentation(Pion pion) {
        if (Objects.equals("blanc", pion.getCouleur())) {
            return REPRESENTATION_DAME_BLANCHE;
        } else {
            return REPRESENTATION_DAME_NOIRE;
        }
    }

    @Override
    protected List<Position> getExpectedPositions(Pion pion) {
        List<Position> moves = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            moves.add(new Position(i, i));
            moves.add(new Position(i, -i));
            moves.add(new Position(-i, i));
            moves.add(new Position(-i, -i));
        }

        return moves;
    }
}
