package cstjean.mobile.dames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.List;
import org.junit.Test;

/**
 * La class de Test pour la class SingletonPartie.
 */
public class TestSingletonPartie {
    /**
     * Test le bon fonctionnement du Singleton.
     */
    @Test
    public void testCreer() {
        SingletonPartie singleton1 = SingletonPartie.getInstance();
        SingletonPartie singleton2 = SingletonPartie.getInstance();
        assertEquals(singleton1, singleton2);

        // Test de la methode pour créer une nouvelle partie
        Damier damier = new Damier();
        Joueur joueur3 = new Joueur(Pion.CouleurPion.Blanc, "Joueur3");
        Joueur joueur4 = new Joueur(Pion.CouleurPion.Noir, "Joueur4");
        singleton1.creerNewPartie(damier, List.of(joueur3, joueur4));
        assertEquals(joueur3, singleton1.getJoueur(0));
        assertEquals(joueur4, singleton1.getJoueur(1));
        assertEquals(joueur3, singleton1.getJoueur("Joueur3"));
        assertEquals(joueur4, singleton1.getJoueur("Joueur4"));
        assertEquals("---- Logs ----", singleton1.getTextLog());
        assertFalse(singleton1.estTerminer());
        assertEquals(joueur3, singleton1.getJoueurCourant());
        assertEquals(0, singleton1.getIndexJoueurCourant());
        assertEquals(joueur3, singleton2.getJoueur(0));
        assertEquals(joueur4, singleton2.getJoueur(1));
        assertEquals(joueur3, singleton2.getJoueur("Joueur3"));
        assertEquals(joueur4, singleton2.getJoueur("Joueur4"));
        assertEquals("---- Logs ----", singleton2.getTextLog());
        assertFalse(singleton2.estTerminer());
        assertEquals(joueur3, singleton2.getJoueurCourant());
        assertEquals(0, singleton2.getIndexJoueurCourant());
    }

    /**
     * Method qui test les tours dans la partie.
     */
    @Test
    public void testToursPartie() {
        SingletonPartie partie = SingletonPartie.getInstance();

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
        SingletonPartie partie = SingletonPartie.getInstance();
        partie.initialiser();
        partie.save("32x32");

        for (int i = 1; i <= 50; i++) {
            assertEquals(partie.getPion(i), partie.peekHistorique().getPion(i));
        }
        partie.prochainJoueur();
        partie.deplacer(new int[]{3, 0}, new int[]{4, 1});
        final Damier damier1 = partie.peekHistorique();
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
        SingletonPartie partie = SingletonPartie.getInstance();
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
        partie.creerNewPartie(damier, List.of(new Joueur(Pion.CouleurPion.Blanc), new Joueur(Pion.CouleurPion.Noir)));

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
        damier1.ajoutPion(1, new Pion(Pion.CouleurPion.Noir));
        damier1.ajoutPion(33, new Pion(Pion.CouleurPion.Noir));
        SingletonPartie partie = SingletonPartie.getInstance();
        partie.creerNewPartie(damier1, List.of(new Joueur(Pion.CouleurPion.Blanc), new Joueur(Pion.CouleurPion.Noir)));

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
     * Le bon fonctionnement de la promotion des pions aux dames.
     */
    @Test
    public void testDeplacementPromotion() {
        Damier damier = new Damier();
        Pion pionN = new Pion(Pion.CouleurPion.Noir);
        Pion pionB = new Pion(Pion.CouleurPion.Blanc);
        damier.ajoutPion(damier.getManouryFrom2dPosition(8, 5), pionN);
        damier.ajoutPion(damier.getManouryFrom2dPosition(1, 6), pionB);

        SingletonPartie partie = SingletonPartie.getInstance();
        partie.creerNewPartie(damier, List.of(new Joueur(Pion.CouleurPion.Blanc), new Joueur(Pion.CouleurPion.Noir)));

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
     * Test la convertion de position 2D à position manoury.
     */
    @Test
    public void test2dToManoury() {
        assertEquals(5, SingletonPartie.getInstance().getManouryFrom2dPosition(0, 9));
    }

    /**
     * Test la method qui donne les déplacements valid.
     */
    @Test
    public void testGetValidMoves() {
        Damier damier = new Damier();
        SingletonPartie partie = SingletonPartie.getInstance();
        partie.creerNewPartie(damier, List.of(new Joueur(Pion.CouleurPion.Blanc), new Joueur(Pion.CouleurPion.Noir)));
        partie.initialiser();
        assertEquals(damier.getValidMoves(new int[]{0, 1}), partie.getValidMoves(new int[]{0, 1}));
    }
}
