import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        boolean[][] board = {{false, true, true, false},
                             {true, false, false, true},
                             {false, true, true, false}};
        printBoard(board);
        int iterations = 20;
//        int rows = board.length;
//        int columns = board[0].length;
//        int threads = 1;
//        int mod = rows%threads;
//        for (int i = 1; i <= threads ; i++){
//            if (mod-- > 0){
//                System.out.println(rows/threads + 1);
//            }
//            else {
//                System.out.println(rows/threads);
//            }
//        }
//        new Counter().start();
//        Thread.sleep(3000);
//        new Counter().start();
    }
    public static int[] getElement(int i, int j){
        int rows = 4;
        int columns = 4;
//        return new int[]{ i != -1 ? i%rows : i+rows, j != -1 ? j%columns : j+columns};
        return new int[]{(i+rows) % rows, (j+columns) % columns};
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