package cstjean.mobile.dames;

/**
 * Class qui s'occupe de représenter un damier en différente façon.
 */
public class RepresentationDamier {

    /**
     * Le saut de ligne par défaut du system.
     */
    protected static final String SAUT_LIGNE = System.lineSeparator();

    /**
     * Créer la représenation en string d'un damier.
     *
     * @param damier Le damier à représenter.
     * @return La représentation du damier en String.
     */
    protected static String representationString(Damier damier) {
        StringBuilder builder = new StringBuilder();
        boolean videPair = false;
        int positionManoury = 1;
        for (int i = 1; i <= 100; i++) {
            if ((i % 2 == 0) == !videPair) {
                String rep = "-";
                if (damier.getPion(positionManoury) != null) {
                    rep = damier.getPion(positionManoury).getRepresentation();
                }
                builder.append(rep);
                positionManoury++;
            } else {
                builder.append("-");
            }
            if (i % 10 == 0) {
                videPair = !videPair;
                builder.append(SAUT_LIGNE);
            }
        }
        return builder.toString();
    }
}
