package Game;

import Cards.*;
import Cards.Hand;

public class Player {

    private final String name;
    public int chips;
    public Hand hand;
    public int currentBet = 0;
    public boolean isFolded = false;
    public HandType bestCurrentCombination;

    public Player(String name) {
        this.name = name;
        chips = 100;
        hand = new Hand();
    }

    public void addCardToHand(Card card) {
        hand.addCardToHand(card);
    }

    public Hand getHand() {
        return hand;
    }

    public void fold() {
        isFolded = true;
    }

    public boolean raise(int raisedChips) {
        if (raisedChips > chips) {
            System.out.println("You do not have enough chips to raise.");
            return false;
        }
        else if (raisedChips <= 0) {
            System.out.println("Raised amount should be bigger than 0.");
            return false;
        }
        else {
            currentBet += raisedChips;
            chips -= raisedChips;
            return true;
        }
    }

    public boolean call(int amountToCall) {
        if (amountToCall > chips) {
            System.out.println("Not enough chips to call current bet.");
            return false;
        }
        currentBet += amountToCall;
        chips -= amountToCall;
        return true;
    }

    public void placeBlind(int blind) {
        if (blind > chips) {
            System.out.println("You do not have enough chips to call blind. You folded.");
            isFolded = true;
            return;
        }
        chips -= blind;
        currentBet += blind;
    }

    public String getName() {
        return this.name;
    }

    public HandType getBestCurrentCombination() {
        return bestCurrentCombination;
    }

    public void deleteHand() {
        hand = new Hand();
    }
}