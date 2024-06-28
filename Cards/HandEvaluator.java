package Cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandEvaluator {

    public HandType evaluateHand(Hand hand, List<Card> tableCards) {
        List<Card> allCards = new ArrayList<>(hand.getCards());
        allCards.addAll(tableCards);
        return null;
    }

    private boolean isThreeOfAKind(List<Card> cards) {
        Map<Card.CardValue, Integer> frequencyMap = getFrequencyMap(cards);
        for (int count : frequencyMap.values()) {
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean isOnePair(List<Card> cards) {
        Map<Card.CardValue, Integer> frequencyMap = getFrequencyMap(cards);
        for (int count : frequencyMap.values()) {
            if (count == 2) {
                return true;
            }
        }
        return false;
    }

    private boolean isTwoPair(List<Card> cards) {
        Map<Card.CardValue, Integer> frequencyMap = getFrequencyMap(cards);
        int pairs = 0;
        for (int count : frequencyMap.values()) {
            if (count == 2) {
                pairs++;
            }
        }
        return pairs >= 2;
    }

    private boolean isFourOfAKind(List<Card> cards) {
        Map<Card.CardValue, Integer> frequencyMap = getFrequencyMap(cards);
        for (int count : frequencyMap.values()) {
            if (count == 4) {
                return true;
            }
        }
        return false;
    }








    private Map<Card.CardValue, Integer> getFrequencyMap(List<Card> cards) {
        Map<Card.CardValue, Integer> frequencyMap = new HashMap<>();
        for (Card card : cards) {
            frequencyMap.put(card.getValue(), frequencyMap.getOrDefault(card.getValue(), 0) + 1);
        }
        return frequencyMap;
    }
}
