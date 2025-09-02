package cstjean.mobile.dames;

import junit.framework.TestCase;

/**
 * Class qui teste Pion.
 *
 * @author Isaak Fortin
 */
public class TestPion extends TestCase {
    /**
     * Teste la création d'un pion.
     */
    public void testCreer() {
        // Teste du constructeur avec argument.
        String couleurPion1 = "noir";
        Pion pion1 = new Pion(couleurPion1);
        assertEquals(couleurPion1, pion1.getCouleur());

        String couleurPion2 = "blanc";
        Pion pion2 = new Pion(couleurPion2);
        assertEquals(couleurPion2, pion2.getCouleur());

        // Teste du constructeur sans argument.
        Pion pion3 = new Pion();
        assertEquals("blanc", pion3.getCouleur());
    }
}
