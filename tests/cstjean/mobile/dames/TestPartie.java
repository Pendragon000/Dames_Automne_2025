package cstjean.mobile.dames;

import java.util.List;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test pour la class Partie.
 */
public class TestPartie {
    /**
     * Test de création d'une partie avec les différent constructeur.
     */
    @Test
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
    @Test
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
    @Test
    public void testHistorique() {
        Partie partie = new Partie(new Damier());
        partie.initialiser();
        partie.save();

        for (int i = 1; i <= 50; i++) {
            assertEquals(partie.getPion(i), partie.peekHistorique().getPion(i));
        }
        partie.prochainJoueur();
        partie.deplacer(new int[]{3, 0}, new int[]{4, 1});
        final Damier damier1 = partie.peekHistorique();
        partie.save();
        for (int i = 1; i <= 50; i++) {
            assertEquals(partie.getPion(i), partie.peekHistorique().getPion(i));
        }
        partie.undo();
        partie.undo();
        for (int i = 1; i <= 50; i++) {
            assertEquals(partie.getPion(i), damier1.getPion(i));
        }
    }

    /**
     * Test le bon fonctionnement d'un déplacement dans la classe partie.
     */
    @Test
    public void testDeplacement() {
        Partie partie = new Partie(new Damier());
        partie.initialiser();

        // Déplacement d'un pion blanc
        partie.deplacer(new int[]{6, 1}, new int[]{5, 0});
        assertNull(partie.get2dArray()[6][1]);
        Pion pionBlanc = new Pion(Pion.CouleurPion.Blanc);
        assertEquals(pionBlanc, partie.get2dArray()[5][0]);

        // Déplacement d'un pion noir
        partie.deplacer(new int[]{3, 0}, new int[]{4, 1});
        assertNull(partie.get2dArray()[3][0]);
        Pion pionNoir = new Pion(Pion.CouleurPion.Noir);
        assertEquals(pionNoir, partie.get2dArray()[4][1]);

        // Ajout d'une dame pour le test son déplacement
        Damier damier = new Damier();
        damier.ajoutPion(28, new Dame(Pion.CouleurPion.Blanc));
        partie = new Partie(damier);

        // Déplacement d'une dame
        partie.deplacer(new int[]{5, 4}, new int[]{6, 3});
        assertNull(partie.get2dArray()[5][4]);
        Dame dame = new Dame(Pion.CouleurPion.Blanc);
        assertEquals(dame, partie.get2dArray()[6][3]);
        partie.prochainJoueur();
        partie.deplacer(new int[]{6, 3}, new int[]{5, 4});
        assertNull(partie.get2dArray()[6][3]);
        assertEquals(dame, partie.get2dArray()[5][4]);
    }

    /**
     * Test le bon fonctionnement des prises dans la classe partie.
     */
    @Test
    public void testPrises() {
        Damier damier1 = new Damier();
        damier1.ajoutPion(28, new Dame(Pion.CouleurPion.Blanc));
        damier1.ajoutPion(33, new Pion(Pion.CouleurPion.Noir));
        Partie partie = new Partie(damier1);

        // Prise avec dame
        assertEquals(new Pion(Pion.CouleurPion.Noir), partie.get2dArray()[6][5]);
        partie.deplacer(new int[]{5, 4}, new int[]{7, 6});
        assertNull(partie.get2dArray()[5][4]);
        Dame dame = new Dame(Pion.CouleurPion.Blanc);
        assertNull(partie.get2dArray()[6][5]);
        assertEquals(dame, partie.get2dArray()[7][6]);
        partie.prochainJoueur();
        damier1.ajoutPion(33, new Pion(Pion.CouleurPion.Noir));
        assertEquals(new Pion(Pion.CouleurPion.Noir), partie.get2dArray()[6][5]);
        partie.deplacer(new int[]{7, 6}, new int[]{5, 4});
        assertNull(partie.get2dArray()[7][6]);
        assertNull(partie.get2dArray()[6][5]);
        assertEquals(dame, partie.get2dArray()[5][4]);
    }

    /**
     * Test la convertion de position 2D à position manoury.
     */
    @Test
    public void test2dToManoury() {
        assertEquals(5, new Partie(new Damier()).getManouryFrom2dPosition(0, 9));
    }
}
