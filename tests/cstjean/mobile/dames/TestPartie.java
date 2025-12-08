package cstjean.mobile.dames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.List;
import org.junit.Test;

/**
 * Test pour la class Partie.
 */
public class TestPartie {
    /**
     * Test de création d'une partie avec les différents constructeurs.
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
        assertEquals("---- Logs ----", partie.getTextLog());
        assertFalse(partie.estTerminer());
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
        assertEquals("---- Logs ----", partie1.getTextLog());
        assertFalse(partie1.estTerminer());
        assertEquals(joueur3, partie1.getJoueurCourant());
        assertEquals(0, partie1.getIndexJoueurCourant());
    }

    /**
     * Test l'exception envoyée quand on tente de creer une partie avec un nombre invalide de joueurs.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTropJoueurException() {
        Joueur joueur1 = new Joueur(Pion.CouleurPion.Blanc);
        new Partie(new Damier(), List.of(joueur1));
    }

    /**
     * Test l'exception envoyée quand on tente de creer une partie avec un nombre de couleurs invalide.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMauvaiseCouleurException() {
        Joueur joueur1 = new Joueur(Pion.CouleurPion.Blanc);
        Joueur joueur2 = new Joueur(Pion.CouleurPion.Blanc);
        new Partie(new Damier(), List.of(joueur1, joueur2));
    }

    /**
     * Test pour le retour d'un objet null lorsqu'on cherche pour un joueur avec un nom invalide.
     */
    @Test
    public void testGetJoueurParNomNull() {
        String nomJoueur = "Joueur";
        String nomInvalide = "nomInvalide";

        Joueur joueur = new Joueur(Pion.CouleurPion.Blanc, nomJoueur);
        Joueur joueur2 = new Joueur(Pion.CouleurPion.Noir);

        Partie partie = new Partie(new Damier(), List.of(joueur, joueur2));

        assertEquals(nomJoueur, joueur.getNom());
        assertEquals(joueur, partie.getJoueur(nomJoueur));
        assertNull(partie.getJoueur(nomInvalide));
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
        Damier damier1 = new Damier();
        damier1.ajoutPion(28, new Dame(Pion.CouleurPion.Blanc));
        damier1.ajoutPion(33, new Pion(Pion.CouleurPion.Noir));
        Partie partie = new Partie(damier1);
        partie.save("32x32");

        for (int i = 1; i <= 50; i++) {
            assertEquals(partie.getPion(i), partie.peekHistorique().getPion(i));
        }
        // Prise avec dame
        assertEquals(new Pion(Pion.CouleurPion.Noir), partie.get2dArray()[6][5]);
        partie.deplacer(new int[]{5, 4}, new int[]{7, 6});
        partie.save("32x32");
        for (int i = 1; i <= 50; i++) {
            assertEquals(partie.getPion(i), partie.peekHistorique().getPion(i));
        }
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
        damier.ajoutPion(1, new Pion(Pion.CouleurPion.Noir));
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
        damier1.ajoutPion(1, new Pion(Pion.CouleurPion.Noir));
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
     * Test l'exception envoyée quand on tente de déplacer un pion lorsque la partie est terminée.
     */
    @Test(expected = IllegalStateException.class)
    public void testDeplacementPartieTerminer() {
        Damier damier = new Damier();
        damier.ajoutPion(damier.getManouryFrom2dPosition(5, 4), new Pion(Pion.CouleurPion.Noir));
        damier.ajoutPion(damier.getManouryFrom2dPosition(6, 5), new Pion(Pion.CouleurPion.Blanc));

        Partie partie = new Partie(damier);
        partie.getJoueur(1).setPoints(20);
        partie.prochainJoueur();
        partie.deplacer(new int[]{5, 4}, new int[]{7, 6});
        partie.prochainJoueur();
        partie.deplacer(new int[]{7, 6}, new int[]{8, 7});
    }

    /**
     * Test l'exception envoyée quand on tente de déplacer un pion lorsque la partie est terminée.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeplacementCouleurInvalide() {
        Damier damier = new Damier();
        damier.ajoutPion(damier.getManouryFrom2dPosition(5, 4), new Pion(Pion.CouleurPion.Noir));
        damier.ajoutPion(damier.getManouryFrom2dPosition(6, 5), new Pion(Pion.CouleurPion.Blanc));

        Partie partie = new Partie(damier);
        partie.deplacer(new int[]{5, 4}, new int[]{7, 6});
    }

    @Test
    public void testDeplacementPromotion() {
        Damier damier = new Damier();
        Pion pionN = new Pion(Pion.CouleurPion.Noir);
        Pion pionB = new Pion(Pion.CouleurPion.Blanc);
        damier.ajoutPion(damier.getManouryFrom2dPosition(8, 5), pionN);
        damier.ajoutPion(damier.getManouryFrom2dPosition(1, 6), pionB);

        Partie partie = new Partie(damier);

        // Promotion Pion blanc
        assertEquals(pionB, partie.get2dArray()[1][6]);
        partie.deplacer(new int[]{1, 6}, new int[]{0, 7});
        assertEquals(new Dame(Pion.CouleurPion.Blanc), partie.get2dArray()[0][7]);

        // Promotion Pion noir
        assertEquals(pionN, partie.get2dArray()[8][5]);
        partie.deplacer(new int[]{8, 5}, new int[]{9, 6});
        assertEquals(new Dame(Pion.CouleurPion.Noir), partie.get2dArray()[9][6]);
    }

    /**
     * Test l'exception envoyée quand on tente de déplacer un pion lorsque la partie est terminée.
     */
    @Test(expected = IllegalStateException.class)
    public void testDeplacementInvalide() {
        Damier damier = new Damier();
        damier.ajoutPion(damier.getManouryFrom2dPosition(5, 4), new Pion(Pion.CouleurPion.Noir));
        damier.ajoutPion(damier.getManouryFrom2dPosition(6, 5), new Pion(Pion.CouleurPion.Blanc));

        Partie partie = new Partie(damier);
        partie.deplacer(new int[]{6, 5}, new int[]{5, 4});
    }

    /**
     * Test la convertion de position 2D à position manoury.
     */
    @Test
    public void test2dToManoury() {
        assertEquals(5, new Partie(new Damier()).getManouryFrom2dPosition(0, 9));
    }

    /**
     * Test la method qui donne les déplacements valid.
     */
    @Test
    public void testGetValidMoves() {
        Damier damier = new Damier();
        Partie partie = new Partie(damier);
        partie.initialiser();
        assertEquals(damier.getValidMoves(new int[]{0, 1}), partie.getValidMoves(new int[]{0, 1}));
    }
}
