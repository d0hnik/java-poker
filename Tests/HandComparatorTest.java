package Tests;

import Cards.Card;
import org.junit.jupiter.api.Test;
import Game.Game;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;


// I did not test draw with trips, flush, full house, four of a kind and straight flush, because it is not possible.
public class HandComparatorTest {

    public Game helperMethod() {
        Game game = new Game();
        game.addPlayers("test1");
        game.addPlayers("test2");
        return game;
    }

    @Test
    public void testHighCardButFirstPlayerHasBetterCard() {
        Game game = helperMethod();

        game.players.get(0).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.H));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.D));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.S5, Card.CardSuit.D));

        game.table.addCardToTable(new Card(Card.CardValue.S7, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.D));
        game.table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S10, Card.CardSuit.D));

        game.evaluateHands();
        assertThat(game.getWinner(), is(game.players.get(1)));
    }

    @Test
    public void testHighCardButItsDraw() {
        Game game = helperMethod();

        game.players.get(0).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.H));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.D));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.D));

        game.table.addCardToTable(new Card(Card.CardValue.S7, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.D));
        game.table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S10, Card.CardSuit.D));

        game.evaluateHands();
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void testOnePairButFirstPlayerHasBetterCard() {
        Game game = helperMethod();

        game.players.get(0).addCardToHand(new Card(Card.CardValue.S4, Card.CardSuit.H));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.S5, Card.CardSuit.H));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.D));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.D));

        game.table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.D));
        game.table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S10, Card.CardSuit.D));

        game.evaluateHands();
        assertThat(game.getWinner(), is(game.players.getFirst()));
    }

    @Test
    public void testOnePairButItsDraw() {
        Game game = helperMethod();

        game.players.get(0).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.H));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.D));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.D));

        game.table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.D));
        game.table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S10, Card.CardSuit.D));

        game.evaluateHands();
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void testTwoPairButFirstPlayerHasBetterCard() {
        Game game = helperMethod();

        game.players.get(0).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.S10, Card.CardSuit.H));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.D));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.S4, Card.CardSuit.D));

        game.table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.D));
        game.table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S10, Card.CardSuit.D));

        game.evaluateHands();
        assertThat(game.getWinner(), is(game.players.getFirst()));
    }

    @Test
    public void testTwoPairButItsDraw() {
        Game game = helperMethod();

        game.players.get(0).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.H));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.S4, Card.CardSuit.H));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.D));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.S4, Card.CardSuit.D));

        game.table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.D));
        game.table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S9, Card.CardSuit.D));

        game.evaluateHands();
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void testTripsButFirstPlayerHasBetterCard() {
        Game game = helperMethod();

        game.players.get(0).addCardToHand(new Card(Card.CardValue.S4, Card.CardSuit.H));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.S4, Card.CardSuit.C));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.D));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.S2, Card.CardSuit.S));

        game.table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.D));
        game.table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S10, Card.CardSuit.D));

        game.evaluateHands();
        assertThat(game.getWinner(), is(game.players.getFirst()));
    }

    @Test
    public void testStraightButFirstPlayerHasBetterCard() {
        Game game = helperMethod();

        game.players.get(0).addCardToHand(new Card(Card.CardValue.Q, Card.CardSuit.H));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.J, Card.CardSuit.H));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.S3, Card.CardSuit.D));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.S5, Card.CardSuit.D));

        game.table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.D));
        game.table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S10, Card.CardSuit.D));

        game.evaluateHands();
        assertThat(game.getWinner(), is(game.players.getFirst()));
    }

    @Test
    public void testStraightButItsDraw() {
        Game game = helperMethod();

        game.players.get(0).addCardToHand(new Card(Card.CardValue.Q, Card.CardSuit.H));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.J, Card.CardSuit.H));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.Q, Card.CardSuit.D));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.J, Card.CardSuit.D));

        game.table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.D));
        game.table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S9, Card.CardSuit.D));

        game.evaluateHands();
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void testFlushButFirstPlayerHasBetterCard() {
        Game game = helperMethod();

        game.players.get(0).addCardToHand(new Card(Card.CardValue.A, Card.CardSuit.H));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.S10, Card.CardSuit.H));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.Q, Card.CardSuit.H));

        game.table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.S5, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.S8, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.S4, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S9, Card.CardSuit.H));

        game.evaluateHands();
        assertThat(game.getWinner(), is(game.players.getFirst()));
    }

    @Test
    public void testFullHouseButFirstPlayerHasBetterCard() {
        Game game = helperMethod();


        game.players.get(0).addCardToHand(new Card(Card.CardValue.K, Card.CardSuit.D));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.K, Card.CardSuit.S));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.S9, Card.CardSuit.S));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.S9, Card.CardSuit.H));

        game.table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.A, Card.CardSuit.D));
        game.table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S9, Card.CardSuit.D));

        game.evaluateHands();
        assertThat(game.getWinner(), is(game.players.getFirst()));
    }
    @Test
    public void testFourOfAKindButFirstPlayerHasBetterCard() {
        Game game = helperMethod();


        game.players.get(0).addCardToHand(new Card(Card.CardValue.K, Card.CardSuit.D));
        game.players.get(0).addCardToHand(new Card(Card.CardValue.K, Card.CardSuit.S));

        game.players.get(1).addCardToHand(new Card(Card.CardValue.S9, Card.CardSuit.S));
        game.players.get(1).addCardToHand(new Card(Card.CardValue.S9, Card.CardSuit.H));

        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.K, Card.CardSuit.H));
        game.table.addCardToTable(new Card(Card.CardValue.S9, Card.CardSuit.C));
        game.table.addCardToTable(new Card(Card.CardValue.S2, Card.CardSuit.S));
        game.table.addCardToTable(new Card(Card.CardValue.S9, Card.CardSuit.D));

        game.evaluateHands();
        assertThat(game.getWinner(), is(game.players.getFirst()));
    }
}
