package cstjean.mobile.dames;

import junit.framework.TestCase;

/**
 * Test pour la class Joueur.
 */
public class TestJoueur extends TestCase {
    /**
     * Test de création des joueurs avec les différents constructeurs.
     */
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
}
