package Cards;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCardToHand(Card card) {
        cards.add(card);
    }

    public HandType getBestCombination() {
        throw new RuntimeException();
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
