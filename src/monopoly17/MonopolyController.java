package monopoly17;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

/**
 * This class represents all the controllers used in the Monopoly GUI
 * @author Elisha Catherasoo & Ibrahim Almalki
 */
public class MonopolyController {
    private final JFrame frame;
    private final JPanel playerInitPanel; // Panel for making the players
    private final JPanel startPanel; // Panel for the main starting page
    private JPanel monopolyPanel; // Panel for the actual Monopoly game
    private final JPanel switchPanels; // Used for switching the panels
    private final LinkedList<Player> playerList;
    private final JButton startButton;
    private final JButton playButton;
    private final JButton addPlayer;
    private final JButton addCPUPlayer;
    private final JTextField playerNameInput;
    private final JPanel playerNameList;
    private final Font playerFont;

    private final Color[] playerTokenColors = {
        Color.RED,
        Color.BLUE,
        Color.GREEN,
        Color.ORANGE,
        Color.YELLOW,
        Color.MAGENTA,
        Color.GRAY,
        Color.PINK
    };

    /**
     * Initialize MonopolyController
     */
    public MonopolyController() {
        this.frame = new JFrame("MONOPOLY"); // used for testing buttons
        this.playerInitPanel = new JPanel(new GridBagLayout());
        this.startPanel = new JPanel(new GridBagLayout());
        this.monopolyPanel = new JPanel();
        this.switchPanels = new JPanel(new CardLayout());
        this.playerList = new LinkedList<>();
        this.startButton = new JButton("Start Game");
        this.playButton = new JButton("Play Game!");
        this.addPlayer = new JButton("Add Player");
        this.addCPUPlayer = new JButton("Add CPU Player");
        this.playerNameInput = new JTextField("");
        this.playerNameList = new JPanel(new GridLayout(0,2));
        this.playerFont = new Font("Lucida Grande", Font.PLAIN, 20);
    }

    /**
     *  Get the panel that switches between the startPanel, playerInitPanel and monopolyPanel
     * @return JPanel
     */
    public JPanel getSwitchPanels() {
        return switchPanels;
    }

    /**
     * Get the list of players.
     * @return      LinkedList<Player>
     */
    public LinkedList<Player> getPlayerList(){
        return playerList;
    }

    /**
     * Set the monopoly panel.
     * @param monopolyPanel JPanel
     */
    public void setMonopolyPanel(JPanel monopolyPanel) {
        this.monopolyPanel = monopolyPanel;
    }

    /**
     * Get the menu item that adds a new player.
     * @return      JMenuItem
     */
    public JButton addPlayerButton() {
        addPlayer.addActionListener(e -> {
            if(playerList.size() < 6 && playerNameInput.getText().matches(".*\\w.*")) {
                // Make the panel to get the username
                Player newPlayer = new HumanPlayer(playerNameInput.getText());
                playerList.add(newPlayer);

                // Add the new player to the player panel
                JLabel playerNumber = new JLabel();
                playerNumber.setFont(playerFont);
                playerNumber.setOpaque(true);
                playerNumber.setBackground(playerTokenColors[playerList.indexOf(newPlayer)]);
                playerNumber.setForeground(Color.WHITE);

                JLabel newPlayerLabel = new JLabel();
                newPlayerLabel.setFont(playerFont);
                newPlayerLabel.setOpaque(true);
                newPlayerLabel.setBackground(playerTokenColors[playerList.indexOf(newPlayer)]);
                newPlayerLabel.setForeground(Color.WHITE);

                playerNumber.setText("Player " + (playerList.indexOf(newPlayer) + 1) + ": ");
                newPlayerLabel.setText(newPlayer.name());

                JPanel tempPanelNumber = new JPanel();
                tempPanelNumber.setPreferredSize(new Dimension(150, 40));
                tempPanelNumber.setBackground(playerTokenColors[playerList.indexOf(newPlayer)]);
                tempPanelNumber.add(playerNumber);

                JPanel tempPanelName = new JPanel();
                tempPanelName.setPreferredSize(new Dimension(250, 40));
                tempPanelName.setBackground(playerTokenColors[playerList.indexOf(newPlayer)]);
                tempPanelName.add(newPlayerLabel);

                // Add player name to panel
                playerNameList.add(tempPanelNumber);
                playerNameList.add(tempPanelName);

                playerNameList.revalidate();
                playerNameList.repaint();

                if (playerList.size() >= 2 && playerList.size() <= 6) {
                    playButton.setEnabled(true);
                }

                // Make text box empty
                playerNameInput.setText("");

            } else if (!playerNameInput.getText().matches(".*\\w.*")) { // if the text box is empty/all whitespace
                JOptionPane.showMessageDialog(playerInitPanel, "Type a name in the text box!");
            }
            else {
                JOptionPane.showMessageDialog(playerInitPanel, "You can't have more than 6 players.\nPress Play Game!");
            }
        });
        return addPlayer;
    }

    public JButton AddCPUPlayer() {
        addCPUPlayer.addActionListener(e -> {
            if (playerList.size() < 6 && playerNameInput.getText().matches(".*\\w.*")) {
                // Make the panel to get the username
                Player newPlayer = new CPUPlayer(playerNameInput.getText());
                playerList.add(newPlayer);

                // Add the new player to the player panel
                JLabel playerNumber = new JLabel();
                playerNumber.setFont(playerFont);
                playerNumber.setOpaque(true);
                playerNumber.setBackground(playerTokenColors[playerList.indexOf(newPlayer)]);
                playerNumber.setForeground(Color.WHITE);

                JLabel newPlayerLabel = new JLabel();
                newPlayerLabel.setFont(playerFont);
                newPlayerLabel.setOpaque(true);
                newPlayerLabel.setBackground(playerTokenColors[playerList.indexOf(newPlayer)]);
                newPlayerLabel.setForeground(Color.WHITE);

                playerNumber.setText("Player " + (playerList.indexOf(newPlayer) + 1) + ": ");
                newPlayerLabel.setText(newPlayer.name());

                JPanel tempPanelNumber = new JPanel();
                tempPanelNumber.setPreferredSize(new Dimension(150, 40));
                tempPanelNumber.setBackground(playerTokenColors[playerList.indexOf(newPlayer)]);
                tempPanelNumber.add(playerNumber);

                JPanel tempPanelName = new JPanel();
                tempPanelName.setPreferredSize(new Dimension(250, 40));
                tempPanelName.setBackground(playerTokenColors[playerList.indexOf(newPlayer)]);
                tempPanelName.add(newPlayerLabel);

                // Add player name to panel
                playerNameList.add(tempPanelNumber);
                playerNameList.add(tempPanelName);

                playerNameList.revalidate();
                playerNameList.repaint();


                if (playerList.size() >= 2 && playerList.size() <= 6) {
                    playButton.setEnabled(true);
                }

                // Make text box empty
                playerNameInput.setText("");

            } else if (!playerNameInput.getText().matches(".*\\w.*")) { // if the text box is empty/all whitespace
                JOptionPane.showMessageDialog(playerInitPanel, "Type a name in the text box!");
            }
            else {
                JOptionPane.showMessageDialog(playerInitPanel, "You can't have more than 6 players.\nPress Play Game!");
            }
        });
        return addCPUPlayer;
    }

    /**
     * This will change to the player initialization panel
     * @return JButton
     */
    public JButton startButton() {
        startButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (switchPanels.getLayout());
            cl.show(switchPanels, "PlayerInitializePanel");
        });
        return startButton;
    }

    /**
     * Play the game after making all the players.
     * @return button
     */
    public JButton playButton() {
        playButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (switchPanels.getLayout());
            cl.show(switchPanels, "MonopolyPanel");
        });
        return playButton;
    }

    /**
     * The Monopoly GUI
     */
    public void displayGUI() {
        // make the main title
        Font font = new Font("Lucida Grande", Font.BOLD, 60);
        JPanel titleBackground = new JPanel();

        // set sizes
        titleBackground.setPreferredSize(new Dimension(450, 90));
        titleBackground.setBackground(Color.RED);

        frame.setBounds(100, 100, 450, 300);
        frame.setSize(1080,710);

        startPanel.setSize(new Dimension(250, 250));
        startPanel.setBackground(new Color(50, 255, 155));
        startPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

        playerInitPanel.setSize(new Dimension(250, 250));
        playerInitPanel.setBackground(new Color(50, 255, 155));
        playerInitPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

        monopolyPanel.setSize(new Dimension(250, 250));
        monopolyPanel.setBackground(Color.white);

        startButton.setPreferredSize(new Dimension(175, 50));

        playerNameList.setPreferredSize(new Dimension(400, 240));
        playerNameList.setBackground(Color.RED);

        playerNameInput.setPreferredSize(new Dimension(175, 50));
        addPlayer.setPreferredSize(new Dimension(175, 50));
        addCPUPlayer.setPreferredSize(new Dimension(175, 50));
        playButton.setPreferredSize(new Dimension(175, 50));
        playButton.setEnabled(false);

        // starting page label
        JLabel title = new JLabel("MONOPOLY!");
        title.setFont(font);
        title.setOpaque(true);
        title.setBackground(Color.RED);
        title.setForeground(Color.WHITE);
        titleBackground.add(title);

        // player initialization label
        JPanel messagePanel = new JPanel();
        JLabel message = new JLabel("Enter Player name in text box then click Add Player (2-6 players)");
        messagePanel.add(message);

        // GridBagConstraints
        GridBagConstraints gbagConstraintsTitle = new GridBagConstraints();
        gbagConstraintsTitle.gridx = 1;
        gbagConstraintsTitle.gridy = 1;
        gbagConstraintsTitle.insets = new Insets(0, 0, 20, 0);

        GridBagConstraints gbagConstraintsStartButton = new GridBagConstraints();
        gbagConstraintsStartButton.gridx = 1;
        gbagConstraintsStartButton.gridy = 2;
        gbagConstraintsStartButton.insets = new Insets(40, 0, 0, 0);

        GridBagConstraints gbagConstraintsPlayerNameList = new GridBagConstraints();
        gbagConstraintsPlayerNameList.gridx = 1;
        gbagConstraintsPlayerNameList.gridy = 1;
        gbagConstraintsPlayerNameList.gridwidth = 2;
        gbagConstraintsPlayerNameList.gridheight = 7;
        gbagConstraintsPlayerNameList.insets = new Insets(0, 0, 10, 0);

        GridBagConstraints gbagConstraintsPlayerNameInput = new GridBagConstraints();
        gbagConstraintsPlayerNameInput.gridx = 1;
        gbagConstraintsPlayerNameInput.gridy = 8;
        gbagConstraintsPlayerNameInput.gridwidth = 2;
        gbagConstraintsPlayerNameInput.insets = new Insets(5, 0, 10, 0);

        GridBagConstraints gbagConstraintsAddPlayerButton = new GridBagConstraints();
        gbagConstraintsAddPlayerButton.gridx = 2;
        gbagConstraintsAddPlayerButton.gridy = 9;
        gbagConstraintsAddPlayerButton.gridwidth = 2;
        gbagConstraintsAddPlayerButton.insets = new Insets(0, 0, 20, 0);

        GridBagConstraints gbagConstraintsAddCPUPlayerButton = new GridBagConstraints();
        gbagConstraintsAddCPUPlayerButton.gridx = 1;
        gbagConstraintsAddCPUPlayerButton.gridy = 9;
        gbagConstraintsAddCPUPlayerButton.gridwidth = 1;
        gbagConstraintsAddCPUPlayerButton.insets = new Insets(0, 15, 20, 0);

        GridBagConstraints gbagConstraintsPlayButton = new GridBagConstraints();
        gbagConstraintsPlayButton.gridx = 1;
        gbagConstraintsPlayButton.gridy = 10;
        gbagConstraintsPlayButton.gridwidth = 2;
        gbagConstraintsPlayButton.insets = new Insets(20, 0, 0, 0);

        GridBagConstraints gbagConstraintsMessage = new GridBagConstraints();
        gbagConstraintsMessage.gridx = 1;
        gbagConstraintsMessage.gridy = 12;
        gbagConstraintsMessage.gridwidth = 2;
        gbagConstraintsMessage.insets = new Insets(40, 0, 0, 0);

        // add the buttons, panels and labels to the frame
        startPanel.add(titleBackground, gbagConstraintsTitle);
        startPanel.add(startButton(), gbagConstraintsStartButton);

        playerInitPanel.add(playerNameList, gbagConstraintsPlayerNameList);
        playerInitPanel.add(playerNameInput, gbagConstraintsPlayerNameInput);
        playerInitPanel.add(addPlayerButton(), gbagConstraintsAddPlayerButton);
        playerInitPanel.add(AddCPUPlayer(), gbagConstraintsAddCPUPlayerButton);
        playerInitPanel.add(playButton(), gbagConstraintsPlayButton);
        playerInitPanel.add(messagePanel, gbagConstraintsMessage);

        getSwitchPanels().add(startPanel, "StartPanel");
        getSwitchPanels().add(playerInitPanel, "PlayerInitializePanel");
        getSwitchPanels().add(monopolyPanel, "MonopolyPanel");

        frame.add(getSwitchPanels());

        // frame doesn't close immediately when trying to quit
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });

        frame.setVisible(true);
    }
}
