import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        boolean[][] board = {{false, false, false, false, false, false}, //beehive (still)
//                             {false, false, true, true, false, false},
//                             {false, true, false, false, true, false},
//                             {false, false, true, true, false, false},
//                             {false, false, false, false, false, false}};
//        boolean[][] board = {{false, false, false, false, false, false}, //block (still)
//                {false, false, false, false, false, false},
//                {false, false, true, true, false, false},
//                {false, false, true, true, false, false},
//                {false, false, false, false, false, false}};
//        boolean[][] board = {{false, false, false, false, false, false}, //blinker (oscillator)
//                {false, false, true, false, false, false},
//                {false, false, true, false, false, false},
//                {false, false, true, false, false, false},
//                {false, false, false, false, false, false}};
        boolean[][] board = {{false, false, false, false, false, false}, //glider (spaceship) 100 iterations
                {false, true, false, false, false, false},
                {false, false, true, false, false, false},
                {true, true, true, false, false, false},
                {false, false, false, false, false, false}};


        boolean[][] newBoard = new boolean[board.length][board[0].length];
        newBoard[0][0] = true;
        int iterations = 100;
        int threads = 1;

        printBoard(board);
        for (int it = 0; it < iterations; it++){
            WorkManager workManager = new WorkManager(threads);
            workManager.startIteration(board, newBoard);
            printBoard(newBoard);
            board = newBoard;
            newBoard = new boolean[board.length][board[0].length];
        }


//        int rows = 100;
//        int columns = 10;
//        int threads = 6;
//        int mod = rows%threads;
//        int previousGivenIndexes = 0;
//        for (int i = 0; i < threads ; i++){
//            if (mod-- > 0){
//                System.out.println(previousGivenIndexes);
//                System.out.println(previousGivenIndexes + rows/threads + 1 -1); //minus one bc of included indexes only
//                System.out.println(previousGivenIndexes + rows/threads + 1 - previousGivenIndexes);
//                System.out.println();
//                previousGivenIndexes += rows/threads + 1;
//            }
//            else {
//                System.out.println(previousGivenIndexes);
//                System.out.println(previousGivenIndexes + rows/threads -1); //ditto
//                System.out.println(previousGivenIndexes + rows/threads + 1 - previousGivenIndexes);
//                System.out.println();
//                previousGivenIndexes += rows/threads;
//            }
//        }
//        new Counter().start();
//        Thread.sleep(3000);
//        new Counter().start();
    }
//    public static int[] getElement(int i, int j){
//        int rows = 4;
//        int columns = 4;
////        return new int[]{ i != -1 ? i%rows : i+rows, j != -1 ? j%columns : j+columns};
//        return new int[]{(i+rows) % rows, (j+columns) % columns};
//    }
    public static void printBoard(boolean[][] board){
        for (boolean[] row : board){
            for (boolean cell : row){
                System.out.print(cell ? "X " : "- ");
            }
            System.out.println();
        }
    }
}