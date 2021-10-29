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
        this.model = new Monopoly();
    }

    public boolean displayGUI() {
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(800,800));

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
        return true;
    }

    public static void main(String[] args) {
        MonopolyFrame frame = new MonopolyFrame();
        frame.displayGUI();
    }
}
