package projecteuler.domain.poker;

import projecteuler.domain.deck.Hand;
import projecteuler.domain.deck.HandStrengthComparator;
import projecteuler.domain.deck.Rank;
import projecteuler.domain.deck.RankComparator;

import java.util.Comparator;

public class OnePair implements Combination {

    private Rank pair;

    private Hand kickers;

    public OnePair(Rank pair, Hand kickers) {
        this.pair = pair;
        this.kickers = kickers;
    }

    public Rank getPair() {
        return pair;
    }

    public Hand getKickers() {
        return kickers;
    }

    @Override
    public int compareTo(Combination other) {
        if (other instanceof HighCard) {
            return 1;
        } else if (other instanceof OnePair onePair) {
            return Comparator.comparing(OnePair::getPair, RankComparator::compareRanks)
                    .thenComparing(OnePair::getKickers, HandStrengthComparator::compareHandsStrength)
                    .compare(this, onePair);
        }
        return -1;
    }

    @Override
    public String toString() {
        return "One Pair " + pair;
    }

}
