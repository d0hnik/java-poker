import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private Game game;
    private JTextArea logTextArea;

    public MainWindow(Game game) {
        this.game = game;
        setTitle("Poker Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        initComponents();
        pack();
        setLocationRelativeTo(null); // Aseta aken ekraani keskele
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Nupp mängu alustamiseks
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> startGame());
        mainPanel.add(startButton, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    private void startGame() {
        game.startGame();
        game.givePlayersHands();
        game.flop();
        game.flipOneMoreCard();
        game.evaluateHands();

        // Näide mängu logi uuendamisest
        logTextArea.append("Game started...\n");
        logTextArea.append("Flop cards: " + game.table.getTableCardsAsString() + "\n");

        // Siin saate lisada rohkem graafilisi komponente ja funktsionaalsust vastavalt vajadusele
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            MainWindow mainWindow = new MainWindow(game);
        });
    }
}
