package projecteuler.domain.poker;

import org.junit.jupiter.api.Test;
import projecteuler.domain.cardgame.Card;
import projecteuler.domain.cardgame.Hand;
import projecteuler.domain.cardgame.Rank;
import projecteuler.domain.cardgame.Suit;

import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokerHandTest {

    @Test
    void givenAStraightFlushHand_WhenConstructor_ThenCombinationIsStraightFlush() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.KING, Suit.SPADES), new Card(Rank.QUEEN, Suit.SPADES), new Card(Rank.JACK, Suit.SPADES), new Card(Rank.TEN, Suit.SPADES));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        assertEquals(Combination.STRAIGHT_FLUSH, getCombination(pokerHand));
    }

    @Test
    void givenAFourOfAKindHand_WhenConstructor_ThenCombinationIsFourOfAKind() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.ACE, Suit.HEARTS), new Card(Rank.SEVEN, Suit.HEARTS));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        assertEquals(Combination.FOUR_OF_A_KIND, getCombination(pokerHand));
    }

    @Test
    void givenAFullHouseHand_WhenConstructor_ThenCombinationIsFullHouse() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.ACE, Suit.HEARTS), new Card(Rank.EIGHT, Suit.HEARTS));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        assertEquals(Combination.FULL_HOUSE, getCombination(pokerHand));
    }

    @Test
    void givenAFlushHand_WhenConstructor_ThenCombinationIsFlush() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.THREE, Suit.SPADES), new Card(Rank.KING, Suit.SPADES), new Card(Rank.NINE, Suit.SPADES), new Card(Rank.SIX, Suit.SPADES), new Card(Rank.FOUR, Suit.SPADES));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        assertEquals(Combination.FLUSH, getCombination(pokerHand));
    }

    @Test
    void givenAStraightHand_WhenConstructor_ThenCombinationIsStraight() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.THREE, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.FIVE, Suit.HEARTS), new Card(Rank.SIX, Suit.SPADES), new Card(Rank.SEVEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        assertEquals(Combination.STRAIGHT, getCombination(pokerHand));
    }

    @Test
    void givenAThreeOfAKindHand_WhenConstructor_ThenCombinationIsThreeOfAKind() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.SEVEN, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.SPADES), new Card(Rank.SEVEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        assertEquals(Combination.THREE_OF_A_KIND, getCombination(pokerHand));
    }

    @Test
    void givenATwoPairsHand_WhenConstructor_ThenCombinationIsTwoPairs() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.SEVEN, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.SPADES), new Card(Rank.FOUR, Suit.CLUBS));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        assertEquals(Combination.TWO_PAIRS, getCombination(pokerHand));
    }

    @Test
    void givenAOnePairHand_WhenConstructor_ThenCombinationIsOnePair() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.QUEEN, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.QUEEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        assertEquals(Combination.ONE_PAIR, getCombination(pokerHand));
    }

    @Test
    void givenAHighCardHand_WhenConstructor_ThenCombinationIsHighCard() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.JACK, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.QUEEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        assertEquals(Combination.HIGH_CARD, getCombination(pokerHand));
    }

    @Test
    void givenAStraightFlushAndAHighCardHand_WhenCompareTo_ThenGreaterThan() {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.KING, Suit.SPADES), new Card(Rank.QUEEN, Suit.SPADES), new Card(Rank.JACK, Suit.SPADES), new Card(Rank.TEN, Suit.SPADES));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        Set<Card> otherCards = Set.of(new Card(Rank.JACK, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.QUEEN, Suit.DIAMONDS));
        Hand otherHand = new Hand(otherCards);
        PokerHand otherPokerHand = new PokerHand(otherHand);

        assertTrue(pokerHand.compareTo(otherPokerHand) > 0);
        assertTrue(otherPokerHand.compareTo(pokerHand) < 0);
    }

    @Test
    void givenAFourOfAKindAndAHighCardHand_WhenCompareTo_ThenGreaterThan() {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.ACE, Suit.HEARTS), new Card(Rank.SEVEN, Suit.HEARTS));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        Set<Card> otherCards = Set.of(new Card(Rank.JACK, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.QUEEN, Suit.DIAMONDS));
        Hand otherHand = new Hand(otherCards);
        PokerHand otherPokerHand = new PokerHand(otherHand);

        assertTrue(pokerHand.compareTo(otherPokerHand) > 0);
        assertTrue(otherPokerHand.compareTo(pokerHand) < 0);
    }

    @Test
    void givenAFullHouseAndAHighCardHand_WhenCompareTo_ThenGreaterThan() {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.ACE, Suit.HEARTS), new Card(Rank.EIGHT, Suit.HEARTS));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        Set<Card> otherCards = Set.of(new Card(Rank.JACK, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.QUEEN, Suit.DIAMONDS));
        Hand otherHand = new Hand(otherCards);
        PokerHand otherPokerHand = new PokerHand(otherHand);

        assertTrue(pokerHand.compareTo(otherPokerHand) > 0);
        assertTrue(otherPokerHand.compareTo(pokerHand) < 0);
    }

    // This is just a demonstration project, let's allow us to not be exhaustive8

    @Test
    void givenAStraightFlushAceHighAndAStraightFlushJackHigh_WhenCompareTo_ThenGreaterThan() {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.KING, Suit.SPADES), new Card(Rank.QUEEN, Suit.SPADES), new Card(Rank.JACK, Suit.SPADES), new Card(Rank.TEN, Suit.SPADES));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        Set<Card> otherCards = Set.of(new Card(Rank.JACK, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.SEVEN, Suit.CLUBS));
        Hand otherHand = new Hand(otherCards);
        PokerHand otherPokerHand = new PokerHand(otherHand);

        assertTrue(pokerHand.compareTo(otherPokerHand) > 0);
        assertTrue(otherPokerHand.compareTo(pokerHand) < 0);
    }

    @Test
    void givenATwoPairsTenAndSevenKickerThreeAndATwoPairsTenAndSevenKickerThreeHand_WhenCompareTo_ThenEquals() {
        Set<Card> cards = Set.of(new Card(Rank.TEN, Suit.SPADES), new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.SEVEN, Suit.SPADES), new Card(Rank.SEVEN, Suit.CLUBS), new Card(Rank.THREE, Suit.SPADES));
        Hand hand = new Hand(cards);
        PokerHand pokerHand = new PokerHand(hand);

        Set<Card> otherCards = Set.of(new Card(Rank.TEN, Suit.DIAMONDS), new Card(Rank.TEN, Suit.HEARTS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.HEARTS), new Card(Rank.THREE, Suit.CLUBS));
        Hand otherHand = new Hand(otherCards);
        PokerHand otherPokerHand = new PokerHand(otherHand);

        assertEquals(0, pokerHand.compareTo(otherPokerHand));
        assertEquals(0, otherPokerHand.compareTo(pokerHand));
        assertEquals(pokerHand, otherPokerHand);
    }


    private Combination getCombination(PokerHand pokerHand) throws NoSuchFieldException, IllegalAccessException {
        Field combinationField = pokerHand.getClass().getDeclaredField("combination");
        combinationField.setAccessible(true);
        return (Combination) combinationField.get(pokerHand);
    }

}
