package projecteuler.adapters;

import org.junit.jupiter.api.Test;
import projecteuler.domain.cardgame.Card;
import projecteuler.domain.cardgame.Deal;
import projecteuler.domain.cardgame.Rank;
import projecteuler.domain.cardgame.Suit;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CardReaderTest {

    @Test
    void givenInvalidFileName_WhenReadHands_ThenThrows() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CardReader("unknown.txt").readHands());
        assertEquals("Impossible to find file unknown.txt", exception.getMessage());
    }

    @Test
    void givenFileWithInvalidDeal_WhenReadHands_ThenThrows() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CardReader("file_with_invalid_deal.txt").readHands());
        assertEquals("File line 5H 6D is an invalid deal", exception.getMessage());
    }

    @Test
    void givenFileWithInvalidCard_WhenReadHands_ThenThrows() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CardReader("file_with_invalid_card.txt").readHands());
        assertEquals("Invalid card 2", exception.getMessage());
    }

    @Test
    void givenFileWithInvalidRank_WhenReadHands_ThenThrows() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CardReader("file_with_invalid_rank.txt").readHands());
        assertEquals("Invalid rank S", exception.getMessage());
    }

    @Test
    void givenFileWithInvalidSuit_WhenReadHands_ThenThrows() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CardReader("file_with_invalid_suit.txt").readHands());
        assertEquals("Invalid suit T", exception.getMessage());
    }

    @Test
    void whenReadHands_ThenReturnDealsWithOrderedHands() throws URISyntaxException, IOException {
        List<Deal> deals = new CardReader("valid_file.txt").readHands();
        assertEquals(3, deals.size());
        deals.forEach(deal -> {
            assertEquals(5, deal.getPlayer1Hand().getCards().size());
            assertEquals(5, deal.getPlayer2Hand().getCards().size());
        });
        Deal firstDeal = deals.get(0);
        assertTrue(new ArrayList<>(firstDeal.getPlayer1Hand().getCards())
                .equals((List.of(new Card(Rank.KING, Suit.CLUBS), new Card(Rank.TEN, Suit.SPADES), new Card(Rank.NINE, Suit.HEARTS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.FOUR, Suit.SPADES)))));
        assertTrue(new ArrayList<>(firstDeal.getPlayer2Hand().getCards())
                .equals(List.of(new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.FIVE, Suit.DIAMONDS), new Card(Rank.THREE, Suit.SPADES), new Card(Rank.TWO, Suit.SPADES))));

        Deal secondDeal = deals.get(1);
        List<Card> secondDealPlayer1Hand = new ArrayList<>(secondDeal.getPlayer1Hand().getCards());
        assertTrue(secondDealPlayer1Hand.containsAll(List.of(new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.FIVE, Suit.CLUBS), new Card(Rank.FIVE, Suit.DIAMONDS))));
        assertEquals(Rank.ACE, secondDealPlayer1Hand.get(0).getRank());
        assertEquals(Rank.ACE, secondDealPlayer1Hand.get(1).getRank());
        assertEquals(Rank.NINE, secondDealPlayer1Hand.get(2).getRank());
        assertEquals(Rank.FIVE, secondDealPlayer1Hand.get(3).getRank());
        assertEquals(Rank.FIVE, secondDealPlayer1Hand.get(3).getRank());
        assertTrue(new ArrayList<>(secondDeal.getPlayer2Hand().getCards())
                .equals(List.of(new Card(Rank.KING, Suit.SPADES), new Card(Rank.TEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.CLUBS), new Card(Rank.FIVE, Suit.HEARTS))));

        Deal thirdDeal = deals.get(2);
        assertTrue(new ArrayList<>(thirdDeal.getPlayer1Hand().getCards())
                .equals(List.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.QUEEN, Suit.DIAMONDS), new Card(Rank.JACK, Suit.SPADES), new Card(Rank.SIX, Suit.HEARTS), new Card(Rank.TWO, Suit.CLUBS))));
        assertTrue(new ArrayList<>(thirdDeal.getPlayer2Hand().getCards())
                .equals(List.of(new Card(Rank.KING, Suit.CLUBS), new Card(Rank.NINE, Suit.HEARTS), new Card(Rank.EIGHT, Suit.SPADES), new Card(Rank.FOUR, Suit.HEARTS), new Card(Rank.THREE, Suit.DIAMONDS))));
    }

}
