package projecteuler.domain.poker;

import projecteuler.domain.cardgame.Card;
import projecteuler.domain.cardgame.Hand;
import projecteuler.domain.cardgame.Rank;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHand {

    private static final String COMBINATION_AND_NAME_SEPARATOR = " ";
    private static final int FOUR_DIFFERENT_RANKS = 4;
    private static final int FIVE_DIFFERENT_RANKS = 5;
    private static final int ONE_UNIQUE_FLUSH = 1;
    private static final int STRAIGHT_GAP_BETWEEN_HIGHEST_AND_LOWEST_VALUE = 4;
    private static final int THREE_CARDS_OF_THE_SAME_RANK = 3;
    private static final int THREE_DIFFERENT_RANKS = 3;
    private static final int TWO_DIFFERENT_RANKS = 2;

    private CombinationLegacy combination;

    private Hand hand;

    public PokerHand(Hand hand) {
        if (hand == null) {
            throw new IllegalStateException("Impossible to create PokerHand from null Hand");
        }
        this.hand = hand;
        this.combination = computeCombination(hand);
    }

    public CombinationLegacy getCombination() {
        return combination;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return combination + COMBINATION_AND_NAME_SEPARATOR + hand;
    }

    private CombinationLegacy computeCombination(Hand hand) {
        TreeSet<Card> cards = hand.getCards();
        Map<Rank, List<Card>> cardsByRank = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank));
        int numberOfDifferentRanks = cardsByRank.size();

        boolean isFlush = isFlush(cards);
        boolean isStraight = hasStraightGapBetweenHighestAndLowest(cards) && numberOfDifferentRanks == FIVE_DIFFERENT_RANKS;
        if (isFlush) {
            if (isStraight) {
                return CombinationLegacy.STRAIGHT_FLUSH;
            }
            return CombinationLegacy.FLUSH;
        }
        if (isStraight) {
            return CombinationLegacy.STRAIGHT;
        }

        if (numberOfDifferentRanks == FIVE_DIFFERENT_RANKS) {
            return CombinationLegacy.HIGH_CARD;
        }
        if (numberOfDifferentRanks == FOUR_DIFFERENT_RANKS) {
            return CombinationLegacy.ONE_PAIR;
        }
        if (numberOfDifferentRanks == THREE_DIFFERENT_RANKS) {
            if (maximumNumberOfCardsOfTheSameRank(cardsByRank) == THREE_CARDS_OF_THE_SAME_RANK) {
                return CombinationLegacy.THREE_OF_A_KIND;
            }
            return CombinationLegacy.TWO_PAIRS;
        }
        if (numberOfDifferentRanks == TWO_DIFFERENT_RANKS) {
            if (maximumNumberOfCardsOfTheSameRank(cardsByRank) == THREE_CARDS_OF_THE_SAME_RANK) {
                return CombinationLegacy.FULL_HOUSE;
            }
            return CombinationLegacy.FOUR_OF_A_KIND;
        }
        throw new IllegalArgumentException("Impossible to build Poker Hand because this Hand is not a valid poker hand");
    }

    private boolean isFlush(Set<Card> cards) {
        return cards.stream()
                .map(Card::getSuit)
                .distinct().count() == ONE_UNIQUE_FLUSH;
    }

    private boolean hasStraightGapBetweenHighestAndLowest(TreeSet<Card> cards) {
        return cards.first().getRank().getValue() - cards.last().getRank().getValue() == STRAIGHT_GAP_BETWEEN_HIGHEST_AND_LOWEST_VALUE;
    }

    private int maximumNumberOfCardsOfTheSameRank(Map<Rank, List<Card>> cardsByRank) {
        return cardsByRank.values().stream().mapToInt(List::size).max().orElseThrow();
    }

}
