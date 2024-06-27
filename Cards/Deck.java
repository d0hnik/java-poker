package Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    public List<Card> CardDeck;
    public enum CardValue { S2, S3, S4, S5, S6, S7, S8, S9, S10, J, Q, K, A }

    public enum CardSuit { C, D, H, S }

    public Deck() {
        CardDeck = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public void initializeDeck() {
        for (Card.CardSuit suit : Card.CardSuit.values()) {
            for (Card.CardValue value: Card.CardValue.values()) {
                CardDeck.add(new Card(value, suit));
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(CardDeck);
    }

    public Card drawCard() {
        Card card = CardDeck.get(0);
        CardDeck.remove(0);
        return card;
    }
}
