package Model;

import java.util.ArrayList;
import java.util.List;

public class PlayBoard {
    enum Cells { IS_PAINTED, IS_X, IS_BLANK }
    private Cells[][] board;
    private int size;
    
    public PlayBoard(int size) {
        this.size = size;
        createBlankBoard(size);
    }

    public enum Difficulty {
    EASY(5), MEDIUM(10), HARD(15);   // remember to use these keywords on the visual interface tomatch XD
    private final int size;
    Difficulty(int size) { this.size = size; }
    public int getSize() { return size; }
}

    private void createBlankBoard(int size) {
        board = new Cells[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Cells.IS_BLANK;
            }
        }
    }

    
    public List<Integer> getRowHints(int row) {
        List<Integer> hints = new ArrayList<>();
        int count = 0;

        for (int j = 0; j < board[row].length; j++) {
            if (board[row][j] == Cells.IS_PAINTED) {
                count++;
            } else {
                if (count > 0) {
                    hints.add(count);
                    count = 0;
                }
            }
        }
        if (count > 0) { 
            hints.add(count);
        }

        if (hints.isEmpty()) {
            hints.add(0); 			// 0  no painted cells
        }

        return hints;
    }

    public List<Integer> getColumnHints(int col) {
        List<Integer> hints = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == Cells.IS_PAINTED) {
                count++;
            } else {
                if (count > 0) {
                    hints.add(count);
                    count = 0;
                }
            }
        }
        if (count > 0) {
            hints.add(count);
        }

        if (hints.isEmpty()) {
            hints.add(0);
        }

        return hints;
    }

    
    public Cells[][] getBoard() {
        return board;
    }
    
    public void toggleCell(int row, int col) {
        switch (board[row][col]) {
            case IS_BLANK:
                board[row][col] = Cells.IS_PAINTED;
                break;
            case IS_PAINTED:
                board[row][col] = Cells.IS_X;
                break;
            case IS_X:
                board[row][col] = Cells.IS_BLANK;
                break;
        }
    }

    public Cells getCell(int row, int col) {
        return board[row][col];
    }

    public void setCell(int row, int col, Cells value) {
        board[row][col] = value;
    }
    public void initializeRandom() {
        for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = Math.random() < 0.5 ? Cells.IS_PAINTED : Cells.IS_X;
                }
        }
    }
    public void resetBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = Cells.IS_BLANK;
            }
        }
    }
    
    public int getSize(){
        return size;
    }
}
