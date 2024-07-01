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
    Player lastRaiser = null;

    public Game() {
        players = new ArrayList<>();
        deck = new Deck();
        table = new Table();
        handEvaluator = new HandEvaluator();
    }

    /**
     * Adds players to List.
     * Players name cannot be empty and should be unique.
     */
    public int addPlayers(String name) {
        if (name.isEmpty()) {
            return -1;
        }
        // Check if there is already player with the same name.
        for (Player player : players) {
            if(player.getName().equals(name)) {
                return 1;
            }
        }
        players.add(new Player(name));
        return 0;
    }

    /**
     * Player sets amount of players and sets their names.
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        int playerCount = getPlayerCount(scanner);
        for (int i = 1; i <= playerCount; i++) {
            getValidPlayerName(scanner, i);
        }
    }

    private int getPlayerCount(Scanner scanner) {
        int playerCount;
        while (true) {
            System.out.print("How many players are playing? Enter a number between 2 and 10: ");
            if (scanner.hasNextInt()) {
                playerCount = scanner.nextInt();
                scanner.nextLine();
                if (playerCount >= 2 && playerCount <= 10) {
                    return playerCount;
                } else {
                    System.out.println("Please enter a number between 2 and 10.");
                }
            } else {
                System.out.println("Input should be a number.");
                scanner.next();
            }
        }
    }

    private void getValidPlayerName(Scanner scanner, int playerNumber) {
        while (true) {
            System.out.print("Insert player nr. " + playerNumber + " name: ");
            String name = scanner.nextLine();
            int result = addPlayers(name);
            if (result == 1) {
                System.out.println("A player with this name already exists. Please enter a unique name.");
            } else if (result == 0) {
                return;
            } else {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        }
    }

    /**
     * Gives every player two cards. (Hand)
     */
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
                    if (!player.call(lastBet)) {
                        System.out.println("You dont have enough money to call!");
                        player.fold();
                        continue;
                    }
                    totalBets += lastBet;
                }


                else if (userOutput.equalsIgnoreCase("raise")) {
                    int raisedChips;
                    do {
                        System.out.println(player.getName() + " , how much do you want to raise?");
                        userOutput = scanner.nextLine();
                        raisedChips = Integer.parseInt(userOutput);
                    } while (!player.raise(raisedChips));
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

    /**
     * Shows the player table cards, players cards and players current best combination.
     * Returns player next move. Either call, raise or fold.
     */
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
        String userOutput;

        // If user typed incorrectly, ask again.
        do {
            System.out.println(player.getName() + " what do you want to do? (raise, call, fold) ?");
            userOutput = scanner.nextLine();
        } while (!userOutput.matches("(?i)(raise|call|fold)"));
        return userOutput;
    }


    /**
     * One method for The turn and the river.
     * Burns one card, and adds one card to the table.
     */
    public void flipOneMoreCard() {
        deck.drawCard(); // BURNER CARD
        table.addCardToTable(deck.drawCard());
    }


    /**
     * Evaluate each player's hand, determine the best combination, and handle payouts accordingly.
     * Prints each player's best current combination to console.
     */
    public void evaluateHands() {
        for (Player player : players) {
            if (player.isFolded) {
                continue;
            }
            // Evaluate and set the best combination for the players hand.
            player.bestCurrentCombination = handEvaluator.evaluateHand(player.hand, table);
            System.out.println(player.getName() + " has a " + player.getBestCurrentCombination());
        }

        // Determine the winner using getWinner() method.
        Player winner = getWinner();
        if (winner != null) {
            System.out.println("Winner is " + winner.getName());
            giveWinnerAllBets(winner);
        }
        else {
            System.out.println("DRAW");
            givePlayersMoneyBackIfDraw();
            cleanPlayersCurrentBets();
        }
    }


    /**
     * Determine who won the game.
     * Return the winner as Player.
     */
    private Player getWinner() {
        Player winner = null;

        for (Player player : players) {
            if (!player.isFolded) {
                // In case it is the first player, set him as winner.
                if (winner == null) {
                    winner = player;
                } else {
                    // Compare winners hand to current players hand.
                    int comparison = player.getBestCurrentCombination().compareTo(winner.getBestCurrentCombination());
                    // If current players hand is better, set him as winner.
                    if (comparison > 0) {
                        winner = player;
                    }

                    // TODO: FIX THIS. FOR EXAMPLE IF THERE IS 3 PLAYERS, FIRST TWO HAVE SAME HAND, THEN THIRD INSTANTLY BECOMES WINNER.
                    // TODO: ALSO ADD HAND COMPARISON.
                    else if (comparison == 0) {
                        winner = null;
                    }
                }
            }
        }
        return winner;
    }

    /**
     * If player won the game, add the chips to players account.
     */
    private void giveWinnerAllBets(Player winner) {
        winner.chips += totalBets;
    }

    /**
     * In case of draw, return player their money.
     */
    private void givePlayersMoneyBackIfDraw() {
        for (Player player : players) {
            if (!player.isFolded) {
                player.chips += player.currentBet;
            }
        }
    }

    /**
     * Clean all players current bets after the round has ended.
     */
    private void cleanPlayersCurrentBets() {
        for (Player player : players) {
            player.currentBet = 0;
        }
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
