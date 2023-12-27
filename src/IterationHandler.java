public class IterationHandler {
    boolean[][] currentBoard;
    boolean[][] nextBoard;
    int threads;
    int iterations;
    IterationHandler(boolean[][] board, int iterations, int threads) {
        this.currentBoard = board;
        this.iterations = iterations;
        this.threads = threads;
    }
    public void startGame() {
        int rows = currentBoard.length;
        int columns = currentBoard[0].length;
        int mod = rows % threads;

        for (int it = 0; it < iterations; it++){
            nextBoard = new boolean[rows][columns];
            int id = 0;
            int givenRows = 0;
            for (int i = 1; i <= threads; i++) {
                if (mod-- > 0) {

                    System.out.println(rows / threads + 1);
                } else {
                    System.out.println(rows / threads);
                }
                id++;
            }

        }
    }
}
