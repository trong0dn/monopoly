package monopoly;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
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
    private Monopoly model;
    private GameBoard gameBoard = new GameBoard();

    public MonopolyFrame() {
        super();
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

    public JPanel playerToken(int playerNumber, Color color) {
        JPanel playerToken = new JPanel();
        playerToken.setBackground(color);
        JLabel numLabel = new JLabel(Integer.toString(playerNumber));
        numLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        numLabel.setForeground(Color.WHITE);
        playerToken.add(numLabel);
        playerToken.setBounds(playerNumber * 30, 30, 20, 30);
        return playerToken;
    }

    public class createDie extends JPanel {
        RollDice rollDice = new RollDice();
        int faceValue = rollDice.rollDice().dieValue1;
        public createDie(int x, int y, int width, int height) {
            setBorder(new LineBorder(Color.BLACK));
            setBounds(x, y, width, height);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int w = 5;
            int h = 5;

            if(faceValue == 1) {
                g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, w, h);
            } else if(faceValue == 2) {
                g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, w, h);
                g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15,  w, h);
            } else if(faceValue == 3) {
                g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10,  w, h);
                g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15,  w, h);
                g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2,  w, h);
            } else if(faceValue == 4) {
                g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10,  w, h);
                g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15,  w, h);
                g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15,  w, h);
                g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10,  w, h);
            } else if(faceValue == 5) {
                g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10,  w, h);
                g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15,  w, h);
                g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, w, h);
                g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10,  w, h);
                g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, w, h);
            } else {
                g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10,  w, h);
                g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15,  w, h);
                g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15,  w, h);
                g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10,  w, h);
                g.fillOval(getWidth()/2 - 15, getHeight()/2 - 5/2,  w, h);
                g.fillOval(getWidth()/2 + 10, getHeight()/2 - 5/2,  w, h);
            }
        }

        public void setRollDice() {
            faceValue = rollDice.rollDice().dieValue1;
            repaint();
        }
    }


    public JPanel createBoard() {
        JPanel boardPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        for (int i = 0; i < gameBoard.size(); i++) {
            if (i <= 10) {
                c.gridx = 10-i;
                c.gridy = 10;
                boardPanel.add(grabImages().get(i), c);
            }
            else if (i <= 20) {
                c.gridx = 0;
                c.gridy = 20-i;
                boardPanel.add(grabImages().get(i), c);
            }
            else if (i <= 30) {
                c.gridx = i-20;
                c.gridy = 0;
                boardPanel.add(grabImages().get(i), c);
            }
            else if (i < 40) {
                c.gridx = 10;
                c.gridy = i-30;
                boardPanel.add(grabImages().get(i), c);
            }
        }
        return boardPanel;
    }

    public void displayGUI() {
        this.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));

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

        JPanel bodyPanel = new JPanel();
        JLayeredPane layeredPane = new JLayeredPane();

        bodyPanel.add(layeredPane);

        // Create body panel
        JPanel board = createBoard();

        layeredPane.add(board, String.valueOf(0));

        // Create east panel
        JPanel eastPanel = new JPanel();
        eastPanel.add(new JScrollPane(printLog()));

        // Add sub-panels to main frame
        this.add(board, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);


        this.setJMenuBar(menuBar);
        this.pack();


        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(MonopolyFrame.this, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    setVisible(false);
                    dispose();
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MonopolyFrame frame = new MonopolyFrame();
        frame.displayGUI();
    }
}
