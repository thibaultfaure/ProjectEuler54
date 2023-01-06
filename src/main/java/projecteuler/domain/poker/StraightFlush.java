package projecteuler.domain.poker;

import projecteuler.domain.deck.Rank;
import projecteuler.domain.deck.RankComparator;

public class StraightFlush extends Straight {

    public StraightFlush(Rank highestRank) {
        super(highestRank);
    }

    @Override
    public int compareTo(Combination other) {
        if (!(other instanceof StraightFlush straightFlush)) {
            return 1;
        }
        return RankComparator.compareRanks(this.highestRank, straightFlush.highestRank);
    }

    @Override
    public String toString() {
        return highestRank + "-high Straight Flush";
    }

}
