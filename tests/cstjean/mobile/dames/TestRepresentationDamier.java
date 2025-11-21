package cstjean.mobile.dames;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Class de test pour ReprsesentationDamier.
 */
public class TestRepresentationDamier {
    /**
     * Methode qui test les représentation du damier.
     */
    @Test
    public void testRepresentation() {
        Damier damier = new Damier();
        damier.initialiser();
        assertEquals("-P-P-P-P-P" + RepresentationDamier.SAUT_LIGNE +
                "P-P-P-P-P-" + RepresentationDamier.SAUT_LIGNE +
                "-P-P-P-P-P" + RepresentationDamier.SAUT_LIGNE +
                "P-P-P-P-P-" + RepresentationDamier.SAUT_LIGNE +
                "----------" + RepresentationDamier.SAUT_LIGNE +
                "----------" + RepresentationDamier.SAUT_LIGNE +
                "-p-p-p-p-p" + RepresentationDamier.SAUT_LIGNE +
                "p-p-p-p-p-" + RepresentationDamier.SAUT_LIGNE +
                "-p-p-p-p-p" + RepresentationDamier.SAUT_LIGNE +
                "p-p-p-p-p-" + RepresentationDamier.SAUT_LIGNE, RepresentationDamier.representationString(damier));
    }
}
