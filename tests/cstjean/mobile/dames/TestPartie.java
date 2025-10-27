package cstjean.mobile.dames;

import java.util.List;
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

        // Premier constructeur.
        Partie partie = new Partie(damier);
        Joueur joueur1 = new Joueur(Pion.CouleurPion.Blanc);
        Joueur joueur2 = new Joueur(Pion.CouleurPion.Noir);
        assertEquals(joueur1, partie.getJoueur(0));
        assertEquals(joueur2, partie.getJoueur(1));
        assertEquals(joueur2, partie.getJoueur("noir"));
        assertEquals(joueur1, partie.getJoueur("blanc"));
        assertEquals(joueur1, partie.getJoueurCourant());
        assertEquals(0, partie.getIndexJoueurCourant());

        // Deuxième constructeur
        Joueur joueur3 = new Joueur(Pion.CouleurPion.Blanc, "Joueur3");
        Joueur joueur4 = new Joueur(Pion.CouleurPion.Noir, "Joueur4");
        Partie partie1 = new Partie(damier, List.of(joueur3, joueur4));
        assertEquals(joueur3, partie1.getJoueur(0));
        assertEquals(joueur4, partie1.getJoueur(1));
        assertEquals(joueur3, partie1.getJoueur("Joueur3"));
        assertEquals(joueur4, partie1.getJoueur("Joueur4"));
        assertEquals(joueur3, partie1.getJoueurCourant());
        assertEquals(0, partie1.getIndexJoueurCourant());
    }

    /**
     * Method qui test les tours dans la partie.
     */
    public void testToursPartie() {
        Partie partie = new Partie(new Damier());

        // Vérification du fonctionnement des tours
        assertEquals(0, partie.getIndexJoueurCourant());
        assertEquals("blanc", partie.getJoueurCourant().getCouleur());
        partie.prochainJoueur();
        assertEquals(1, partie.getIndexJoueurCourant());
        assertEquals("noir", partie.getJoueurCourant().getCouleur());
        partie.prochainJoueur();
        assertEquals(0, partie.getIndexJoueurCourant());
        assertEquals("blanc", partie.getJoueurCourant().getCouleur());
    }

    /**
     * Method qui test l'historique de la partie.
     */
    public void testHistorique() {
        Partie partie = new Partie(new Damier());
        // TODO: test unitaire
    }
}
