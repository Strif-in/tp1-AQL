package uni.aql.model;

public class Cell {

    private Piece piece;

    public boolean isNotEmpty() {
        return piece != null;
    }

    public void setValue(Piece piece) {
        this.piece = piece;
    }

    public Piece getValue() {
        return this.piece;
    }
}
