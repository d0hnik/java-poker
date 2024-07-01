package Cards;

import java.util.ArrayList;
import java.util.List;

public class Table {

    public List<Card> tableCards;

    public Table() {
        tableCards = new ArrayList<>();
    }

    public void addCardToTable(Card card) {
        tableCards.add(card);
    }

    public List<Card> getTableCards() {
        return tableCards;
    }
}
