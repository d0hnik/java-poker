package Cards;
import Game.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandComparator {

    public static int compareHands(Player player1, Player player2, Table table) {
        HandEvaluator handEvaluator = new HandEvaluator();

        HandType hand1Type = handEvaluator.evaluateHand(player1.getHand(), table);
        HandType hand2Type = handEvaluator.evaluateHand(player2.getHand(), table);

        if (hand1Type.compareTo(hand2Type) > 0) {
            return 1;
        } else if (hand1Type.compareTo(hand2Type) < 0) {
            return -1;
        } else {
            List<Card> allCardsPlayer1 = new ArrayList<>(player1.getHand().getCards());
            allCardsPlayer1.addAll(table.getTableCards());

            List<Card> allCardsPlayer2 = new ArrayList<>(player2.getHand().getCards());
            allCardsPlayer2.addAll(table.getTableCards());
            
            allCardsPlayer1.sort(Collections.reverseOrder());
            allCardsPlayer2.sort(Collections.reverseOrder());

            for (int i = 0; i < allCardsPlayer1.size(); i++) {
                int compare = allCardsPlayer1.get(i).compareTo(allCardsPlayer2.get(i));
                if (compare != 0) {
                    return Integer.compare(compare, 0);
                }
            }
        }
        return 0;
    }
}
