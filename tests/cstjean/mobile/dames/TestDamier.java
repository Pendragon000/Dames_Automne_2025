package cstjean.mobile.dames;

import junit.framework.TestCase;

/**
 * Class Test pour la Class Damier.
 *
 * @author Isaak Fortin
 */
public class TestDamier extends TestCase {

    /**
     * Class qui teste la création du Damier.
     */
    public void testCreer() {
        Damier damier = new Damier();
        Pion pion1 = new Pion();
        damier.ajoutPion(38, pion1);
        assertEquals(damier.getNombresPion(), 1);
        assertEquals(damier.getPion(38), pion1);

        Pion pion2 = new Pion("noir");
        damier.ajoutPion(1, pion2);
        assertEquals(damier.getNombresPion(), 2);
        assertEquals(damier.getPion(1), pion2);
    }
}
