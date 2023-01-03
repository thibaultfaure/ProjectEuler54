package projecteuler.domain.cardgame;

import java.util.Comparator;

public class CardStrengthComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        return compareCardsStrength(card1, card2);
    }

    public static int compareCardsStrength(Card card1, Card card2) {
        int rankComparison = Integer.compare(card1.getRank().getValue(), card2.getRank().getValue());
        if (rankComparison != 0) {
            return rankComparison;
        }
        return 1; // if rank value is the same, we don't care how suits are ordered
    }

}
