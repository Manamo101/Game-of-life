import java.util.concurrent.CountDownLatch;

public class RowsSplitter {
    private final int threads;
    RowsSplitter(int threads) {
        this.threads = threads;
    }
    public void startIteration(boolean[][] currentBoard, boolean[][] nextBoard) {
        int rows = currentBoard.length;
        int mod = rows % threads;
        int previousGivenIndexes = 0;

        CountDownLatch readyThreadCounter = new CountDownLatch(threads);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(threads);

        for (int i = 1; i <= threads; i++) {
            //first included, second excluded
            int[] rowsRange = new int[2];
            if (mod-- > 0){
                rowsRange[0] = previousGivenIndexes;
                rowsRange[1] = previousGivenIndexes + rows/threads + 1;

                System.out.println();
                new PartialBoardAnalyzer(i, currentBoard, nextBoard, rowsRange, readyThreadCounter, callingThreadBlocker, completedThreadCounter).start();

                previousGivenIndexes += rows/threads + 1;
            }
            else {
                rowsRange[0] = previousGivenIndexes;
                rowsRange[1] = previousGivenIndexes + rows/threads;

                new PartialBoardAnalyzer(i, currentBoard, nextBoard, rowsRange, readyThreadCounter, callingThreadBlocker, completedThreadCounter).start();

                previousGivenIndexes += rows/threads;
            }
        }

        try {
            readyThreadCounter.await();
            callingThreadBlocker.countDown();
            completedThreadCounter.await();
        }
        catch (InterruptedException e){
            e.getMessage();
        }
    }
}
