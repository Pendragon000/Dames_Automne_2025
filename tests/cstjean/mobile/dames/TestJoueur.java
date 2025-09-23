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
        Partie partie = new Partie(new Damier());
        Joueur joueur = new Joueur(Pion.CouleurPion.Blanc, partie);
        assertEquals("blanc", joueur.getNom());
        assertEquals("blanc", joueur.getCouleur());
        Joueur joueur2 = new Joueur(Pion.CouleurPion.Noir, partie);
        assertEquals("noir", joueur2.getNom());
        assertEquals("noir", joueur2.getCouleur());
        Joueur joueur3 = new Joueur(Pion.CouleurPion.Noir, partie, "joueur");
        assertEquals("joueur", joueur3.getNom());
        assertEquals("noir", joueur3.getCouleur());
    }
}
