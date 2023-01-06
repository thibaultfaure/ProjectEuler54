package projecteuler.domain.poker;

import org.junit.jupiter.api.Test;
import projecteuler.domain.cardgame.Card;
import projecteuler.domain.cardgame.Hand;
import projecteuler.domain.cardgame.Rank;
import projecteuler.domain.cardgame.Suit;

import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandTest {

    @Test
    void givenAStraightFlushHand_WhenConstructor_ThenCombinationIsStraightFlush() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.KING, Suit.SPADES), new Card(Rank.QUEEN, Suit.SPADES), new Card(Rank.JACK, Suit.SPADES), new Card(Rank.TEN, Suit.SPADES));
        Hand hand = new Hand(cards);
        CombinationFactory pokerHand = new CombinationFactory(hand);

        assertEquals(CombinationLegacy.STRAIGHT_FLUSH, getCombination(pokerHand));
    }

    @Test
    void givenAFourOfAKindHand_WhenConstructor_ThenCombinationIsFourOfAKind() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.ACE, Suit.HEARTS), new Card(Rank.SEVEN, Suit.HEARTS));
        Hand hand = new Hand(cards);
        CombinationFactory pokerHand = new CombinationFactory(hand);

        assertEquals(CombinationLegacy.FOUR_OF_A_KIND, getCombination(pokerHand));
    }

    @Test
    void givenAFullHouseHand_WhenConstructor_ThenCombinationIsFullHouse() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.ACE, Suit.HEARTS), new Card(Rank.EIGHT, Suit.HEARTS));
        Hand hand = new Hand(cards);
        CombinationFactory pokerHand = new CombinationFactory(hand);

        assertEquals(CombinationLegacy.FULL_HOUSE, getCombination(pokerHand));
    }

    @Test
    void givenAFlushHand_WhenConstructor_ThenCombinationIsFlush() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.THREE, Suit.SPADES), new Card(Rank.KING, Suit.SPADES), new Card(Rank.NINE, Suit.SPADES), new Card(Rank.SIX, Suit.SPADES), new Card(Rank.FOUR, Suit.SPADES));
        Hand hand = new Hand(cards);
        CombinationFactory pokerHand = new CombinationFactory(hand);

        assertEquals(CombinationLegacy.FLUSH, getCombination(pokerHand));
    }

    @Test
    void givenAStraightHand_WhenConstructor_ThenCombinationIsStraight() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.THREE, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.FIVE, Suit.HEARTS), new Card(Rank.SIX, Suit.SPADES), new Card(Rank.SEVEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        CombinationFactory pokerHand = new CombinationFactory(hand);

        assertEquals(CombinationLegacy.STRAIGHT, getCombination(pokerHand));
    }

    @Test
    void givenAThreeOfAKindHand_WhenConstructor_ThenCombinationIsThreeOfAKind() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.SEVEN, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.SPADES), new Card(Rank.SEVEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        CombinationFactory pokerHand = new CombinationFactory(hand);

        assertEquals(CombinationLegacy.THREE_OF_A_KIND, getCombination(pokerHand));
    }

    @Test
    void givenATwoPairsHand_WhenConstructor_ThenCombinationIsTwoPairs() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.SEVEN, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.SPADES), new Card(Rank.FOUR, Suit.CLUBS));
        Hand hand = new Hand(cards);
        CombinationFactory pokerHand = new CombinationFactory(hand);

        assertEquals(CombinationLegacy.TWO_PAIRS, getCombination(pokerHand));
    }

    @Test
    void givenAOnePairHand_WhenConstructor_ThenCombinationIsOnePair() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.QUEEN, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.QUEEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards);
        CombinationFactory pokerHand = new CombinationFactory(hand);

        assertEquals(CombinationLegacy.ONE_PAIR, getCombination(pokerHand));
    }

    @Test
    void givenAHighCardHand_WhenConstructor_ThenCombinationIsHighCard() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.JACK, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.QUEEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards);
        CombinationFactory pokerHand = new CombinationFactory(hand);

        assertEquals(CombinationLegacy.HIGH_CARD, getCombination(pokerHand));
    }

    private CombinationLegacy getCombination(CombinationFactory pokerHand) throws NoSuchFieldException, IllegalAccessException {
        Field combinationField = pokerHand.getClass().getDeclaredField("combination");
        combinationField.setAccessible(true);
        return (CombinationLegacy) combinationField.get(pokerHand);
    }

}
