package projecteuler.domain.deck;

import java.util.Comparator;
import java.util.Iterator;

public class HandStrengthComparator implements Comparator<Hand> {

    @Override
    public int compare(Hand hand1, Hand hand2) {
        return compareHandsStrength(hand1, hand2);
    }

    public static int compareHandsStrength(Hand hand1, Hand hand2) {
        if (hand1.getCards().size() != hand2.getCards().size()) {
            throw new IllegalArgumentException("Impossible to compare hands of different sizes");
        }
        int result = 0;
        Iterator<Card> iteratorHand1 = hand1.getCards().iterator();
        Iterator<Card> iteratorHand2 = hand2.getCards().iterator();
        while (iteratorHand1.hasNext() && result == 0) {
            result = RankComparator.compareRanks(iteratorHand1.next().rank(), iteratorHand2.next().rank());
        }
        return result;
    }

}
