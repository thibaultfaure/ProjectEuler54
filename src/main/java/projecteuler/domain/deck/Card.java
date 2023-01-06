package projecteuler.domain.deck;

import java.util.Objects;

public record Card(Rank rank, Suit suit) {

    private static final String RANK_AND_SUIT_SEPARATOR = " of ";

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Card card)) {
            return false;
        }
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public String toString() {
        return rank + RANK_AND_SUIT_SEPARATOR + suit;
    }

}
