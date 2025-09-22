package cstjean.mobile.dames;

import java.util.Objects;
import junit.framework.TestCase;

/**
 * Class qui teste Pion.
 *
 * @author Isaak Fortin
 */
public class TestPion extends TestCase {
    /**
     * La représentation d'un pion blanc.
     */
    protected static final String REPRESENTATION_PION_BLANC = "p";
    /**
     * La représentation d'un pion noir.
     */
    protected static final String REPRESENTATION_PION_NOIR = "P";
    /**
     * Teste la création d'un pion.
     */

    public void testCreer() {
        // Teste du constructeur avec argument.
        Pion pion1 = creerPion(Pion.CouleurPion.Noir);
        assertEquals("noir", pion1.getCouleur());
        assertEquals(getExpectedRepresentation(pion1), pion1.getRepresentation());

        Pion pion2 = creerPion(Pion.CouleurPion.Blanc);
        assertEquals("blanc", pion2.getCouleur());
        assertEquals(getExpectedRepresentation(pion2), pion2.getRepresentation());

        // Teste du constructeur sans argument.
        Pion pion3 = creerPion();
        assertEquals("blanc", pion3.getCouleur());
        assertEquals(getExpectedRepresentation(pion3), pion3.getRepresentation());
    }

    /**
     * Créer un pion.
     *
     * @param couleur La couleur du pion selon l'énumération CouleurPion.
     * @return Retourne le pion créer.
     */
    protected Pion creerPion(Pion.CouleurPion couleur) {
        return new Pion(couleur);
    }

    /**
     * Créer un pion utilisant l'initialisateur par défaut.
     *
     * @return Un pion blanc.
     */
    protected Pion creerPion() {
        return new Pion();
    }

    /**
     * Donne la représentation attendue du pion dans la console.
     *
     * @param pion Le pion qu'on a besoin de la représentation
     * @return La représentation attendue du pion en String.
     */
    protected String getExpectedRepresentation(Pion pion) {
        if (Objects.equals(pion.getCouleur(), "blanc")) {
            return REPRESENTATION_PION_BLANC;
        } else {
            return REPRESENTATION_PION_NOIR;
        }
    }
}
