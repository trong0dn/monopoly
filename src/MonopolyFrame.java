import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;

public class MonopolyFrame extends JFrame {
    private JFrame mainFrame;
    private Monopoly model;
    private GameBoard gameBoard = new GameBoard();
    private ArrayList<JLabel> squareLabels = new ArrayList<>();

    public MonopolyFrame() {
        super();
        this.mainFrame = new JFrame("Monopoly!");
        displayGUI();
        model = new Monopoly();
        model.play();
    }

    private ArrayList<JLabel> grabImages() {
        ArrayList<JLabel> imageSquareList = new ArrayList<>();
        for (int i = 0; i < gameBoard.size()-1; i++) {
            String s = Integer.toString(i);
            BufferedImage image = null;
            try {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/" + s + ".jpg")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert image != null;
            int imageWidth = (int) (image.getWidth() * 0.15);
            int imageHeight = (int) (image.getHeight() * 0.15);
            Image scaledImage = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageSquareList.add(imageLabel);
        }
        return imageSquareList;
    }

    public class CustomOutputStream extends OutputStream {
        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
            this.textArea.setEditable(false);
        }

        @Override
        public void write(int b) {
            // redirects data to the text area
            textArea.append(String.valueOf((char)b));
            // scrolls the text area to the end of data
            textArea.setCaretPosition(textArea.getDocument().getLength());
            // keeps the textArea up to date
            textArea.update(textArea.getGraphics());
        }
    }

    private JTextArea printLog() {
        JTextArea textArea = new JTextArea(45, 45);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);
        return textArea;
    }

    public void displayGUI() {
        this.mainFrame.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        this.mainFrame.setPreferredSize(new Dimension(screenWidth,screenHeight));

        // Create JMenuBar
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem loadGameItem = new JMenuItem("Load Game");
        JMenuItem exitGameItem = new JMenuItem("Exit Game");
        fileMenu.add(newGameItem);
        fileMenu.add(loadGameItem);
        fileMenu.add(exitGameItem);

        JMenu settingsMenu = new JMenu("Settings");
        JMenu aboutMenu = new JMenu("About");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);
        menuBar.add(aboutMenu);
        menuBar.add(helpMenu);

        // Create body panel
        JPanel bodyPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        for (int i = 0; i < gameBoard.size(); i++) {
            if (i <= 10) {
                c.gridx = 10-i;
                c.gridy = 10;
                bodyPanel.add(grabImages().get(i), c);
            }
            else if (i <= 20) {
                c.gridx = 0;
                c.gridy = 20-i;
                bodyPanel.add(grabImages().get(i), c);
            }
            else if (i <= 30) {
                c.gridx = i-20;
                c.gridy = 0;
                bodyPanel.add(grabImages().get(i), c);
            }
            else if (i < 40) {
                c.gridx = 10;
                c.gridy = i-30;
                bodyPanel.add(grabImages().get(i), c);
            }
        }

        // Create east panel
        JPanel eastPanel = new JPanel();
        eastPanel.add(new JScrollPane(printLog()));

        // Add sub-panels to main frame
        this.mainFrame.add(bodyPanel, BorderLayout.CENTER);
        this.mainFrame.add(eastPanel, BorderLayout.EAST);


        this.mainFrame.setJMenuBar(menuBar);
        this.mainFrame.pack();

        this.mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    mainFrame.setVisible(false);
                    mainFrame.dispose();
                }
            }
        });
        this.mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        MonopolyFrame frame = new MonopolyFrame();
        frame.displayGUI();
    }
}
