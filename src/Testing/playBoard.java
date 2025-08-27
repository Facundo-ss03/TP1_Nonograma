package Testing;

public class playBoard {
    enum Cells { IS_PAINTED, IS_X }
    private String[][] board;

    public playBoard(int size) {
        board = new String[size][size]; 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.random() < 0.5) {
                    board[i][j] = Cells.IS_PAINTED.name();
                } else {
                    board[i][j] = Cells.IS_X.name();
                }
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }
}
