package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Cards.*;

public class HandEvaluatorTest {

    private final HandEvaluator handEvaluator = new HandEvaluator();

    @Test
    public void testHighCard() {
        Hand hand = new Hand();
        Table table = new Table();

        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        hand.addCardToHand(new Card(Card.CardValue.S4, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S6, Card.CardSuit.S));
        helperMethod(table);

        assertEquals(HandType.HIGH_CARD, handEvaluator.evaluateHand(hand, table));
    }

    private void helperMethod(Table table) {
        table.addCardToTable(new Card(Card.CardValue.S8, Card.CardSuit.D));
        table.addCardToTable(new Card(Card.CardValue.J, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.S));
    }

    @Test
    public void testOnePair() {
        Hand hand = new Hand();
        Table table = new Table();

        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        helperMethod(table);

        assertEquals(HandType.ONE_PAIR, handEvaluator.evaluateHand(hand, table));
    }

    @Test
    public void testTwoPair() {
        Hand hand = new Hand();
        Table table = new Table();

        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.D));
        table.addCardToTable(new Card(Card.CardValue.J, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.S));

        assertEquals(HandType.TWO_PAIRS, handEvaluator.evaluateHand(hand, table));
    }

    @Test
    public void testTrips() {
        Hand hand = new Hand();
        Table table = new Table();

        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.S));
        helperMethod(table);

        assertEquals(HandType.TRIPS, handEvaluator.evaluateHand(hand, table));
    }

    @Test
    public void testStraight() {
        Hand hand = new Hand();
        Table table = new Table();

        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        hand.addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        table.addCardToTable(new Card(Card.CardValue.S5, Card.CardSuit.D));
        table.addCardToTable(new Card(Card.CardValue.S6, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.S));

        assertEquals(HandType.STRAIGHT, handEvaluator.evaluateHand(hand, table));
    }

    @Test
    public void testFlush() {
        Hand hand = new Hand();
        Table table = new Table();

        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        hand.addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.S6, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.J, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.S));

        assertEquals(HandType.FLUSH, handEvaluator.evaluateHand(hand, table));
    }

    @Test
    public void testFullHouse() {
        Hand hand = new Hand();
        Table table = new Table();

        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.S));
        table.addCardToTable(new Card(Card.CardValue.S5, Card.CardSuit.D));
        table.addCardToTable(new Card(Card.CardValue.S5, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.S));

        assertEquals(HandType.FULL_HOUSE, handEvaluator.evaluateHand(hand, table));
    }

    @Test
    public void testFourOfAKind() {
        Hand hand = new Hand();
        Table table = new Table();

        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.S));
        table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.D));
        table.addCardToTable(new Card(Card.CardValue.J, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.S));

        assertEquals(HandType.FOUR_OF_A_KIND, handEvaluator.evaluateHand(hand, table));
    }

    @Test
    public void testStraightFlush() {
        Hand hand = new Hand();
        Table table = new Table();

        hand.addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        hand.addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.S5, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.S6, Card.CardSuit.H));
        table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.C));
        table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.S));

        assertEquals(HandType.STRAIGHT_FLUSH, handEvaluator.evaluateHand(hand, table));
    }

    @Test
    public void testTwoHighCards() {

    }
}