package cstjean.mobile.dames;

import junit.framework.TestCase;
import org.junit.Assert;

/**
 * Class de test pour la class d'historique.
 */
public class TestHistorique extends TestCase {

    /**
     * Test la création simple d'un historique de damier.
     */
    public void testCreer() {
        Damier damier = new Damier();
        Historique historique = new Historique();
        Damier damier1 = damier.instantiate();
        historique.save(damier1);
        damier.initialiser();
        Damier damier2 = damier.instantiate();
        historique.save(damier2);
        damier.deplacer(new int[]{6, 1}, new int[]{5, 0});
        Damier damier3 = damier.instantiate();
        historique.save(damier3);

        Assert.assertEquals(damier3, historique.peek());
        Assert.assertEquals(damier3, historique.undo());
        Assert.assertEquals(damier2, historique.undo());
        Assert.assertEquals(damier1, historique.undo());
    }
}
