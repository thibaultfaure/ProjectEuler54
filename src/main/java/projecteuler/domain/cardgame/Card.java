package projecteuler.domain.cardgame;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private Rank rank;

    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Card)) {
            return false;
        }
        Card card = (Card) other;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public int compareTo(Card other) {
        int rankComparison = - Integer.compare(this.getRank().getValue(), other.getRank().getValue());
        if (rankComparison != 0) {
            return rankComparison;
        }
        return 1; // if rank value is the same, we don't care how suits are ordered
    }
}
