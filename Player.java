import Cards.Hand;
import Cards.Deck;
import Cards.Card;
import Cards.Hand;

public class Player {

    public String name;
    public int chips;
    public Hand hand;
    public int currentBet = 0;

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
        throw new RuntimeException();
    }

    public void raise() {
        throw new RuntimeException();
    }

    public void call() {
        throw new RuntimeException();
    }

    public String getName() {
        return this.name;
    }
}
