package cstjean.mobile.dames;

import org.junit.Test;

import java.util.List;

/**
 * Classe de test pour la classe MoveData.
 */
public class TestValidMoves {
    @Test
    public void testCreer() {
        ValidMoves data = new ValidMoves();
        data.add(new Move(new Position(5, 1), null, false));
    }
}
