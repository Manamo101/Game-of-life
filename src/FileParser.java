import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser {
    public static boolean[][] getArray(File file) {
        boolean[][] board;
        int iterations;
        int i = 0;
        try {
            Scanner fileScanner = new Scanner(file);
            Scanner lineScanner;
            int rows = parseInt(fileScanner.nextLine().trim());
            int columns = parseInt(fileScanner.nextLine().trim());
            if(rows > 0 && columns > 0){
                board = new boolean[rows][columns];
            }
            else {
                System.out.println("size is not valid");
                return null;
            }
            fileScanner.nextLine();
            iterations = parseInt(fileScanner.nextLine().trim());
            while (fileScanner.hasNext()) {
                i++;
                lineScanner = new Scanner(fileScanner.nextLine());
                while (lineScanner.hasNext()){
                    try{
                        int x = parseInt(lineScanner.next().trim());
                        int y = parseInt(lineScanner.next().trim());
                        if ((x >= 0 && x < rows) && (y >= 0 && y < columns)){
                            board[x][y] = true;
                        }
                        else {
                            System.out.println("index out of bound");
                            return null;
                        }
                    }
                    catch (Exception e){
                        System.out.println("bad format");
                        return null;
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("file does not exist");
            return null;
        }
        if (i != iterations){
            System.out.println("declared number of living cells do not match with number of written cell indexes in the file");
            return null;
        }
        return board;
    }
    public static int getNumberOfIterations(File file) {
        int i = 0;
        try {
            Scanner fileScanner = new Scanner(file);
            fileScanner.nextLine();
            fileScanner.nextLine();
            i = parseInt(fileScanner.nextLine().trim());
        }
        catch (FileNotFoundException e) {
            System.out.println("file does not exist");
        }
        return i;
    }
    public static int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        }
        catch (Exception e) {
            System.out.println("not a number");
            return -1;
        }
    }
}
