import java.io.File;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        String[] input = Game.getArguments();
        File file = new File(input[1]);
        int threads = FileParser.parseInt(input[0]);
        int iterations = FileParser.getNumberOfIterations(file);
        boolean [][] board = FileParser.getArray(file);
        if (iterations < 1){
            Game.iterationError();
        }
        boolean[][] newBoard = new boolean[board.length][board[0].length];

        CountDownLatch startGameLatch = new CountDownLatch(1);
        Game frame = new Game(startGameLatch, board);
        frame.printBoard(board, 0);
        // waits until the start button be pressed
        try {
            startGameLatch.await();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        for (int it = 1; it <= iterations; it++){
            System.out.println();
            System.out.println("iteration: " + it);
            RowsSplitter workManager = new RowsSplitter(threads);
            workManager.startIteration(board, newBoard);
            frame.printBoard(newBoard, it);
            board = newBoard;
            newBoard = new boolean[board.length][board[0].length];
            try{
                Thread.sleep(700);
            }
            catch (InterruptedException e){
                e.getMessage();
            }
        }
    }
}