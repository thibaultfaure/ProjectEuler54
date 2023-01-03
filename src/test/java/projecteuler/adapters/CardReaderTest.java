package projecteuler.adapters;

import org.junit.jupiter.api.Test;
import projecteuler.domain.Card;
import projecteuler.domain.Deal;
import projecteuler.domain.Rank;
import projecteuler.domain.Suit;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

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
    void whenReadHands_ThenReturnParsedDeals() throws URISyntaxException, IOException {
        List<Deal> deals = new CardReader("valid_file.txt").readHands();
        assertEquals(3, deals.size());
        deals.forEach(deal -> {
            assertEquals(5, deal.getPlayer1Hand().getCards().size());
            assertEquals(5, deal.getPlayer2Hand().getCards().size());
        });
        Deal firstDeal = deals.get(0);
        assertTrue(firstDeal.getPlayer1Hand().getCards().containsAll(Set.of(new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.TEN, Suit.SPADES),
                new Card(Rank.KING, Suit.CLUBS), new Card(Rank.NINE, Suit.HEARTS), new Card(Rank.FOUR, Suit.SPADES))));
        assertTrue(firstDeal.getPlayer2Hand().getCards().containsAll(Set.of(new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.FIVE, Suit.DIAMONDS), new Card(Rank.THREE, Suit.SPADES), new Card(Rank.ACE, Suit.CLUBS))));

        Deal secondDeal = deals.get(1);
        assertTrue(secondDeal.getPlayer1Hand().getCards().containsAll(Set.of(new Card(Rank.FIVE, Suit.CLUBS), new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.FIVE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.NINE, Suit.CLUBS))));
        assertTrue(secondDeal.getPlayer2Hand().getCards().containsAll(Set.of(new Card(Rank.SEVEN, Suit.CLUBS), new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.DIAMONDS), new Card(Rank.TEN, Suit.DIAMONDS), new Card(Rank.KING, Suit.SPADES))));

        Deal thirdDeal = deals.get(2);
        assertTrue(thirdDeal.getPlayer1Hand().getCards().containsAll(Set.of(new Card(Rank.QUEEN, Suit.DIAMONDS), new Card(Rank.ACE, Suit.SPADES),
                new Card(Rank.SIX, Suit.HEARTS), new Card(Rank.JACK, Suit.SPADES), new Card(Rank.TWO, Suit.CLUBS))));
        assertTrue(thirdDeal.getPlayer2Hand().getCards().containsAll(Set.of(new Card(Rank.THREE, Suit.DIAMONDS), new Card(Rank.NINE, Suit.HEARTS),
                new Card(Rank.KING, Suit.CLUBS), new Card(Rank.FOUR, Suit.HEARTS), new Card(Rank.EIGHT, Suit.SPADES))));
    }

}
