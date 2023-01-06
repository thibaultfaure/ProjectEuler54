package projecteuler.domain.deck;

/**
 * According to Wikipedia
 * A hand is a unit of the game that begins with the dealer shuffling and dealing the cards as described below, and ends with the players scoring and the next dealer being determined.
 * The set of cards that each player receives and holds in his or her hands is also known as that player's hand.
 * So I choose to name this a deal, but it's not 100% accurate
 */
public class Deal {

    private Hand player1Hand;

    private Hand player2Hand;

    private Deal(DealBuilder builder) {
        // NB: here we don't need to check that hands don't overlap
        this.player1Hand = builder.player1Hand;
        this.player2Hand = builder.player2Hand;
    }

    public Hand getPlayer1Hand() {
        return player1Hand;
    }

    public Hand getPlayer2Hand() {
        return player2Hand;
    }

    public static class DealBuilder {

        private Hand player1Hand;

        private Hand player2Hand;

        public DealBuilder withPlayer1Hand(Hand player1Hand) {
            this.player1Hand = player1Hand;
            return this;
        }

        public DealBuilder withPlayer2Hand(Hand player2Hand) {
            this.player2Hand = player2Hand;
            return this;
        }

        public Deal build() {
            return new Deal(this);
        }

    }

}
