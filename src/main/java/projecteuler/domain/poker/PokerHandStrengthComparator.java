package projecteuler.domain.poker;

import projecteuler.domain.cardgame.HandStrengthComparator;

import java.util.Comparator;

public class PokerHandStrengthComparator implements Comparator<PokerHand> {

    @Override
    public int compare(PokerHand pokerHand1, PokerHand pokerHand2) {
        return compareStrength(pokerHand1, pokerHand2);
    }

    public static int compareStrength(PokerHand pokerHand1, PokerHand pokerHand2) {
        return Comparator.comparing(PokerHand::getCombination, Comparator.comparing(Combination::getValue))
                .thenComparing(PokerHand::getHand, HandStrengthComparator::compareHandsStrength)
                .compare(pokerHand1, pokerHand2);
    }

}
