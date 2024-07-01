import Cards.Deck;
import Cards.HandEvaluator;
import Cards.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    List<Player> players;
    Deck deck;
    Table table;
    HandEvaluator handEvaluator;
    int totalBets = 0;
    int lastBet = 0;
    int currentBet = 0;
    Player lastRaiser = null;

    public Game() {
        players = new ArrayList<>();
        deck = new Deck();
        table = new Table();
        handEvaluator = new HandEvaluator();
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
        boolean roundCompleted = false;
        while (!roundCompleted) {
            roundCompleted = true;
            for (Player player : players) {
                if (player.isFolded) {
                    continue;
                }
                if (lastRaiser != null && lastRaiser.equals(player)) {
                    break;
                }
                Scanner scanner = new Scanner(System.in);
                String userOutput = getPlayerMove(player);

                if (userOutput.equalsIgnoreCase("fold")) {
                    player.fold();
                }


                else if (userOutput.equalsIgnoreCase("call")) {
                    if (lastBet > player.chips) {
                        System.out.println("You dont have enough money to call!");
                        player.fold();
                        continue;
                    }
                    player.call(lastBet);
                    totalBets += lastBet;
                }


                else if (userOutput.equalsIgnoreCase("raise")) {
                    System.out.println(player.getName() + "How much do you want to raise?");
                    userOutput = scanner.nextLine();
                    int raisedChips = Integer.parseInt(userOutput);
                    if (!player.raise(raisedChips)) {
                        System.out.println("You dont have enough money to raise!");
                        continue;
                    }
                    lastBet += raisedChips;
                    totalBets += raisedChips;
                    lastRaiser = player;
                    roundCompleted = false;
                }
            }
        }
        lastBet = 0;
        lastRaiser = null;
    }

    public String getPlayerMove(Player player) {
        Scanner scanner = new Scanner(System.in);
        HandEvaluator handEvaluator = new HandEvaluator();
        player.bestCurrentCombination = handEvaluator.evaluateHand(player.hand, table);
        System.out.println("TABLE CARDS ARE: ");
        System.out.println(table.getTableCards());
        System.out.println("------------------------");
        System.out.println("YOUR CARDS ARE");
        System.out.println(player.getHand());
        System.out.println(player.getBestCurrentCombination() + " Is your current best combination");
        System.out.println(lastBet + " currently last bet is");
        System.out.println(player.getName() + " what do you want to do? (raise, call, fold) ?");
        return scanner.nextLine();
    }

    public void flipOneMoreCard() {
        deck.drawCard(); // BURNER CARD
        table.addCardToTable(deck.drawCard());
    }

    public void evaluateHands() {
        for (Player player : players) {
            if (player.isFolded) {
                continue;
            }
            player.bestCurrentCombination = handEvaluator.evaluateHand(player.hand, table);
            System.out.println(player.getName() + " has a " + player.getBestCurrentCombination());
        }

        Player winner = getWinner();
        if (winner != null) {
            System.out.println("Winner is " + winner.getName());
        }
        else {
            System.out.println("DRAW");
        }
    }

    private Player getWinner() {
        Player winner = null;

        for (Player player : players) {
            if (!player.isFolded) {
                if (winner == null) {
                    winner = player;
                } else {
                    int comparison = player.getBestCurrentCombination().compareTo(winner.getBestCurrentCombination());
                    if (comparison > 0) {
                        winner = player;
                    }
                    else if (comparison == 0) {
                        winner = null;
                    }
                }
            }
        }
        return winner;
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
        game.evaluateHands(); // Käte hindamine
    }
}
