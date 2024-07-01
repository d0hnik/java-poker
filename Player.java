import Cards.*;
import Cards.Hand;

public class Player {

    public String name;
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

    public String getHand() {
        return hand.toString();
    }

    public void fold() {
        isFolded = true;
    }

    public boolean raise(int raisedChips) {
        if (raisedChips > chips) {
            System.out.println("You do not have enough chips to raise.");
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

    public String getName() {
        return this.name;
    }

    public HandType getBestCurrentCombination() {
        return bestCurrentCombination;
    }
}