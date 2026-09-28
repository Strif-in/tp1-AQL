package uni.aql.model;

public enum TicTacToePiece implements Piece{
    X ,
    O;

    @Override
    public boolean matches(Piece piece) {
        if (piece == null) {
            return false;
        }
        return this == piece;
    }
}

