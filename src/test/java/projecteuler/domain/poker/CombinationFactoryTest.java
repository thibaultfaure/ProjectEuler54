package projecteuler.domain.poker;

import org.junit.jupiter.api.Test;
import projecteuler.domain.deck.Card;
import projecteuler.domain.deck.Hand;
import projecteuler.domain.deck.Rank;
import projecteuler.domain.deck.Suit;

import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombinationFactoryTest {

    private CombinationFactory combinationFactory = new CombinationFactory();

    @Test
    void givenAStraightFlushHand_WhenCreateCombination_ThenCombinationIsStraightFlush() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.KING, Suit.SPADES), new Card(Rank.QUEEN, Suit.SPADES), new Card(Rank.JACK, Suit.SPADES), new Card(Rank.TEN, Suit.SPADES));
        Hand hand = new Hand(cards);
        Combination combination = combinationFactory.createCombination(hand);

        assertTrue(combination instanceof StraightFlush);
        StraightFlush straightFlush = (StraightFlush) combination;
        Field highestRankField = straightFlush.getClass().getSuperclass().getDeclaredField("highestRank");
        highestRankField.setAccessible(true);
        assertEquals(Rank.ACE, highestRankField.get(straightFlush));
    }

    @Test
    void givenAFourOfAKindHand_WhenCreateCombination_ThenCombinationIsFourOfAKind() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.ACE, Suit.HEARTS), new Card(Rank.SEVEN, Suit.HEARTS));
        Hand hand = new Hand(cards);
        Combination combination = combinationFactory.createCombination(hand);

        assertTrue(combination instanceof FourOfAKind);
        FourOfAKind fourOfAKind = (FourOfAKind) combination;
        Field rankField = fourOfAKind.getClass().getDeclaredField("rank");
        rankField.setAccessible(true);
        assertEquals(Rank.ACE, rankField.get(fourOfAKind));
    }

    @Test
    void givenAFullHouseHand_WhenCreateCombination_ThenCombinationIsFullHouse() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.ACE, Suit.HEARTS), new Card(Rank.EIGHT, Suit.HEARTS));
        Hand hand = new Hand(cards);
        Combination combination = combinationFactory.createCombination(hand);

        assertTrue(combination instanceof FullHouse);
        FullHouse fullHouse = (FullHouse) combination;
        Field threeOfAKindField = fullHouse.getClass().getDeclaredField("threeOfAKind");
        threeOfAKindField.setAccessible(true);
        assertEquals(Rank.ACE, threeOfAKindField.get(fullHouse));
        Field pairField = fullHouse.getClass().getDeclaredField("pair");
        pairField.setAccessible(true);
        assertEquals(Rank.EIGHT, pairField.get(fullHouse));
    }

    @Test
    void givenAFlushHand_WhenCreateCombination_ThenCombinationIsFlush() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.THREE, Suit.SPADES), new Card(Rank.KING, Suit.SPADES), new Card(Rank.NINE, Suit.SPADES), new Card(Rank.SIX, Suit.SPADES), new Card(Rank.FOUR, Suit.SPADES));
        Hand hand = new Hand(cards);
        Combination combination = combinationFactory.createCombination(hand);

        assertTrue(combination instanceof Flush);
        Flush flush = (Flush) combination;
        Field handField = flush.getClass().getDeclaredField("hand");
        handField.setAccessible(true);
        Hand resultHand = (Hand) handField.get(flush);
        assertEquals(Rank.KING, resultHand.getCards().pollFirst().rank());
        assertEquals(Rank.NINE, resultHand.getCards().pollFirst().rank());
        assertEquals(Rank.SIX, resultHand.getCards().pollFirst().rank());
        assertEquals(Rank.FOUR, resultHand.getCards().pollFirst().rank());
        assertEquals(Rank.THREE, resultHand.getCards().pollFirst().rank());
    }

    @Test
    void givenAStraightHand_WhenCreateCombination_ThenCombinationIsStraight() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.THREE, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.FIVE, Suit.HEARTS), new Card(Rank.SIX, Suit.SPADES), new Card(Rank.SEVEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        Combination combination = combinationFactory.createCombination(hand);

        assertTrue(combination instanceof Straight);
        Straight straight = (Straight) combination;
        Field highestRankField = straight.getClass().getDeclaredField("highestRank");
        highestRankField.setAccessible(true);
        assertEquals(Rank.SEVEN, highestRankField.get(straight));
    }

    @Test
    void givenAThreeOfAKindHand_WhenCreateCombination_ThenCombinationIsThreeOfAKind() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.SEVEN, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.SPADES), new Card(Rank.SEVEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        Combination combination = combinationFactory.createCombination(hand);

        assertTrue(combination instanceof ThreeOfAKind);
        ThreeOfAKind threeOfAKind = (ThreeOfAKind) combination;
        Field rankField = threeOfAKind.getClass().getDeclaredField("rank");
        rankField.setAccessible(true);
        assertEquals(Rank.SEVEN, rankField.get(threeOfAKind));
    }

    @Test
    void givenATwoPairsHand_WhenCreateCombination_ThenCombinationIsTwoPairs() {
        Set<Card> cards = Set.of(new Card(Rank.SEVEN, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.SPADES), new Card(Rank.FOUR, Suit.CLUBS));
        Hand hand = new Hand(cards);
        Combination combination = combinationFactory.createCombination(hand);

        assertTrue(combination instanceof TwoPairs);
        TwoPairs twoPairs = (TwoPairs) combination;
        assertEquals(Rank.SEVEN, twoPairs.getHighPair());
        assertEquals(Rank.FOUR, twoPairs.getLowPair());
        assertEquals(Rank.EIGHT, twoPairs.getKicker());
    }

    @Test
    void givenAOnePairHand_WhenCreateCombination_ThenCombinationIsOnePair() {
        Set<Card> cards = Set.of(new Card(Rank.QUEEN, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.QUEEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards);
        Combination combination = combinationFactory.createCombination(hand);

        assertTrue(combination instanceof OnePair);
        OnePair onePair = (OnePair) combination;
        assertEquals(Rank.QUEEN, onePair.getPair());
        Hand kickers = onePair.getKickers();
        assertEquals(Rank.EIGHT, kickers.getCards().pollFirst().rank());
        assertEquals(Rank.SEVEN, kickers.getCards().pollFirst().rank());
        assertEquals(Rank.FOUR, kickers.getCards().pollFirst().rank());
    }

    @Test
    void givenAHighCardHand_WhenCreateCombination_ThenCombinationIsHighCard() throws NoSuchFieldException, IllegalAccessException {
        Set<Card> cards = Set.of(new Card(Rank.JACK, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.QUEEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards);
        Combination combination = combinationFactory.createCombination(hand);

        assertTrue(combination instanceof HighCard);
        HighCard highCard = (HighCard) combination;
        Field handField = highCard.getClass().getDeclaredField("hand");
        handField.setAccessible(true);
        Hand resultHand = (Hand) handField.get(highCard);
        assertEquals(Rank.QUEEN, resultHand.getCards().pollFirst().rank());
        assertEquals(Rank.JACK, resultHand.getCards().pollFirst().rank());
        assertEquals(Rank.EIGHT, resultHand.getCards().pollFirst().rank());
        assertEquals(Rank.SEVEN, resultHand.getCards().pollFirst().rank());
        assertEquals(Rank.FOUR, resultHand.getCards().pollFirst().rank());
    }

}
