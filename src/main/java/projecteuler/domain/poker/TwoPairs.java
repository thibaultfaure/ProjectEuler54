package projecteuler.domain.poker;

import projecteuler.domain.deck.Rank;
import projecteuler.domain.deck.RankComparator;

import java.util.Comparator;

public class TwoPairs implements Combination {

    private Rank highPair;

    private Rank lowPair;

    private Rank kicker;

    private TwoPairs(TwoPairsBuilder builder) {
        this.highPair = builder.highPair;
        this.lowPair = builder.lowPair;
        this.kicker = builder.kicker;
    }

    public Rank getHighPair() {
        return highPair;
    }

    public Rank getLowPair() {
        return lowPair;
    }

    public Rank getKicker() {
        return kicker;
    }

    public static class TwoPairsBuilder {

        private Rank highPair;

        private Rank lowPair;

        private Rank kicker;

        public TwoPairsBuilder withHighPair(Rank highPair) {
            this.highPair = highPair;
            return this;
        }

        public TwoPairsBuilder withLowPair(Rank lowPair) {
            this.lowPair = lowPair;
            return this;
        }

        public TwoPairsBuilder withKicker(Rank kicker) {
            this.kicker = kicker;
            return this;
        }

        public TwoPairs build() {
            return new TwoPairs(this);
        }

    }

    @Override
    public int compareTo(Combination other) {
        if (other instanceof OnePair || other instanceof HighCard) {
            return 1;
        } else if (other instanceof TwoPairs twoPairs) {
            return Comparator.comparing(TwoPairs::getHighPair, RankComparator::compareRanks)
                    .thenComparing(TwoPairs::getLowPair, RankComparator::compareRanks)
                    .thenComparing(TwoPairs::getKicker, RankComparator::compareRanks)
                    .compare(this, twoPairs);
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Two Pairs " + highPair + " and " + lowPair;
    }

}
