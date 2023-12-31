import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.concurrent.CountDownLatch;

public class GameFrame extends JFrame{
    private final JPanel boardPanel;
    JLabel iterations;
    private CountDownLatch startGameLatch;
    public GameFrame(CountDownLatch startGameLatch, boolean[][] board){
        this.startGameLatch = startGameLatch;
        boardPanel = new JPanel(new GridLayout(board.length,board[0].length));
        this.add(boardPanel, BorderLayout.CENTER);
    }

    {
        this.setTitle("Conway's Game of Life");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Game of life");
        title.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 30));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        JLabel version = new JLabel("version 23/3");
        version.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 18));
        version.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        version.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start");
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> startGameLatch.countDown());

        iterations = new JLabel("Iterations: 0");
        iterations.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 18));
        iterations.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        iterations.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        JPanel interactivePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        interactivePanel.add(startButton);
        interactivePanel.add(Box.createRigidArea(new Dimension(50,0)));
        interactivePanel.add(iterations);

        topPanel.add(title);
        topPanel.add(version);
        topPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        topPanel.add(interactivePanel);
        topPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(topPanel, BorderLayout.NORTH);

        this.setMinimumSize(new Dimension(800,600));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void printBoard(boolean[][] board, int it){
        boardPanel.removeAll();
        for (boolean[] row : board){
            for (boolean cell : row){
                JPanel tile = new JPanel();
                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                if (cell){
                    tile.setBackground(Color.DARK_GRAY);
                }
                else {
                    tile.setBackground(Color.white);
                }
                boardPanel.add(tile);
            }
        }
        iterations.setText("iterations: " + it);
        this.repaint();
        this.pack();
    }
    public static String[] getArguments(){
        String path = getFilePath();
        String threads = String.valueOf(getNumberOfThreads());
        return new String[]{threads, path};
    }
    private static int getNumberOfThreads(){
        int threads;
        boolean isFirst = true;
        do {
            if (!isFirst){
                JOptionPane.showMessageDialog(null, "invalid number","ERROR", JOptionPane.ERROR_MESSAGE);
            }
            String response = JOptionPane.showInputDialog(null," type number of threads:","how many threads do you want to use?",JOptionPane.PLAIN_MESSAGE);
            if (response == null){
                System.exit(1);
            }
            threads = FileParser.parseInt(response);
            isFirst = false;
        }
        while (threads < 1);
        return threads;
    }
    private static String getFilePath(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileFilter(new FileNameExtensionFilter("text file", "txt"));
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        else {
            System.exit(1);
            return null;
        }
    }
    public static void iterationError(int i){
        if (i < 1){
            JOptionPane.showMessageDialog(null, "Number of iterations cannot be lesser than 1", "ERROR" , JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
