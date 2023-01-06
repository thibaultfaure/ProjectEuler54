package projecteuler.domain.poker;

import projecteuler.domain.deck.Rank;
import projecteuler.domain.deck.RankComparator;

public class ThreeOfAKind implements Combination {

    private Rank rank;

    public ThreeOfAKind(Rank rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Combination other) {
        if (other instanceof TwoPairs || other instanceof OnePair || other instanceof HighCard) {
            return 1;
        } else if (other instanceof ThreeOfAKind threeOfAKind) {
            return RankComparator.compareRanks(this.rank, threeOfAKind.rank);
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Three of a Kind " + rank;
    }

}
