package cstjean.mobile.dames;

import junit.framework.TestSuite;

/**
 * Suite de test qui test toutes les classes.
 *
 * @author Isaak Fortin
 */
public class TestComplet {
    /**
     * TestSuite de la Suite TestComplet.
     *
     * @return La suite
     */
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestDamier.class);
        suite.addTestSuite(TestPion.class);
        suite.addTestSuite(TestDamier.class);
        return suite;
    }
}
