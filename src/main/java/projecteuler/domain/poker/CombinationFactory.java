package projecteuler.domain.poker;

import projecteuler.domain.deck.Card;
import projecteuler.domain.deck.Hand;
import projecteuler.domain.deck.Rank;
import projecteuler.domain.deck.RankComparator;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CombinationFactory {

    private static final int FOUR_CARDS_OF_THE_SAME_RANK = 4;
    private static final int FOUR_DIFFERENT_RANKS = 4;
    private static final int FIVE_DIFFERENT_RANKS = 5;
    private static final int ONE_UNIQUE_FLUSH = 1;
    private static final int STRAIGHT_GAP_BETWEEN_HIGHEST_AND_LOWEST_VALUE = 4;
    private static final int THREE_CARDS_OF_THE_SAME_RANK = 3;
    private static final int THREE_DIFFERENT_RANKS = 3;
    private static final int TWO_CARDS_OF_THE_SAME_RANK = 2;
    private static final int TWO_DIFFERENT_RANKS = 2;

    public Combination createCombination(Hand hand) {
        if (hand == null) {
            throw new IllegalStateException("Impossible to create PokerHand from null Hand");
        }
        TreeSet<Card> cards = hand.getCards();
        Map<Rank, List<Card>> cardsByRank = cards.stream()
                .collect(Collectors.groupingBy(Card::rank));
        int numberOfDifferentRanks = cardsByRank.size();

        boolean isFlush = isFlush(cards);
        boolean isStraight = hasStraightGapBetweenHighestAndLowest(cards) && numberOfDifferentRanks == FIVE_DIFFERENT_RANKS;
        if (isFlush) {
            if (isStraight) {
                return new StraightFlush(cards.first().rank());
            }
            return new Flush(hand);
        }
        if (isStraight) {
            return new Straight(cards.first().rank());
        }

        if (numberOfDifferentRanks == FIVE_DIFFERENT_RANKS) {
            return new HighCard(hand);
        }
        if (numberOfDifferentRanks == FOUR_DIFFERENT_RANKS) {
            Rank pair = getRankHavingOccurrences(cardsByRank, TWO_CARDS_OF_THE_SAME_RANK);
            Set<Card> kickers = getCardsHavingRankNotIn(cards, List.of(pair));
            return new OnePair(pair, new Hand(kickers));
        }
        if (numberOfDifferentRanks == THREE_DIFFERENT_RANKS) {
            if (maximumNumberOfCardsOfTheSameRank(cardsByRank) == THREE_CARDS_OF_THE_SAME_RANK) {
                Rank threeOfAKind = getRankHavingOccurrences(cardsByRank, THREE_CARDS_OF_THE_SAME_RANK);
                return new ThreeOfAKind(threeOfAKind);
            }
            List<Rank> pairs = getOrderedRanksHavingOccurrences(cardsByRank, TWO_CARDS_OF_THE_SAME_RANK);
            Card kicker = getCardHavingRankNotIn(cards, pairs);
            return new TwoPairs.TwoPairsBuilder()
                    .withHighPair(pairs.get(0))
                    .withLowPair(pairs.get(1))
                    .withKicker(kicker.rank())
                    .build();
        }
        if (numberOfDifferentRanks == TWO_DIFFERENT_RANKS) {
            if (maximumNumberOfCardsOfTheSameRank(cardsByRank) == THREE_CARDS_OF_THE_SAME_RANK) {
                Rank threeOfAKind = getRankHavingOccurrences(cardsByRank, THREE_CARDS_OF_THE_SAME_RANK);
                Rank pair = getRankHavingOccurrences(cardsByRank, TWO_CARDS_OF_THE_SAME_RANK);
                return new FullHouse.FullHouseBuilder()
                        .withThreeOfAKind(threeOfAKind)
                        .withPair(pair)
                        .build();
            }
            Rank fourOfAKind = getRankHavingOccurrences(cardsByRank, FOUR_CARDS_OF_THE_SAME_RANK);
            return new FourOfAKind(fourOfAKind);
        }
        throw new IllegalArgumentException("Impossible to create the combination because this Hand is not a valid poker hand");
    }

    private boolean isFlush(Set<Card> cards) {
        return cards.stream()
                .map(Card::suit)
                .distinct().count() == ONE_UNIQUE_FLUSH;
    }

    private boolean hasStraightGapBetweenHighestAndLowest(TreeSet<Card> cards) {
        return cards.first().rank().getValue() - cards.last().rank().getValue() == STRAIGHT_GAP_BETWEEN_HIGHEST_AND_LOWEST_VALUE;
    }

    private int maximumNumberOfCardsOfTheSameRank(Map<Rank, List<Card>> cardsByRank) {
        return cardsByRank.values().stream().mapToInt(List::size).max().orElseThrow();
    }

    private List<Rank> getOrderedRanksHavingOccurrences(Map<Rank, List<Card>> cardsByRank, int occurrences) {
        return streamRanksHavingOccurrences(cardsByRank, occurrences)
                .sorted(Comparator.comparing(Function.identity(), RankComparator::compareRanks).reversed())
                .toList();
    }

    private Rank getRankHavingOccurrences(Map<Rank, List<Card>> cardsByRank, int occurrences) {
        return streamRanksHavingOccurrences(cardsByRank, occurrences).findAny().orElseThrow();
    }

    private Stream<Rank> streamRanksHavingOccurrences(Map<Rank, List<Card>> cardsByRank, int occurrences) {
        return cardsByRank.keySet().stream()
                .filter(key -> cardsByRank.get(key).size() == occurrences);
    }

    private Set<Card> getCardsHavingRankNotIn(Set<Card> cards, List<Rank> ranks) {
        return streamCardsHavingRankNotIn(cards, ranks).collect(Collectors.toSet());
    }

    private Card getCardHavingRankNotIn(Set<Card> cards, List<Rank> ranks) {
        return streamCardsHavingRankNotIn(cards, ranks).findAny().orElseThrow();
    }

    private Stream<Card> streamCardsHavingRankNotIn(Set<Card> cards, List<Rank> ranks) {
        return cards.stream()
                .filter(card -> !ranks.contains(card.rank()));
    }

}
