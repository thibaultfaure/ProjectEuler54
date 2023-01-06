package projecteuler.domain.poker;

import projecteuler.domain.deck.Hand;
import projecteuler.domain.deck.HandStrengthComparator;

public class Flush implements Combination {

    private Hand hand;

    public Flush(Hand hand) {
        this.hand = hand;
    }

    @Override
    public int compareTo(Combination other) {
        if (other instanceof StraightFlush || other instanceof FourOfAKind || other instanceof FullHouse) {
            return -1;
        } else if (other instanceof Flush flush) {
            return HandStrengthComparator.compareHandsStrength(hand, flush.hand);
        }
        return 1;
    }

    @Override
    public String toString() {
        return hand.getCards().first().rank() + "-high Flush";
    }

}
