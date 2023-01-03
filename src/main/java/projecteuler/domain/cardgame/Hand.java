package projecteuler.domain.cardgame;

import java.util.*;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand> {

    private TreeSet<Card> cards;

    public Hand(Set<Card> cards) {
        this.cards = cards.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Card> getCards() {
        return cards;
    }

    @Override
    public int compareTo(Hand other) {
        if (this.cards.size() != other.cards.size()) {
            throw new IllegalArgumentException("Impossible to compare hands of different sizes");
        }
        int result = 0;
        Iterator<Card> iterator = this.cards.iterator();
        Iterator<Card> iteratorOnOther = other.cards.iterator();
        while (iterator.hasNext() && result == 0) {
            result = Integer.compare(iterator.next().getRank().getValue(), iteratorOnOther.next().getRank().getValue());
        }
        return result;
    }
}
