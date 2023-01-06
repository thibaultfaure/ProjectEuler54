package projecteuler.domain.poker;

import projecteuler.domain.deck.Rank;
import projecteuler.domain.deck.RankComparator;

public class FourOfAKind implements Combination {

    private Rank rank;

    public FourOfAKind(Rank rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Combination other) {
        if (other instanceof StraightFlush) {
            return -1;
        } else if (other instanceof FourOfAKind fourOfAKind) {
            return RankComparator.compareRanks(rank, fourOfAKind.rank);
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Four of a Kind " + rank;
    }

}
