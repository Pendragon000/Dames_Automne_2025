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
        assertEquals(20, damier.getNombresPionParCouleur(Pion.CouleurPion.Blanc));
        assertEquals(20, damier.getNombresPionParCouleur(Pion.CouleurPion.Noir));
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
     * Method test qui test le déplacement de pion dans le damier.
     */
    public void testDeplacement() {
        Damier damier = new Damier();
        damier.initialiser();

        // Déplacement d'un pion noir
        damier.deplacer(new int[]{3, 0}, new int[]{4, 1});
        Assert.assertNull(damier.get2dArray()[3][0]);
        Pion pionNoir = new Pion(Pion.CouleurPion.Noir);
        Assert.assertEquals(pionNoir, damier.get2dArray()[4][1]);

        // Déplacement d'un pion blanc
        damier.deplacer(new int[]{6, 1}, new int[]{5, 0});
        Assert.assertNull(damier.get2dArray()[6][1]);
        Pion pionBlanc = new Pion(Pion.CouleurPion.Blanc);
        Assert.assertEquals(pionBlanc, damier.get2dArray()[5][0]);

        // Ajout d'une dame pour le test son déplacement
        damier = new Damier();
        damier.ajoutPion(28, new Dame(Pion.CouleurPion.Blanc));

        // Déplacement d'une dame
        damier.deplacer(new int[]{5, 4}, new int[]{6, 3});
        Assert.assertNull(damier.get2dArray()[5][4]);
        Dame dame = new Dame(Pion.CouleurPion.Blanc);
        Assert.assertEquals(dame, damier.get2dArray()[6][3]);

        damier.deplacer(new int[]{6, 3}, new int[]{5, 4});
        Assert.assertNull(damier.get2dArray()[6][3]);
        Assert.assertEquals(dame, damier.get2dArray()[5][4]);
    }

    /**
     * Methode qui teste les prises dans le damier.
     */
    public void testPrises() {
        Damier damier = new Damier();
        damier.ajoutPion(28, new Dame(Pion.CouleurPion.Blanc));
        damier.ajoutPion(33, new Pion(Pion.CouleurPion.Noir));

        // Prise avec dame
        Assert.assertEquals(new Pion(Pion.CouleurPion.Noir), damier.get2dArray()[6][5]);
        damier.deplacer(new int[]{5, 4}, new int[]{7, 6});
        Assert.assertNull(damier.get2dArray()[5][4]);
        Dame dame = new Dame(Pion.CouleurPion.Blanc);
        assertNull(damier.get2dArray()[6][5]);
        Assert.assertEquals(dame, damier.get2dArray()[7][6]);

        damier.ajoutPion(33, new Pion(Pion.CouleurPion.Noir));
        Assert.assertEquals(new Pion(Pion.CouleurPion.Noir), damier.get2dArray()[6][5]);
        damier.deplacer(new int[]{7, 6}, new int[]{5, 4});
        Assert.assertNull(damier.get2dArray()[7][6]);
        assertNull(damier.get2dArray()[6][5]);
        Assert.assertEquals(dame, damier.get2dArray()[5][4]);
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
                boolean isDarkSquare = (row % 2 == 0) ? (col % 2 == 1) : (col % 2 == 0);
                if (isDarkSquare) {
                    if (row < 4) {
                        damier2D[row][col] = new Pion(Pion.CouleurPion.Noir);
                    } else if (row > 5) {
                        damier2D[row][col] = new Pion(Pion.CouleurPion.Blanc);
                    }
                } else {
                    damier2D[row][col] = null;
                }
            }
        }
        Assert.assertArrayEquals(damier2D, damier.get2dArray());
    }

    /**
     * Test la methode instantiate de la classe Damier.
     */
    public void testInstantiate() {
        Damier damier = new Damier();
        assertEquals(damier, damier.instantiate());
    }
}
