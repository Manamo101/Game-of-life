import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File(args[1]);
        int threads = FileParser.parseInt(args[0]);
        int iterations = FileParser.getNumberOfIterations(file);
        boolean [][] board = FileParser.getArray(file);
        if (threads < 0 || iterations < 1 || board == null){
            System.out.println("inadequacy detected");
            System.exit(1);
        }
        boolean[][] newBoard = new boolean[board.length][board[0].length];
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




        printBoard(board);
        for (int it = 0; it < iterations; it++){
            WorkManager workManager = new WorkManager(threads);
            workManager.startIteration(board, newBoard);
            printBoard(newBoard);
            board = newBoard;
            newBoard = new boolean[board.length][board[0].length];
            try{
                Thread.sleep(300);
            }
            catch (InterruptedException e){
                e.getMessage();
            }
        }
    }
    public static void printBoard(boolean[][] board){
        for (boolean[] row : board){
            for (boolean cell : row){
                System.out.print(cell ? "X " : "- ");
            }
            System.out.println();
        }
    }
}