package projecteuler.domain.poker;

import projecteuler.domain.deck.Rank;
import projecteuler.domain.deck.RankComparator;

public class Straight implements Combination {

    protected Rank highestRank;

    public Straight(Rank highestRank) {
        this.highestRank = highestRank;
    }

    @Override
    public int compareTo(Combination other) {
        if (other instanceof StraightFlush || other instanceof FourOfAKind || other instanceof FullHouse || other instanceof Flush) {
            return -1;
        } else if (other instanceof Straight straight) {
            return RankComparator.compareRanks(this.highestRank, straight.highestRank);
        }
        return 1;
    }

    @Override
    public String toString() {
        return highestRank + "-high Straight";
    }

}
