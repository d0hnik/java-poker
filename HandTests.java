import Cards.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HandTests {
    @Test
    public void handKnowsWhetherItContainsOnePair() {
        Hand hand = new Hand();
        hand.addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.D));
        hand.addCardToHand(new Card(Card.CardValue.S4, Card.CardSuit.D));
        Table table = new Table();
        table.addCardToTable(new Card(Card.CardValue.S3, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S5, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S6, Card.CardSuit.C));

        HandEvaluator handEvaluator = new HandEvaluator();
        HandType handType = handEvaluator.evaluateHand(hand, table);

        assertThat(handType, is(HandType.ONE_PAIR));
    }

    @Test
    public void handKnowsWhetherItContainsTwoPairs() {
        Hand hand = new Hand();
        hand.addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.D));
        hand.addCardToHand(new Card(Card.CardValue.S4, Card.CardSuit.D));
        Table table = new Table();
        table.addCardToTable(new Card(Card.CardValue.S3, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S6, Card.CardSuit.C));

        HandEvaluator handEvaluator = new HandEvaluator();
        HandType handType = handEvaluator.evaluateHand(hand, table);

        assertThat(handType, is(HandType.TWO_PAIRS));
    }

    @Test
    public void handKnowsWhetherItContainsStraight() {
        Hand hand = new Hand();
        hand.addCardToHand(new Card(Card.CardValue.K, Card.CardSuit.D));
        hand.addCardToHand(new Card(Card.CardValue.Q, Card.CardSuit.D));
        Table table = new Table();
        table.addCardToTable(new Card(Card.CardValue.J, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S10, Card.CardSuit.C));

        HandEvaluator handEvaluator = new HandEvaluator();
        HandType handType = handEvaluator.evaluateHand(hand, table);
        assertThat(handType, is(HandType.STRAIGHT));
    }

    @Test
    public void handKnowsWhetherItContainsStraightFlush() {
        Hand hand = new Hand();
        hand.addCardToHand(new Card(Card.CardValue.A, Card.CardSuit.C));
        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.C));
        Table table = new Table();
        table.addCardToTable(new Card(Card.CardValue.S3, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S5, Card.CardSuit.C));

        HandEvaluator handEvaluator = new HandEvaluator();
        HandType handType = handEvaluator.evaluateHand(hand, table);
        assertThat(handType, is(HandType.STRAIGHT_FLUSH));
    }

    @Test
    public void handKnowsWhetherItContainsFullHouse() {
        Hand hand = new Hand();
        hand.addCardToHand(new Card(Card.CardValue.A, Card.CardSuit.C));
        hand.addCardToHand(new Card(Card.CardValue.A, Card.CardSuit.D));
        Table table = new Table();
        table.addCardToTable(new Card(Card.CardValue.S3, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S3, Card.CardSuit.D));
        table.addCardToTable(new Card(Card.CardValue.S3, Card.CardSuit.H));

        HandEvaluator handEvaluator = new HandEvaluator();
        HandType handType = handEvaluator.evaluateHand(hand, table);
        assertThat(handType, is(HandType.FULL_HOUSE));
    }
}