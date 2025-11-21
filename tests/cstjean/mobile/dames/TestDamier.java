package cstjean.mobile.dames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Class Test pour la Class Damier.
 *
 * @author Isaak Fortin
 */
public class TestDamier {

    /**
     * Class qui teste la création du Damier.
     */
    @Test
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
    @Test
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
    @Test
    public void testDeplacement() {
        Damier damier = new Damier();
        damier.initialiser();

        // Déplacement d'un pion noir
        damier.deplacer(new int[]{3, 0}, new int[]{4, 1});
        assertNull(damier.get2dArray()[3][0]);
        Pion pionNoir = new Pion(Pion.CouleurPion.Noir);
        assertEquals(pionNoir, damier.get2dArray()[4][1]);

        // Déplacement d'un pion blanc
        damier.deplacer(new int[]{6, 1}, new int[]{5, 0});
        assertNull(damier.get2dArray()[6][1]);
        Pion pionBlanc = new Pion(Pion.CouleurPion.Blanc);
        assertEquals(pionBlanc, damier.get2dArray()[5][0]);

        // Ajout d'une dame pour le test son déplacement
        damier = new Damier();
        damier.ajoutPion(28, new Dame(Pion.CouleurPion.Blanc));

        // Déplacement d'une dame
        damier.deplacer(new int[]{5, 4}, new int[]{6, 3});
        assertNull(damier.get2dArray()[5][4]);
        Dame dame = new Dame(Pion.CouleurPion.Blanc);
        assertEquals(dame, damier.get2dArray()[6][3]);

        damier.deplacer(new int[]{6, 3}, new int[]{5, 4});
        assertNull(damier.get2dArray()[6][3]);
        assertEquals(dame, damier.get2dArray()[5][4]);

        damier.deplacer(new int[]{5, 4}, new int[]{9, 8});
        assertNull(damier.get2dArray()[5][4]);
        assertEquals(dame, damier.get2dArray()[9][8]);

        damier.deplacer(new int[]{9, 8}, new int[]{1, 0});
        assertNull(damier.get2dArray()[9][8]);
        assertEquals(dame, damier.get2dArray()[1][0]);
    }

    /**
     * Methode qui teste les prises dans le damier.
     */
    @Test
    public void testPrises() {
        Damier damier = new Damier();
        damier.ajoutPion(28, new Dame(Pion.CouleurPion.Blanc));
        damier.ajoutPion(33, new Pion(Pion.CouleurPion.Noir));

        // Prise avec dame
        assertEquals(new Pion(Pion.CouleurPion.Noir), damier.get2dArray()[6][5]);
        damier.deplacer(new int[]{5, 4}, new int[]{7, 6});
        assertNull(damier.get2dArray()[5][4]);
        Dame dame = new Dame(Pion.CouleurPion.Blanc);
        assertNull(damier.get2dArray()[6][5]);
        assertEquals(dame, damier.get2dArray()[7][6]);

        damier.ajoutPion(33, new Pion(Pion.CouleurPion.Noir));
        assertEquals(new Pion(Pion.CouleurPion.Noir), damier.get2dArray()[6][5]);
        damier.deplacer(new int[]{7, 6}, new int[]{5, 4});
        assertNull(damier.get2dArray()[7][6]);
        assertNull(damier.get2dArray()[6][5]);
        assertEquals(dame, damier.get2dArray()[5][4]);
    }

    /**
     * Test la création de l'array 2d du damier.
     */
    @Test
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
    @Test
    public void testInstantiate() {
        Damier damier = new Damier();
        assertEquals(damier, damier.instantiate());
    }

    /**
     * Test les methodes Equals et HashCode.
     */
    @Test
    public void testEqualsHashCode() {
        Damier damierA = new Damier();
        Damier damierB = new Damier();
        assertEquals(damierA, damierB);
        assertEquals(damierA.hashCode(), damierB.hashCode());
        Damier damierC = new Damier();
        damierC.initialiser();
        assertNotEquals(damierA, damierC);
        // Réflexivité
        assertEquals(damierA, damierA);
        assertEquals(damierB, damierA);
        // Transitivité
        Damier damierD = new Damier();
        assertEquals(damierB, damierD);
        assertEquals(damierA, damierD);
        // Constance
        assertEquals(damierA, damierB);
        // Comparaison à null
        // LINT : jUnit n'appelle pas le equal si on envoit null donc on veut comparer directement
        // On veut vraiment tester le null ici...
        assertNotEquals(null, damierA);
        // Validation
        assertNotEquals("BLABLABLA", damierA);
    }

    /**
     * Test le bon fonctionnement de l'exception lorsqu'on tente
     * de convertir une case blanche en notation manoury.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testManouryTo2dPositionCaseBlanche() {
        Damier damier = new Damier();
        damier.getManouryFrom2dPosition(10, 0);
    }

    /**
     * Test le bon fonctionnement de l'exception lorsqu'on tente
     * de get les déplacements valid d'un case vide.
     */
    @Test(expected = NoSuchElementException.class)
    public void testGetValidMovesPionNull() {
        Damier damier = new Damier();
        damier.getValidMoves(new int[]{5, 4});
    }

    /**
     * Test le bon fonctionnement de l'exception lorsqu'on tente
     * de déplacer un pion d'une case vide.
     */
    @Test(expected = NoSuchElementException.class)
    public void testDeplacerPionNull() {
        Damier damier = new Damier();
        damier.deplacer(new int[]{5, 4}, new int[]{5, 4});
    }

    /**
     * Test le bon fonctionnement de l'exception lorsqu'on tente
     * de déplacer un pion d'une case vide.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeplacerMouvementInvalide() {
        Damier damier = new Damier();
        damier.ajoutPion(damier.getManouryFrom2dPosition(5, 4), new Pion());
        damier.deplacer(new int[]{5, 4}, new int[]{5, 4});
    }
}
