package cstjean.mobile.dames;

import junit.framework.TestCase;

/**
 * Test pour la class Partie.
 */
public class TestPartie extends TestCase {
    /**
     * Test de création d'une partie avec les différent constructeur.
     */
    public void testCreer() {
        Damier damier = new Damier();
        Partie partie = new Partie(damier);
        Joueur joueur1 = new Joueur(Pion.CouleurPion.Blanc, partie);
        Joueur joueur2 = new Joueur(Pion.CouleurPion.Noir, partie);
        assertEquals(damier, partie.getDamier());
        assertEquals(joueur1, partie.getJoueur(0));
        assertEquals(joueur2, partie.getJoueur(1));
        assertEquals(joueur2, partie.getJoueur("noir"));
        assertEquals(joueur1, partie.getJoueur("blanc"));
    }
}
