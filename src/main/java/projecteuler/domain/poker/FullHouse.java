package projecteuler.domain.poker;

import projecteuler.domain.deck.Rank;
import projecteuler.domain.deck.RankComparator;

import java.util.Comparator;

public class FullHouse implements Combination {

    private Rank threeOfAKind;

    private Rank pair;

    private FullHouse (FullHouseBuilder fullHouseBuilder) {
        this.threeOfAKind = fullHouseBuilder.threeOfAKind;
        this.pair = fullHouseBuilder.pair;
    }

    public Rank getThreeOfAKind() {
        return threeOfAKind;
    }

    public Rank getPair() {
        return pair;
    }

    public static class FullHouseBuilder {

        private Rank threeOfAKind;

        private Rank pair;

        public FullHouseBuilder withThreeOfAKind(Rank threeOfAKind) {
            this.threeOfAKind = threeOfAKind;
            return this;
        }

        public FullHouseBuilder withPair(Rank pair) {
            this.pair = pair;
            return this;
        }

        public FullHouse build() {
            return new FullHouse(this);
        }

    }

    @Override
    public int compareTo(Combination other) {
        if (other instanceof StraightFlush || other instanceof FourOfAKind) {
            return -1;
        } else if (other instanceof FullHouse fullHouse) {
            return Comparator.comparing(FullHouse::getThreeOfAKind, RankComparator::compareRanks)
                    .thenComparing(FullHouse::getPair, RankComparator::compareRanks)
                    .compare(this, fullHouse);
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Full House " + threeOfAKind + "over" + pair;
    }

}
