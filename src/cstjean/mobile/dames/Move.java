package cstjean.mobile.dames;

import java.util.List;

/**
 * Record qui enregistre toutes les données d'un seul mouvement.
 *
 * @param targetPosition La position final du mouvement.
 * @param takenPionPosition Liste de position de pion qui sont pris.
 * @param hasTakenPion Vrai si un ou plusieurs pions sont pris.
 */
public record Move(Position targetPosition, List<Position> takenPionPosition, boolean hasTakenPion) {
}
