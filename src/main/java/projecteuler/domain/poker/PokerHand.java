package projecteuler.domain.poker;

import projecteuler.domain.cardgame.Card;
import projecteuler.domain.cardgame.Hand;
import projecteuler.domain.cardgame.Rank;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {

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

    private Combination computeCombination(Hand hand) {
        Map<Rank, List<Card>> cardsByRank = hand.getCards().stream()
                .collect(Collectors.groupingBy(Card::getRank));
        int numberOfDifferentRanks = cardsByRank.size();

        boolean isFlush = isFlush(hand);
        boolean isStraight = isPotentialStraight(hand) && numberOfDifferentRanks == FIVE_DIFFERENT_RANKS;
        if (isFlush) {
            if (isStraight) {
                return Combination.STRAIGHT_FLUSH;
            }
            return Combination.FLUSH;
        } else if (isStraight) {
            return Combination.STRAIGHT;
        }

        if (numberOfDifferentRanks == FIVE_DIFFERENT_RANKS) {
            return Combination.HIGH_CARD;
        } else if (numberOfDifferentRanks == FOUR_DIFFERENT_RANKS) {
            return Combination.ONE_PAIR;
        } else if (numberOfDifferentRanks == THREE_DIFFERENT_RANKS) {
            if (maximumNumberOfCardsOfTheSameRank(cardsByRank) == THREE_CARDS_OF_THE_SAME_RANK) {
                return Combination.THREE_OF_A_KIND;
            }
            return Combination.TWO_PAIRS;
        } else if (numberOfDifferentRanks == TWO_DIFFERENT_RANKS) {
            if (maximumNumberOfCardsOfTheSameRank(cardsByRank) == THREE_CARDS_OF_THE_SAME_RANK) {
                return Combination.FULL_HOUSE;
            }
            return Combination.FOUR_OF_A_KIND;
        }
        throw new IllegalArgumentException("Impossible to build Poker Hand because this Hand is not a valid poker hand");
    }

    private boolean isFlush(Hand hand) {
        return hand.getCards().stream()
                .map(Card::getSuit)
                .distinct().count() == ONE_UNIQUE_FLUSH;
    }

    private boolean isPotentialStraight(Hand hand) {
        return hand.getCards().first().getRank().getValue() - hand.getCards().last().getRank().getValue() == STRAIGHT_GAP_BETWEEN_HIGHEST_AND_LOWEST_VALUE;
    }

    private int maximumNumberOfCardsOfTheSameRank(Map<Rank, List<Card>> cardsByRank) {
        return cardsByRank.values().stream().mapToInt(List::size).max().orElseThrow();
    }

    @Override
    public int compareTo(PokerHand other) {
        int combinationComparison = Integer.compare(this.combination.getValue(), other.combination.getValue());
        if (combinationComparison != 0) {
            return combinationComparison;
        }
        return this.hand.compareTo(other.hand);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PokerHand)) {
            return false;
        }
        PokerHand pokerHand = (PokerHand) other;
        return this.compareTo(pokerHand) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(combination, hand.getCards().first());
    }

}
