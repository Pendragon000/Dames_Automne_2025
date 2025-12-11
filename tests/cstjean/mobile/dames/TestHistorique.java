package cstjean.mobile.dames;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.PipedInputStream;

/**
 * Class de test pour la class d'historique.
 */
public class TestHistorique {

    /**
     * Test la création simple d'un historique de damier.
     */
    @Test
    public void testCreer() {
        Damier damier = new Damier();
        Historique historique = new Historique();
        StringBuilder log = new StringBuilder();
        log.append("---- Logs ----");
        assertEquals(log.toString(), historique.getLog());
        Damier damier1 = damier.instantiate();
        log.append("\n(32x27)");
        historique.save(damier1, "\n(32x27)");
        assertEquals(log.toString(), historique.getLog());
        damier.initialiser();
        log.append("\n18-21");
        Damier damier2 = damier.instantiate();
        historique.save(damier2, "\n18-21");
        assertEquals(log.toString(), historique.getLog());
        damier.deplacer(new Position(6, 1), new Position(5, 0));
        Damier damier3 = damier.instantiate();
        log.append("\n(18-22)");
        historique.save(damier3, "\n(18-22)");
        assertEquals(log.toString(), historique.getLog());

        assertEquals(damier3, historique.peek());
        assertEquals(damier3, historique.undo());
        assertEquals(damier2, historique.undo());
        assertEquals(damier1, historique.undo());
    }
}
