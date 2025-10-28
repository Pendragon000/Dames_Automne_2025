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
    protected void deplacer(int[] pionPos, int[] targetPos) throws NoSuchElementException, IllegalArgumentException {
        int pionManouryIndex = getManouryFrom2dPosition(pionPos[0], pionPos[1]);
        int targetManouryIndex = getManouryFrom2dPosition(targetPos[0], targetPos[1]);

        // Vérifie si position du pion n'est pas vide
        if (getPion(pionManouryIndex) == null) {
            throw new NoSuchElementException("Aucun pion trouver à [" + pionPos[0] +
                    ", " + pionPos[1] + "] / Manoury: " + pionManouryIndex);
        }

        boolean isValid = false;
        // Vérifie si la targetPos est un mouvement valide.
        for (List<Integer> validMove : getValidMoves(pionPos)) {
            if (targetPos[0] == validMove.get(0) && targetPos[1] == validMove.get(1)) {
                isValid = true;
                int hx = targetPos[0] - pionPos[0];
                int hy = targetPos[1] - pionPos[1];
                if (Math.abs(hx) == 2 && Math.abs(hy) == 2) {
                    int takenManoury = getManouryFrom2dPosition(pionPos[0] + hx / 2, pionPos[1] + hy / 2);
                    ajoutPion(takenManoury, null);
                }

                ajoutPion(targetManouryIndex, getPion(pionManouryIndex));
                ajoutPion(pionManouryIndex, null);
                break;
            }
        }

        if (!isValid) {
            throw new IllegalArgumentException("Movement Invalid [" + targetPos[0] + "," + targetPos[1] + "]");
        }
    }

    /**
     * Donne un liste de tous les mouvement valid d'un pion.
     *
     * @param pionPos La position du pion dont veut les déplacements vailde.
     * @return Une liste avec les coordonnées des déplacement valide.
     * @throws NoSuchElementException Quand aucun pion n'est trouvé sur la position donnée.
     */
    protected List<List<Integer>> getValidMoves(int[] pionPos) throws NoSuchElementException {
        List<List<Integer>> validMoves = new ArrayList<>();
        int pionManouryIndex = getManouryFrom2dPosition(pionPos[0], pionPos[1]);

        // Vérifie si position du pion n'est pas vide
        if (getPion(pionManouryIndex) == null) {
            throw new NoSuchElementException("Aucun pion trouver à [" + pionPos[0] +
                    ", " + pionPos[1] + "] / Manoury: " + pionManouryIndex);
        }

        Pion pion = getPion(pionManouryIndex);

        List<List<Integer>> moves = pion.getPosition();

        for (List<Integer> move : moves) {
            int x = move.get(0) + pionPos[0];
            int y = move.get(1) + pionPos[1];

            // Vérifie que l'index est pas out of bounds
            if (x > 9 || x < 0 || y > 9 || y < 0) {
                continue;
            }

            // Vérifie qu'un pion n'est pas à la position
            if (getPion(getManouryFrom2dPosition(x, y)) != null) {

                // Vérifie le prochain hop pour une prise possible
                if (getPion(getManouryFrom2dPosition(x + move.get(0), y + move.get(1))) != null) {
                    continue;
                }

                // Change le validMove pour inclure le nect hop
                x += move.get(0);
                y += move.get(1);
            }

            // Ajoute le validMove
            validMoves.add(List.of(x, y));
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
        int manoury = base + offset + 1;

        // Vérifie si la convertion manoury est valide.
        if (getPion(manoury) != get2dArray()[x][y]) {
            throw new IllegalArgumentException("Erreur de convertion entre la position 2D et la notation manoury");
        }
        return manoury;
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
