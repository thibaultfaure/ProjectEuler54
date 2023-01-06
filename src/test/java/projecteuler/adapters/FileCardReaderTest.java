package projecteuler.adapters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import projecteuler.domain.deck.Card;
import projecteuler.domain.deck.Deal;
import projecteuler.domain.deck.Rank;
import projecteuler.domain.deck.Suit;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileCardReaderTest {

    @ParameterizedTest
    @CsvSource({"unknown.txt, Impossible to find file unknown.txt",
                "file_with_invalid_deal.txt, File line 5H 6D is an invalid deal",
                "file_with_invalid_card.txt, Invalid card 2",
                "file_with_invalid_rank.txt, Invalid rank S",
                "file_with_invalid_suit.txt, Invalid suit T"
    })
    void givenInvalidFile_WhenReadHands_ThenThrows(String fileName, String expectedErrorMessage) {
        FileCardReader fileCardReader = new FileCardReader(fileName);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fileCardReader.readHands());
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void whenReadHands_ThenReturnDealsWithOrderedHands() throws URISyntaxException, IOException {
        List<Deal> deals = new FileCardReader("valid_file.txt").readHands();
        assertEquals(3, deals.size());
        deals.forEach(deal -> {
            assertEquals(5, deal.getPlayer1Hand().getCards().size());
            assertEquals(5, deal.getPlayer2Hand().getCards().size());
        });
        Deal firstDeal = deals.get(0);
        assertEquals(List.of(new Card(Rank.KING, Suit.CLUBS), new Card(Rank.TEN, Suit.SPADES), new Card(Rank.NINE, Suit.HEARTS), new Card(Rank.EIGHT, Suit.CLUBS), new Card(Rank.FOUR, Suit.SPADES)), new ArrayList<>(firstDeal.getPlayer1Hand().getCards()));
        assertEquals(List.of(new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.SEVEN, Suit.DIAMONDS), new Card(Rank.FIVE, Suit.DIAMONDS), new Card(Rank.THREE, Suit.SPADES), new Card(Rank.TWO, Suit.SPADES)), new ArrayList<>(firstDeal.getPlayer2Hand().getCards()));

        Deal secondDeal = deals.get(1);
        List<Card> secondDealPlayer1Hand = new ArrayList<>(secondDeal.getPlayer1Hand().getCards());
        assertTrue(secondDealPlayer1Hand.containsAll(List.of(new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.FIVE, Suit.CLUBS), new Card(Rank.FIVE, Suit.DIAMONDS))));
        assertEquals(Rank.ACE, secondDealPlayer1Hand.get(0).rank());
        assertEquals(Rank.ACE, secondDealPlayer1Hand.get(1).rank());
        assertEquals(Rank.NINE, secondDealPlayer1Hand.get(2).rank());
        assertEquals(Rank.FIVE, secondDealPlayer1Hand.get(3).rank());
        assertEquals(Rank.FIVE, secondDealPlayer1Hand.get(3).rank());
        assertEquals(List.of(new Card(Rank.KING, Suit.SPADES), new Card(Rank.TEN, Suit.DIAMONDS), new Card(Rank.EIGHT, Suit.DIAMONDS), new Card(Rank.SEVEN, Suit.CLUBS), new Card(Rank.FIVE, Suit.HEARTS)), new ArrayList<>(secondDeal.getPlayer2Hand().getCards()));

        Deal thirdDeal = deals.get(2);
        assertEquals(List.of(new Card(Rank.ACE, Suit.SPADES), new Card(Rank.QUEEN, Suit.DIAMONDS), new Card(Rank.JACK, Suit.SPADES), new Card(Rank.SIX, Suit.HEARTS), new Card(Rank.TWO, Suit.CLUBS)), new ArrayList<>(thirdDeal.getPlayer1Hand().getCards()));
        assertEquals(List.of(new Card(Rank.KING, Suit.CLUBS), new Card(Rank.NINE, Suit.HEARTS), new Card(Rank.EIGHT, Suit.SPADES), new Card(Rank.FOUR, Suit.HEARTS), new Card(Rank.THREE, Suit.DIAMONDS)), new ArrayList<>(thirdDeal.getPlayer2Hand().getCards()));
    }

}
