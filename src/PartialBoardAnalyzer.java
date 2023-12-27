public class PartialBoardAnalyzer extends Thread {
    int threadID;
    boolean[][] currentBoard;
    boolean[][] nextBoard;
    int[] rowsRange;
    PartialBoardAnalyzer(int threadID, boolean[][] currentBoard, boolean[][] nextBoard, int[] rowsRange) {
        this.threadID = threadID;
        this.currentBoard = currentBoard;
        this.nextBoard = nextBoard;
        this.rowsRange = rowsRange;
    }

    public void run() {
        for (int i = rowsRange[0]; i <= rowsRange[1]; i++) {
            for (int j = 0; j < currentBoard[0].length; j++) {

                int alive = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k != 0 && l != 0) {

                            if (getElement(k,l)){
                                alive++;
                            }

                        }
                    }
                }
                if (currentBoard[i][j]){
                    nextBoard[i][j] = alive == 2 || alive == 3;
                }
                else {
                    nextBoard[i][j] = alive == 3;
                }
                System.out.printf("ThreadID %2d: rows: %2d:%2d (%2d) cols: 0-%-2d (%2d)"
                        ,threadID, rowsRange[0], rowsRange[1], rowsRange[1]-rowsRange[0]+1, currentBoard[0].length-1, currentBoard[0].length);

            }
        }
    }

    public boolean getElement(int i, int j){
        // Torus Model
//        return new int[]{ i != -1 ? i%totalRows : i+totalRows, j != -1 ? j%columns : j+columns};
//        return new int[]{(i+ TOTAL_ROWS) % TOTAL_ROWS, (j+ TOTAL_COLUMNS) % TOTAL_COLUMNS};
        int rows = currentBoard.length;
        int columns = currentBoard[0].length;
        return currentBoard[(i+ rows) % rows][(j+ columns) % columns];
    }
}
