package projecteuler.domain.poker;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import projecteuler.domain.cardgame.Card;
import projecteuler.domain.cardgame.Hand;
import projecteuler.domain.cardgame.Rank;
import projecteuler.domain.cardgame.Suit;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokerHandStrenghtComparatorTest {

    // NB: Ties are not considered in the problem so let's not test those cases
    @ParameterizedTest
    @CsvSource({"Straight_Flush_Ace,Straight_Flush_Jack,1",
                "Straight_Flush_Jack,Straight_Flush_Ace,-1",
                "Straight_Flush_Ace,Four_Of_A_Kind_Six,1",
                "Four_Of_A_Kind_Six,Straight_Flush_Ace,-1",
                "Straight_Flush_Ace,Full_House_Sevens_With_Fives,1",
                "Full_House_Sevens_With_Fives,Straight_Flush_Ace,-1",
                "Straight_Flush_Ace,Flush_Eight_High_Diamonds,1",
                "Flush_Eight_High_Diamonds,Straight_Flush_Ace,-1",
                "Straight_Flush_Ace,Straight_Queen_High,1",
                "Straight_Queen_High,Straight_Flush_Ace,-1",
                "Straight_Flush_Ace,Three_Of_A_Kind_Two,1",
                "Three_Of_A_Kind_Two,Straight_Flush_Ace,-1",
                "Straight_Flush_Ace,Two_Pairs_Ten_And_Seven,1",
                "Two_Pairs_Ten_And_Seven,Straight_Flush_Ace,-1",
                "Straight_Flush_Ace,One_Pair_Six,1",
                "One_Pair_Six,Straight_Flush_Ace,-1",
                "Straight_Flush_Ace,High_Card_Ace,1",
                "High_Card_Ace,Straight_Flush_Ace,-1",
                "Four_Of_A_Kind_Ten,Four_Of_A_Kind_Six,1",
                "Four_Of_A_Kind_Six,Four_Of_A_Kind_Ten,-1",
                "Four_Of_A_Kind_Six,Four_Of_A_Kind_FiveKickerAce,1", // pb here
                "Four_Of_A_Kind_FiveKickerAce,Four_Of_A_Kind_Six,-1",
                "Four_Of_A_Kind_Six,Full_House_Sevens_With_Fives,1",
                "Full_House_Sevens_With_Fives,Four_Of_A_Kind_Six,-1",
                "Four_Of_A_Kind_Six,Flush_QJ852_Clubs,1",
                "Flush_QJ852_Clubs,Four_Of_A_Kind_Six,-1",
                "Four_Of_A_Kind_Six,Straight_Queen_High,1",
                "Straight_Queen_High,Four_Of_A_Kind_Six,-1",
                "Four_Of_A_Kind_Six,Three_Of_A_Kind_Two,1",
                "Three_Of_A_Kind_Two,Four_Of_A_Kind_Six,-1",
                "Four_Of_A_Kind_Six,Two_Pairs_Ten_And_Seven,1",
                "Two_Pairs_Ten_And_Seven,Four_Of_A_Kind_Six,-1",
                "Four_Of_A_Kind_Ten,One_Pair_Six,1",
                "One_Pair_Six,Four_Of_A_Kind_Ten,-1",
                "Four_Of_A_Kind_Six,High_Card_Ace,1",
                "High_Card_Ace,Four_Of_A_Kind_Six,-1",
                "Full_House_Jacks_With_Aces,Full_House_Sevens_With_Fives,1",
                "Full_House_Sevens_With_Fives,Full_House_Jacks_With_Aces,-1",
                "Full_House_Jacks_With_Twos,Full_House_Sevens_With_Fives,1",
                "Full_House_Sevens_With_Fives,Full_House_Jacks_With_Twos,-1",
                "Full_House_Jacks_With_Aces,Flush_Eight_High_Diamonds,1",
                "Flush_Eight_High_Diamonds,Full_House_Jacks_With_Aces,-1",
                "Full_House_Sevens_With_Fives,Straight_Queen_High,1",
                "Straight_Queen_High,Full_House_Sevens_With_Fives,-1",
                "Full_House_Sevens_With_Fives,Three_Of_A_Kind_Two,1",
                "Three_Of_A_Kind_Two,Full_House_Sevens_With_Fives,-1",
                "Full_House_Sevens_With_Fives,Two_Pairs_Ten_And_Seven,1",
                "Two_Pairs_Ten_And_Seven,Full_House_Sevens_With_Fives,-1",
                "Full_House_Sevens_With_Fives,One_Pair_Six,1",
                "One_Pair_Six,Full_House_Sevens_With_Fives,-1",
                "Full_House_Sevens_With_Fives,High_Card_Ace,1",
                "High_Card_Ace,Full_House_Sevens_With_Fives,-1",
                "Flush_QJ852_Clubs,Flush_Eight_High_Diamonds,1",
                "Flush_Eight_High_Diamonds,Flush_QJ852_Clubs,-1",
                "Flush_QJ852_Clubs,Flush_QT852_Hearts,1",
                "Flush_QT852_Hearts,Flush_QJ852_Clubs,-1",
                "Flush_QJ852_Clubs,Flush_QJ752_Hearts,1",
                "Flush_QJ752_Hearts,Flush_QJ852_Clubs,-1",
                "Flush_QJ852_Clubs,Flush_QJ842_Hearts,1",
                "Flush_QJ842_Hearts,Flush_QJ852_Clubs,-1",
                "Flush_QJ853_Hearts,Flush_QJ852_Clubs,1",
                "Flush_QJ852_Clubs,Flush_QJ853_Hearts,-1",
                "Flush_Eight_High_Diamonds,Straight_Queen_High,1",
                "Straight_Queen_High,Flush_Eight_High_Diamonds,-1",
                "Flush_Eight_High_Diamonds,Three_Of_A_Kind_King,1",
                "Three_Of_A_Kind_King,Flush_Eight_High_Diamonds,-1",
                "Flush_Eight_High_Diamonds,Two_Pairs_Ten_And_Seven,1",
                "Two_Pairs_Ten_And_Seven,Flush_Eight_High_Diamonds,-1",
                "Flush_Eight_High_Diamonds,One_Pair_Six,1",
                "One_Pair_Six,Flush_Eight_High_Diamonds,-1",
                "Flush_Eight_High_Diamonds,High_Card_Ace,1",
                "High_Card_Ace,Flush_Eight_High_Diamonds,-1",
                "Straight_Queen_High,Straight_Jack_High,1",
                "Straight_Jack_High,Straight_Queen_High,-1",
                "Straight_Queen_High,Three_Of_A_Kind_Two,1",
                "Three_Of_A_Kind_Two,Straight_Queen_High,-1",
                "Straight_Queen_High,Two_Pairs_Ten_And_Seven,1",
                "Two_Pairs_Ten_And_Seven,Straight_Queen_High,-1",
                "Straight_Queen_High,One_Pair_Six,1",
                "One_Pair_Six,Straight_Queen_High,-1",
                "Straight_Jack_High,High_Card_Ace,1",
                "High_Card_Ace,Straight_Jack_High,-1",
                "Three_Of_A_Kind_King,Three_Of_A_Kind_Two,1",
                "Three_Of_A_Kind_Two,Three_Of_A_Kind_King,-1",
                "Three_Of_A_Kind_Two,Two_Pairs_Ten_And_Seven,1",
                "Two_Pairs_Ten_And_Seven,Three_Of_A_Kind_Two,-1",
                "Three_Of_A_Kind_King,One_Pair_Six,1",
                "One_Pair_Six,Three_Of_A_Kind_King,-1",
                "Three_Of_A_Kind_King,High_Card_Ace,1",
                "High_Card_Ace,Three_Of_A_Kind_King,-1",
                "Two_Pairs_Jacks_And_Tens,Two_Pairs_Ten_And_Seven,1",
                "Two_Pairs_Ten_And_Seven,Two_Pairs_Jacks_And_Tens,-1",
                "Two_Pairs_Ten_And_Seven,Two_Pairs_Tens_And_Sixes_Kicker3,1",
                "Two_Pairs_Tens_And_Sixes_Kicker3,Two_Pairs_Ten_And_Seven,-1",
                "Two_Pairs_Tens_And_Sixes_Kicker4,Two_Pairs_Tens_And_Sixes_Kicker3,1",
                "Two_Pairs_Tens_And_Sixes_Kicker3,Two_Pairs_Tens_And_Sixes_Kicker4,-1",
                "Two_Pairs_Ten_And_Seven,One_Pair_Six,1",
                "One_Pair_Six,Two_Pairs_Ten_And_Seven,-1",
                "Two_Pairs_Ten_And_Seven,High_Card_Ace,1",
                "High_Card_Ace,Two_Pairs_Ten_And_Seven,-1",
                "One_Pair_NineKJ5,One_Pair_Six,1", // pb ici!!!
                "One_Pair_Six,One_Pair_NineKJ5,-1",
                "One_Pair_NineKJ5,One_Pair_NineQJ5,1",
                "One_Pair_NineQJ5,One_Pair_NineKJ5,-1",
                "One_Pair_NineKJ5,One_Pair_NineKT5,1",
                "One_Pair_NineKT5,One_Pair_NineKJ5,-1",
                "One_Pair_NineKJ5,One_Pair_NineKJ4,1",
                "One_Pair_NineKJ4,One_Pair_NineKJ5,-1",
                "One_Pair_NineKJ5,High_Card_Ace,1",
                "High_Card_Ace,One_Pair_NineKJ5,-1",
                "High_Card_Ace,High_Card_QT864,1",
                "High_Card_QT864,High_Card_Ace,-1",
                "High_Card_QT864,High_Card_Q9864,1",
                "High_Card_Q9864,High_Card_QT864,-1",
                "High_Card_QT864,High_Card_QT764,1",
                "High_Card_QT764,High_Card_QT864,-1",
                "High_Card_QT864,High_Card_QT854,1",
                "High_Card_QT854,High_Card_QT864,-1",
                "High_Card_QT864,High_Card_QT863,1",
                "High_Card_QT863,High_Card_QT864,-1",
    })
    void whenCompareTo_ThenExpectedResult(String firstHandName, String secondHandName, int expectedSign) {
        CombinationFactory firstPlayerHand = from(firstHandName);
        CombinationFactory secondPlayerHand = from(secondHandName);
        //assertTrue(PokerHandStrengthComparator.compareStrength(firstPlayerHand, secondPlayerHand) * expectedSign > 0);
    }

    private CombinationFactory from(String handName) {
        Hand hand = new Hand(cardsFor(handName));
        //return new CombinationFactory(hand);
        return null;
    }

    private Set<Card> cardsFor(String handName) {
        switch (handName) {
            case "Straight_Flush_Ace":
                return Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.KING, Suit.SPADES), new Card(Rank.QUEEN, Suit.SPADES), new Card(Rank.JACK, Suit.SPADES), new Card(Rank.TEN, Suit.SPADES));
            case "Straight_Flush_Jack":
                return Set.of(new Card(Rank.JACK, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.SEVEN, Suit.CLUBS));
            case "Four_Of_A_Kind_Six":
                return Set.of(new Card(Rank.SIX, Suit.SPADES), new Card(Rank.SIX, Suit.DIAMONDS), new Card(Rank.SIX, Suit.CLUBS), new Card(Rank.SIX, Suit.HEARTS), new Card(Rank.FOUR, Suit.HEARTS));
            case "Four_Of_A_Kind_FiveKickerAce":
                return Set.of(new Card(Rank.FIVE, Suit.SPADES), new Card(Rank.FIVE, Suit.DIAMONDS), new Card(Rank.FIVE, Suit.CLUBS), new Card(Rank.FIVE, Suit.HEARTS), new Card(Rank.ACE, Suit.HEARTS));
            case "Four_Of_A_Kind_Ten":
                return Set.of(new Card(Rank.TEN, Suit.SPADES), new Card(Rank.TEN, Suit.DIAMONDS), new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.HEARTS), new Card(Rank.TWO, Suit.HEARTS));
            case "Full_House_Jacks_With_Aces":
                return Set.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.JACK, Suit.CLUBS), new Card(Rank.JACK, Suit.HEARTS), new Card(Rank.JACK, Suit.DIAMONDS));
            case "Full_House_Jacks_With_Twos":
                return Set.of(new Card(Rank.JACK, Suit.SPADES), new Card(Rank.JACK, Suit.DIAMONDS), new Card(Rank.JACK, Suit.CLUBS), new Card(Rank.TWO, Suit.HEARTS), new Card(Rank.TWO, Suit.DIAMONDS));
            case "Full_House_Sevens_With_Fives":
                return Set.of(new Card(Rank.SEVEN, Suit.SPADES), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.CLUBS), new Card(Rank.FIVE, Suit.HEARTS), new Card(Rank.FIVE, Suit.DIAMONDS));
            case "Flush_Eight_High_Diamonds":
                return Set.of(new Card(Rank.EIGHT, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.SIX, Suit.DIAMONDS), new Card(Rank.FIVE, Suit.DIAMONDS), new Card(Rank.TWO, Suit.DIAMONDS));
            case "Flush_QJ852_Clubs":
                return Set.of(new Card(Rank.QUEEN, Suit.CLUBS), new Card(Rank.JACK, Suit.CLUBS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.FIVE, Suit.CLUBS), new Card(Rank.TWO, Suit.CLUBS));
            case "Flush_QT852_Hearts":
                return Set.of(new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.TEN, Suit.HEARTS), new Card(Rank.EIGHT, Suit.HEARTS), new Card(Rank.FIVE, Suit.HEARTS), new Card(Rank.TWO, Suit.HEARTS));
            case "Flush_QJ752_Hearts":
                return Set.of(new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.JACK, Suit.HEARTS), new Card(Rank.SEVEN, Suit.HEARTS), new Card(Rank.FIVE, Suit.HEARTS), new Card(Rank.TWO, Suit.HEARTS));
            case "Flush_QJ842_Hearts":
                return Set.of(new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.JACK, Suit.HEARTS), new Card(Rank.EIGHT, Suit.HEARTS), new Card(Rank.FOUR, Suit.HEARTS), new Card(Rank.TWO, Suit.HEARTS));
            case "Flush_QJ853_Hearts":
                return Set.of(new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.JACK, Suit.HEARTS), new Card(Rank.EIGHT, Suit.HEARTS), new Card(Rank.FIVE, Suit.HEARTS), new Card(Rank.THREE, Suit.HEARTS));
            case "Straight_Queen_High":
                return Set.of(new Card(Rank.QUEEN, Suit.DIAMONDS), new Card(Rank.JACK, Suit.CLUBS), new Card(Rank.TEN, Suit.DIAMONDS), new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.EIGHT, Suit.HEARTS));
            case "Straight_Jack_High":
                return Set.of(new Card(Rank.JACK, Suit.DIAMONDS), new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.NINE, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.SPADES), new Card(Rank.SEVEN, Suit.HEARTS));
            case "Three_Of_A_Kind_Two":
                return Set.of(new Card(Rank.NINE, Suit.HEARTS), new Card(Rank.SEVEN, Suit.HEARTS), new Card(Rank.TWO, Suit.DIAMONDS), new Card(Rank.TWO, Suit.CLUBS), new Card(Rank.TWO, Suit.HEARTS));
            case "Three_Of_A_Kind_King":
                return Set.of(new Card(Rank.KING, Suit.HEARTS), new Card(Rank.KING, Suit.CLUBS), new Card(Rank.KING, Suit.SPADES), new Card(Rank.QUEEN, Suit.CLUBS), new Card(Rank.JACK, Suit.HEARTS));
            case "Two_Pairs_Ten_And_Seven":
                return Set.of(new Card(Rank.TEN, Suit.SPADES), new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.SEVEN, Suit.SPADES), new Card(Rank.SEVEN, Suit.CLUBS), new Card(Rank.THREE, Suit.SPADES));
            case "Two_Pairs_Jacks_And_Tens":
                return Set.of(new Card(Rank.JACK, Suit.SPADES), new Card(Rank.JACK, Suit.CLUBS), new Card(Rank.TEN, Suit.HEARTS), new Card(Rank.TEN, Suit.DIAMONDS), new Card(Rank.THREE, Suit.DIAMONDS));
            case "Two_Pairs_Tens_And_Sixes_Kicker3":
                return Set.of(new Card(Rank.TEN, Suit.HEARTS), new Card(Rank.TEN, Suit.DIAMONDS), new Card(Rank.SIX, Suit.SPADES), new Card(Rank.SIX, Suit.CLUBS), new Card(Rank.THREE, Suit.HEARTS));
            case "Two_Pairs_Tens_And_Sixes_Kicker4":
                return Set.of(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.SPADES), new Card(Rank.SIX, Suit.HEARTS), new Card(Rank.SIX, Suit.DIAMONDS), new Card(Rank.FOUR, Suit.HEARTS));
            case "One_Pair_Six":
                return Set.of(new Card(Rank.KING, Suit.DIAMONDS), new Card(Rank.QUEEN, Suit.CLUBS), new Card(Rank.SEVEN, Suit.HEARTS), new Card(Rank.SIX, Suit.CLUBS), new Card(Rank.SIX, Suit.SPADES));
            case "One_Pair_NineKJ5":
                return Set.of(new Card(Rank.KING, Suit.SPADES), new Card(Rank.JACK, Suit.CLUBS), new Card(Rank.NINE, Suit.HEARTS), new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.FIVE, Suit.SPADES));
            case "One_Pair_NineQJ5":
                return Set.of(new Card(Rank.QUEEN, Suit.SPADES), new Card(Rank.JACK, Suit.DIAMONDS), new Card(Rank.NINE, Suit.DIAMONDS), new Card(Rank.NINE, Suit.SPADES), new Card(Rank.FIVE, Suit.HEARTS));
            case "One_Pair_NineKT5":
                return Set.of(new Card(Rank.KING, Suit.DIAMONDS), new Card(Rank.TEN, Suit.DIAMONDS), new Card(Rank.NINE, Suit.DIAMONDS), new Card(Rank.NINE, Suit.SPADES), new Card(Rank.FIVE, Suit.HEARTS));
            case "One_Pair_NineKJ4":
                return Set.of(new Card(Rank.KING, Suit.DIAMONDS), new Card(Rank.JACK, Suit.DIAMONDS), new Card(Rank.NINE, Suit.DIAMONDS), new Card(Rank.TEN, Suit.SPADES), new Card(Rank.FOUR, Suit.HEARTS));
            case "High_Card_Ace":
                return Set.of(new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.KING, Suit.DIAMONDS), new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.THREE, Suit.HEARTS));
            case "High_Card_QT864":
                return Set.of(new Card(Rank.QUEEN, Suit.DIAMONDS), new Card(Rank.TEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.SPADES), new Card(Rank.SIX, Suit.CLUBS), new Card(Rank.FOUR, Suit.HEARTS));
            case "High_Card_Q9864":
                return Set.of(new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.NINE, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.SIX, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS));
            case "High_Card_QT764":
                return Set.of(new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.SEVEN, Suit.CLUBS), new Card(Rank.SIX, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS));
            case "High_Card_QT854":
                return Set.of(new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.FIVE, Suit.SPADES), new Card(Rank.FOUR, Suit.DIAMONDS));
            case "High_Card_QT863":
                return Set.of(new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.SIX, Suit.SPADES), new Card(Rank.THREE, Suit.DIAMONDS));

            default:
                throw new RuntimeException();
        }
    }

}
