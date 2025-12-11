package cstjean.mobile.dames;

import java.util.List;

public record Move(Position targetPosition, List<Position> takenPionPosition, boolean hasTakenPion) {
}
