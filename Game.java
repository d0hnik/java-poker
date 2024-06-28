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

        System.out.println("THE GAME IS STARTING....");
        System.out.println("------------------------------------------------------");
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
            table.addCardToTable(deck.drawCard());
        }
    }

    public void playersTurn() {
        for (Player player : players) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("TABLE CARDS ARE: ");
            System.out.println(table.getTableCards());
            System.out.println("------------------------");
            System.out.println("YOUR CARDS ARE");
            System.out.println(player.getHand());
            System.out.println(player.getName() + " what do you want to do? (raise, call, fold) ?");
            String userOutput = scanner.nextLine();
        }
    }

    public void flipOneMoreCard() {
        // BURNER CARD
        deck.drawCard();
        table.addCardToTable(deck.drawCard());
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
        game.givePlayersHands();
        game.flop();
        game.playersTurn();
        game.flipOneMoreCard();
        game.playersTurn();
        game.flipOneMoreCard();
        game.playersTurn();
    }
}
