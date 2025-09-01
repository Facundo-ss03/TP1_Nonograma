/*package Model;

public class Nonogram {
    private Playboard solutionBoard;
    private Playboard playerBoard;
    private GameState currentState;

    public enum GameState {
        INIT, PLAYING, COMPLETED, FAILED
    }

    public Nonogram() {
        currentState = GameState.INIT;
    }

    public void startGame(int size) {
        solutionBoard = new Playboard(size);
        solutionBoard.initializeRandom(); // Or load from file for real puzzles

        playerBoard = new Playboard(size); // Starts blank
        currentState = GameState.PLAYING;
    }

    public void toggleCell(int row, int col) {
        if (currentState == GameState.PLAYING) {
            playerBoard.toggleCell(row, col);
        }
    }

    public boolean checkSolution() {
        boolean solved = compareBoards();
        currentState = solved ? GameState.COMPLETED : GameState.FAILED;
        return solved;
    }

    private boolean compareBoards() {
        int size = playerBoard.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (solutionBoard.getCell(i, j) == PlayBoard.Cells.IS_PAINTED &&
                    playerBoard.getCell(i, j) != PlayBoard.Cells.IS_PAINTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public PlayBoard getPlayerBoard() {
        return playerBoard;
    }

    public PlayBoard getSolutionBoard() {
        return solutionBoard;
    }

    public GameState getCurrentState() {
        return currentState;
    }

}
*/