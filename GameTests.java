
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTests {
    @Test
    public void checkIfAddingPlayersWorks() {
        Game game = new Game();
        game.addPlayers("test");
        game.addPlayers("test2");
        assertThat(game.players.size(), is(2));
        // Duplicates or player without names should not be added
        game.addPlayers("test");
        game.addPlayers("");
        assertThat(game.players.size(), is(2));
    }
}
