import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MonopolyFrame extends JFrame {
    private JFrame mainFrame;
    private Monopoly model;

    public MonopolyFrame() {
        super();
        this.mainFrame = new JFrame("Monopoly!");
        //this.model = new Monopoly();
    }

    public void displayGUI() {
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setPreferredSize(new Dimension(800,800));

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
