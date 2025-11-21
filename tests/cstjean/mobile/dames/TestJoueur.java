package cstjean.mobile.dames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Test pour la class Joueur.
 */
public class TestJoueur {
    /**
     * Test de création des joueurs avec les différents constructeurs.
     */
    @Test
    public void testCreer() {

        Joueur joueur = new Joueur(Pion.CouleurPion.Blanc);
        assertEquals("blanc", joueur.getNom());
        assertEquals("blanc", joueur.getCouleur());
        assertEquals(0, joueur.getPoints());

        Joueur joueur2 = new Joueur(Pion.CouleurPion.Noir);
        assertEquals("noir", joueur2.getNom());
        assertEquals("noir", joueur2.getCouleur());
        assertEquals(0, joueur2.getPoints());
        joueur2.setPoints(5);
        assertEquals(5, joueur2.getPoints());

        Joueur joueur3 = new Joueur(Pion.CouleurPion.Noir, "joueur");
        assertEquals("joueur", joueur3.getNom());
        assertEquals("noir", joueur3.getCouleur());
        assertEquals(0, joueur3.getPoints());
        joueur3.setPoints(5);
        assertEquals(5, joueur3.getPoints());
    }

    /**
     * Test les methode Euquals et HashCode.
     */
    @Test
    public void testEqualsHashCode() {

        // Test pour égal
        Joueur joueur1 = new Joueur(Pion.CouleurPion.Blanc);
        Joueur joueur2 = new Joueur(Pion.CouleurPion.Blanc);
        assertEquals(joueur1, joueur2);
        assertEquals(joueur1.hashCode(), joueur2.hashCode());

        // Test pour non égal
        Joueur joueur3 = new Joueur(Pion.CouleurPion.Noir);
        assertNotEquals(joueur1, joueur3);
        assertNotEquals(joueur1.hashCode(), joueur3.hashCode());

        // Test pour class non égal
        Pion pion1 = new Pion(Pion.CouleurPion.Blanc);
        assertNotEquals(joueur1, pion1);
        assertNotEquals(joueur1.hashCode(), pion1.hashCode());
    }
}
