package cstjean.mobile.dames;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;



/**
 * Suite de test qui test toutes les classes.
 *
 * @author Isaak Fortin
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
    TestDamier.class,
    TestPion.class,
    TestDamier.class,
    TestPartie.class,
    TestJoueur.class,
    TestRepresentationDamier.class,
    TestHistorique.class,
    TestSingletonPartie.class,
    TestValidMoves.class
})
public class TestComplet {

}
