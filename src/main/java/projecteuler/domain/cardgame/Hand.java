package projecteuler.domain.cardgame;

import java.util.*;
import java.util.stream.Collectors;

public class Hand {

    private static final CardStrengthComparator cardStrengthComparator = new CardStrengthComparator();

    private TreeSet<Card> cards;

    public Hand(Set<Card> cards) {
        this.cards = cards.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Collections.reverseOrder(CardStrengthComparator::compareCardsStrength))));
    }

    public TreeSet<Card> getCards() {
        return cards;
    }

}
