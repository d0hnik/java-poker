import Cards.Hand;
import Cards.Deck;
import Cards.Card;
import Cards.Hand;

public class Player {

    public String name;
    public int chips;
    public Hand hand;
    public int currentBet = 0;
    public boolean isSmallBlind = false;
    public boolean isBigBlind = false;
    public boolean isFolded = false;

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
            System.out.println("RAISE FAILED");
            return false;
        }
        else {
            System.out.println("RAISE SUCCESS, CHIPS " + chips);
            currentBet += raisedChips;
            chips -= raisedChips;
            System.out.println("NOW CHIPS " + chips);
            return true;
        }
    }

    public boolean call(int amountToCall) {
        if (amountToCall > chips) {
            System.out.println("CALL FAILED");
            return false;
        }
        System.out.println("CALL SUCCESS, CHIPS " + chips);
        currentBet += amountToCall;
        chips -= amountToCall;
        System.out.println("NOW CHIPS " + chips);
        return true;
    }

    public String getName() {
        return this.name;
    }

}
