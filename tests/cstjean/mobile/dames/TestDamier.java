package cstjean.mobile.dames;

import junit.framework.TestCase;
import org.junit.Assert;

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
        assertEquals(1, damier.getNombresPion());
        assertEquals(damier.getPion(38), pion1);

        Pion pion2 = new Pion(Pion.CouleurPion.Noir);
        damier.ajoutPion(1, pion2);
        assertEquals(2, damier.getNombresPion());
        assertEquals(damier.getPion(1), pion2);
    }

    /**
     * Test l'initialisation d'un damier.
     */
    public void testInitialisation() {
        Damier damier = new Damier();
        damier.initialiser();
        assertEquals(40, damier.getNombresPion());
        for (int i = 1; i <= 50; i++) {
            if (i < 21) {
                assertEquals("noir", damier.getPion(i).getCouleur());
            }
            if (i > 30) {
                assertEquals("blanc", damier.getPion(i).getCouleur());
            }
        }
    }

    /**
     * Test la création de l'array 2d du damier.
     */
    public void test2dArray() {
        Damier damier = new Damier();
        damier.initialiser();
        Pion[][] damier2D = new Pion[10][10];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if ((row + col) % 2 == 0) {
                    if (row < 4) {
                        damier2D[row][col] = new Pion(Pion.CouleurPion.Noir);
                    } else if (row > 5) {
                        damier2D[row][col] = new Pion(Pion.CouleurPion.Blanc);
                    }
                }
            }
        }
        Assert.assertArrayEquals(damier2D, damier.get2dArray());
    }
}
