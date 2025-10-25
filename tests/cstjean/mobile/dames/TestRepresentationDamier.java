package cstjean.mobile.dames;

import junit.framework.TestCase;

/**
 * Class de test pour ReprsesentationDamier.
 */
public class TestRepresentationDamier extends TestCase {
    /**
     * Methode qui test les représentation du damier.
     */
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
