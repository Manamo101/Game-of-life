import java.util.concurrent.CountDownLatch;

public class PartialBoardAnalyzer extends Thread {
    private final int threadID;
    private final boolean[][] currentBoard;
    private final boolean[][] nextBoard;
    private final int[] rowsRange;
    private final CountDownLatch readyThreadCounter;
    private final CountDownLatch callingThreadBlocker;
    private final CountDownLatch completedThreadCounter;
    PartialBoardAnalyzer(int threadID, boolean[][] currentBoard, boolean[][] nextBoard, int[] rowsRange,
                        CountDownLatch readyThreadCounter, CountDownLatch callingThreadBlocker, CountDownLatch completedThreadCounter) {
        this.threadID = threadID;
        this.currentBoard = currentBoard;
        this.nextBoard = nextBoard;
        this.rowsRange = rowsRange;
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadBlocker = callingThreadBlocker;
        this.completedThreadCounter = completedThreadCounter;
    }

    public void run() {
        readyThreadCounter.countDown();
        try {
            callingThreadBlocker.await();
            doYourJob();
            printThreadDetails();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            completedThreadCounter.countDown();
        }
    }

    private void doYourJob(){
        for (int i = rowsRange[0]; i < rowsRange[1]; i++) {
            for (int j = 0; j < currentBoard[0].length; j++) {

                int alive = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (!(k == 0 && l == 0)) {

                            if (getElement(i + l,j + k)){
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

            }
        }
    }

    private void printThreadDetails(){
        System.out.printf("ThreadID %2d: rows: %2d:%-2d (%d) cols: 0-%d (%d)\n"
                ,threadID, rowsRange[0], rowsRange[1]-1, rowsRange[1]-rowsRange[0], currentBoard[0].length-1, currentBoard[0].length);
    }
    boolean getElement(int i, int j){
        // Torus Model
        int rows = currentBoard.length;
        int columns = currentBoard[0].length;
        return currentBoard[(i+ rows) % rows][(j+ columns) % columns];
    }
}
