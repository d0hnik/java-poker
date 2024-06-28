package Cards;

import java.util.*;

public class HandEvaluator {

    public HandType evaluateHand(Hand hand, Table table) {
        List<Card> allCards = new ArrayList<>(hand.getCards());
        allCards.addAll(table.getTableCards());

        if (isStraightFlush(allCards)) return HandType.STRAIGHT_FLUSH;
        if (isFourOfAKind(allCards)) return HandType.FOUR_OF_A_KIND;
        if (isFullHouse(allCards)) return HandType.FULL_HOUSE;
        if (isFlush(allCards)) return HandType.FLUSH;
        if (isStraight(allCards)) return HandType.STRAIGHT;
        if (isThreeOfAKind(allCards)) return HandType.TRIPS;
        if (isTwoPair(allCards)) return HandType.TWO_PAIRS;
        if (isOnePair(allCards)) return HandType.ONE_PAIR;

        return HandType.HIGH_CARD; // If there is no combinations, then return high card
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

    private boolean isFullHouse(List<Card> cards) {
        return isThreeOfAKind(cards) && isOnePair(cards);
    }

    private boolean isFlush(List<Card> cards) {
        if (cards.size() < 4) {
            return false;
        }
        else {
            Set<String> handSuits = new HashSet<>();
            for (Card card : cards) {
                handSuits.add(String.valueOf(card.getSuit()));
            }
            return handSuits.size() == 1;
        }
    }

    private boolean isStraight(List<Card> cards) {
        Set<Integer> uniqueValues = new HashSet<>();
        for (Card card : cards) {
            uniqueValues.add(card.getOrdinal());
        }
        List<Integer> sortedValues = new ArrayList<>(uniqueValues);
        Collections.sort(sortedValues);
        int consecutiveCount = 1;
        for (int i = 1; i < sortedValues.size(); i++) {
            if (sortedValues.get(i) - sortedValues.get(i - 1) == 1) {
                consecutiveCount++;
                if (consecutiveCount == 5) {
                    return true;
                }
            } else {
                consecutiveCount = 1;
            }
        }
        return false;
    }

    private boolean isStraightFlush(List<Card> cards) {
        return isStraight(cards) && isFlush(cards);
    }

    private Map<Card.CardValue, Integer> getFrequencyMap(List<Card> cards) {
        Map<Card.CardValue, Integer> frequencyMap = new HashMap<>();
        for (Card card : cards) {
            frequencyMap.put(card.getValue(), frequencyMap.getOrDefault(card.getValue(), 0) + 1);
        }
        return frequencyMap;
    }
}
