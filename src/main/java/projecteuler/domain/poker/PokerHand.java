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

    private Combination combination;

    private Hand hand;

    public PokerHand(Hand hand) {
        if (hand == null) {
            throw new IllegalStateException("Impossible to create PokerHand from null Hand");
        }
        this.hand = hand;
        this.combination = computeCombination(hand);
    }

    public Combination getCombination() {
        return combination;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return combination + COMBINATION_AND_NAME_SEPARATOR + hand;
    }

    private Combination computeCombination(Hand hand) {
        TreeSet<Card> cards = hand.getCards();
        Map<Rank, List<Card>> cardsByRank = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank));
        int numberOfDifferentRanks = cardsByRank.size();

        boolean isFlush = isFlush(cards);
        boolean isStraight = hasStraightGapBetweenHighestAndLowest(cards) && numberOfDifferentRanks == FIVE_DIFFERENT_RANKS;
        if (isFlush) {
            if (isStraight) {
                return Combination.STRAIGHT_FLUSH;
            }
            return Combination.FLUSH;
        }
        if (isStraight) {
            return Combination.STRAIGHT;
        }

        if (numberOfDifferentRanks == FIVE_DIFFERENT_RANKS) {
            return Combination.HIGH_CARD;
        }
        if (numberOfDifferentRanks == FOUR_DIFFERENT_RANKS) {
            return Combination.ONE_PAIR;
        }
        if (numberOfDifferentRanks == THREE_DIFFERENT_RANKS) {
            if (maximumNumberOfCardsOfTheSameRank(cardsByRank) == THREE_CARDS_OF_THE_SAME_RANK) {
                return Combination.THREE_OF_A_KIND;
            }
            return Combination.TWO_PAIRS;
        }
        if (numberOfDifferentRanks == TWO_DIFFERENT_RANKS) {
            if (maximumNumberOfCardsOfTheSameRank(cardsByRank) == THREE_CARDS_OF_THE_SAME_RANK) {
                return Combination.FULL_HOUSE;
            }
            return Combination.FOUR_OF_A_KIND;
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
