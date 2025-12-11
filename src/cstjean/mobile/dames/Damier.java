package cstjean.mobile.dames;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Classe Damier qui représente le damier d'un jeu de dame.
 *
 * @author Isaak Fortin
 */
public class Damier {
    /**
     * ArrayList qui contient tous les pions du jeu de dame.
     */
    private final List<Pion> pions = new ArrayList<>(50);

    /**
     * Constructeur par défaut de la classe Damier.
     */
    protected Damier() {
        for (int i = 0; i < 50; i++) {
            pions.add(null);
        }
    }

    /**
     * Initialise le damier avec 40 pion 20 de chaque couleur.
     */
    protected void initialiser() {
        for (int i = 1; i <= 50; i++) {
            if (i < 21) {
                ajoutPion(i, new Pion(Pion.CouleurPion.Noir));
            }
            if (i > 30) {
                ajoutPion(i, new Pion());
            }
        }
    }

    /**
     * Ajoute un pion dans la list pions.
     *
     * @param position La position Manoury du pion.
     * @param pion Le pion à ajouter dans la list.
     */
    protected void ajoutPion(int position, Pion pion) {
        pions.set(position - 1, pion);
    }

    /**
     * Methode qui permet de savoir le nombre de pions dans la list.
     *
     * @return Le nombre de pion en Int
     */
    protected int getNombresPion() {
        int nombresPion = 0;

        for (Pion p : pions) {
            if (p != null) {
                nombresPion++;
            }
        }
        return nombresPion;
    }

    /**
     * Donne le nombre de pion dans le damier selon la couleur donnée.
     *
     * @param couleur La couleur de pion qu'on veut le compte.
     * @return Retourne le compte de pion de la couleur demandée.
     */
    protected int getNombresPionParCouleur(Pion.CouleurPion couleur) {
        int nombresPion = 0;

        for (Pion p : pions) {
            if (p != null && p.getCouleur().equals(couleur.toString())) {
                nombresPion++;
            }
        }
        return nombresPion;
    }

    /**
     * Getter de base de la list pions.
     *
     * @param position La position Manoury du pion
     * @return Le pion sur la position Manoury
     */
    protected Pion getPion(int position) {
        return pions.get(position - 1);
    }

    /**
     * Covertie la list de 50 de notation manoury pour un array 2D de pion.
     *
     * @return Un array 2D de pion du damier.
     */
    protected Pion[][] get2dArray() {
        Pion[][] grille = new Pion[10][10];
        int index = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                boolean isDarkSquare = (row % 2 == 0) ? (col % 2 == 1) : (col % 2 == 0);
                if (isDarkSquare) {
                    grille[row][col] = pions.get(index++);
                } else {
                    grille[row][col] = null;
                }
            }
        }
        return grille;
    }

    /**
     * Permet le déplacement et la prise de pion dans le damier.
     *
     * @param pionPos La position du pion.
     * @param targetPos La position dont on veut déplacer le pion.
     * @throws NoSuchElementException Quand aucun pion n'est trouvé sur la position donnée.
     * @throws IllegalArgumentException Quand le mouvement n'est pas valide.
     */
    protected void deplacer(Position pionPos, Position targetPos) throws NoSuchElementException, IllegalArgumentException {
        int pionManouryIndex = getManouryFrom2dPosition(pionPos.x(), pionPos.y());
        int targetManouryIndex = getManouryFrom2dPosition(targetPos.x(), targetPos.y());

        // Vérifie si position du pion n'est pas vide
        if (getPion(pionManouryIndex) == null) {
            throw new NoSuchElementException("Aucun pion trouver à [" + pionPos.x() +
                    ", " + pionPos.y() + "] / Manoury: " + pionManouryIndex);
        }

        boolean isValid = false;
        // Vérifie si la targetPos est un mouvement valide.
        for (List<Integer> validMove : getValidMoves(pionPos)) {
            if (targetPos.x() == validMove.get(0) && targetPos.y() == validMove.get(1)) {
                isValid = true;
                int hx = targetPos.x() - pionPos.x();
                int hy = targetPos.y() - pionPos.y();
                if (Math.abs(hx) >= 2 && Math.abs(hy) >= 2) {
                    // Fix need to find where the taken pion is in the diagonal
                    int dx = Integer.signum(targetPos.x() - pionPos.x());
                    int dy = Integer.signum(targetPos.y() - pionPos.y());

                    int x = pionPos.x() + dx;
                    int y = pionPos.y() + dy;
                    while (x != targetPos.x() && y != targetPos.y()) {
                        Pion mid = getPion(getManouryFrom2dPosition(x, y));

                        if (mid != null && !mid.getCouleur().equals(getPion(pionManouryIndex).getCouleur())) {
                            int takenManoury = getManouryFrom2dPosition(x, y);
                            ajoutPion(takenManoury, null); // remove captured piece
                            break;
                        }

                        x += dx;
                        y += dy;
                    }
                }

                ajoutPion(targetManouryIndex, getPion(pionManouryIndex));
                ajoutPion(pionManouryIndex, null);
                break;
            }
        }

        if (!isValid) {
            throw new IllegalArgumentException("Movement Invalid [" + targetPos.x() + "," + targetPos.y() + "]");
        }
    }

    /**
     * Donne une liste de tous les mouvements valid d'un pion.
     *
     * @param pionPos La position du pion dont veut les déplacements valide.
     * @return Une liste avec les coordonnées des déplacements valide.
     * @throws NoSuchElementException Quand aucun pion n'est trouvé sur la position donnée.
     */
    protected List<List<Integer>> getValidMoves(Position pionPos) throws NoSuchElementException {
        List<List<Integer>> validMoves = new ArrayList<>();
        int pionManouryIndex = getManouryFrom2dPosition(pionPos.x(), pionPos.y());
        boolean blockedNorthEast = false;
        boolean blockedSouthEast = false;
        boolean blockedNorthWest = false;
        boolean blockedSouthWest = false;

        // Vérifie si position du pion n'est pas vide
        if (getPion(pionManouryIndex) == null) {
            throw new NoSuchElementException("Aucun pion trouver à [" + pionPos.x() +
                    ", " + pionPos.y() + "] / Manoury: " + pionManouryIndex);
        }

        Pion pion = getPion(pionManouryIndex);

        List<Position> moves = pion.getPosition();

        for (Position move : moves) {
            int x = move.x() + pionPos.x();
            int y = move.y() + pionPos.y();

            // Vérifie que l'index est pas out of bounds
            if (x > 9 || x < 0 || y > 9 || y < 0) {
                continue;
            }

            // Vérification des diagonal
            if (move.x() < 0) {
                if (move.y() > 0 && blockedNorthEast) {
                    continue;
                } else if (move.y() < 0 && blockedNorthWest) {
                    continue;
                }
            } else {
                if (move.y() > 0 && blockedSouthEast) {
                    continue;
                } else if (move.y() < 0 && blockedSouthWest) {
                    continue;
                }
            }

            // Prise d'un Pion
            if (getPion(getManouryFrom2dPosition(x, y)) != null) {
                int dx = Integer.signum(move.x());
                int dy = Integer.signum(move.y());
                int hopX = x + dx;
                int hopY = y + dy;

                // Vérifie que l'index est pas out of bounds
                if (hopX > 9 || hopX < 0 || hopY > 9 || hopY < 0) {
                    continue;
                }

                if (move.x() < 0) {
                    if (move.y() > 0) {
                        blockedNorthEast = true;
                    } else {
                        blockedNorthWest = true;
                    }
                } else {
                    if (move.y() > 0) {
                        blockedSouthEast = true;
                    } else {
                        blockedSouthWest = true;
                    }
                }

                // Vérifie que le pion de ne prend pas un pion de la même couleur
                if (Objects.equals(pion.getCouleur(), getPion(getManouryFrom2dPosition(x, y)).getCouleur())) {
                    continue;
                }

                // Vérifie le prochain hop pour une prise possible
                if (getPion(getManouryFrom2dPosition(hopX, hopY)) != null) {
                    continue;
                }

                // Change le validMove pour inclure le next hop
                x += dx;
                y += dy;
            }

            // Ajoute le validMove
            validMoves.add(List.of(x, y));
            if (blockedNorthEast && blockedSouthEast && blockedNorthWest && blockedSouthWest) {
                break;
            }
        }
        return validMoves;
    }

    /**
     * Convertie la position d'un pion selon un array 2D en position notation manoury.
     *
     * @param x La position x du pion.
     * @param y La position y du pion.
     * @return La position manoury du pion.
     * @throws IllegalArgumentException Quand position est sur une case blanche ou une erreur dans la convertion.
     */
    protected int getManouryFrom2dPosition(int x, int y) throws IllegalArgumentException {
        if ((x + y) % 2 == 0) {
            throw new IllegalArgumentException("Position invalide donner une case blanche");
        }
        int base = x * 5;
        int offset = (x % 2 == 0) ? (y - 1) / 2 : y / 2;

        return base + offset + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Damier damier = (Damier) o;
        return Objects.equals(pions, damier.pions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pions);
    }

    /**
     * Créer une copie du damier.
     *
     * @return Une copie du damier.
     */
    public Damier instantiate() {
        Damier copie = new Damier();
        for (int i = 1; i <= 50; i++) {
            copie.ajoutPion(i, this.getPion(i));
        }
        return copie;
    }
}
