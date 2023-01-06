package projecteuler.domain.poker;

import projecteuler.domain.deck.Hand;
import projecteuler.domain.deck.HandStrengthComparator;

public class HighCard implements Combination {

    private Hand hand;

    public HighCard(Hand hand) {
        this.hand = hand;
    }

    @Override
    public int compareTo(Combination other) {
        if (other instanceof HighCard highCard) {
            return HandStrengthComparator.compareHandsStrength(hand, highCard.hand);
        }
        return -1;
    }

    @Override
    public String toString() {
        return hand.getCards().first().rank() + "-high";
    }

}
