package Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    public List<Card> CardDeck;

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
        Card card = CardDeck.getFirst();
        CardDeck.removeFirst();
        return card;
    }

    public int getDeckSize() {
        return CardDeck.size();
    }
}
