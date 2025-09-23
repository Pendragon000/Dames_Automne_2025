package cstjean.mobile.dames;

import junit.framework.TestCase;

public class TestJoueur extends TestCase {
    public void testCreer() {
        Joueur joueur = new Joueur(Pion.CouleurPion.Blanc);
        assertEquals("blanc", joueur.getNom());
        assertEquals("blanc", joueur.getCouleur());
        Joueur joueur2 = new Joueur(Pion.CouleurPion.Noir);
        assertEquals("noir", joueur2.getNom());
        assertEquals("noir", joueur2.getCouleur());
        Joueur joueur3 = new Joueur(Pion.CouleurPion.Noir, "joueur");
        assertEquals("joueur", joueur3.getNom());
        assertEquals("noir", joueur3.getCouleur());
    }
}
