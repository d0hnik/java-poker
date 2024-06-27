import Cards.Deck;
import Cards.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    List<Player> players;
    Deck deck;
    Table table;
    Player currentPlayer;

    public Game() {
        players = new ArrayList<>();
        deck = new Deck();
        table = new Table();
    }

    public void addPlayers(String name) {
        players.add(new Player(name));
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);


        System.out.print("How many players are playing? ");
        int playerCount = scanner.nextInt();
        scanner.nextLine();


        for (int i = 0; i < playerCount; i++) {
            System.out.print("Insert player nr. " + (i + 1) + " name: ");
            String name = scanner.nextLine();
            addPlayers(name);
        }


        System.out.println("Players are added: ");
        for (Player player : players) {
            System.out.println(player.getName());
        }
    }

    public void givePlayersHands() {
        for (Player player : players) {
            player.addCardToHand(deck.drawCard());
            player.addCardToHand(deck.drawCard());
        }
    }

    public void flop() {
        // BURNER CARD
        deck.drawCard();

        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            table.addCardToTable(deck.drawCard());
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
        game.givePlayersHands();
        game.flop();
    }
}
