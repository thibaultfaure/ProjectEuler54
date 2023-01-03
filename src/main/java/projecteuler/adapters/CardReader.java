package projecteuler.adapters;

import projecteuler.domain.cardgame.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class CardReader {

    private static final int HAND_SIZE = 5;

    private String fileName;

    public CardReader(String fileName) {
        this.fileName = fileName;
    }

    public List<Deal> readHands() throws URISyntaxException, IOException {
        URL url = getClass().getClassLoader().getResource(fileName);
        if (url == null) {
            throw new IllegalArgumentException("Impossible to find file " + fileName);
        }
        Path path = Paths.get(url.toURI());

        try (Stream<String> lines = Files.lines(path)) {
            return lines.map(this::toDeal).toList();
        }
    }

    private Deal toDeal(String deal) {
        String[] cards = deal.split("\\s");
        if (cards.length != 10) {
            throw new IllegalArgumentException(("File line " + deal + " is an invalid deal"));
        }
        int index = 0;
        Set<Card> player1Cards = new HashSet<>();
        Set<Card> player2Cards = new HashSet<>();
        while (index < HAND_SIZE) {
            player1Cards.add(toCard(cards[index]));
            index++;
        }
        while (index < 2 * HAND_SIZE) {
            player2Cards.add(toCard(cards[index]));
            index++;
        }

        return new Deal.DealBuilder()
                .withPlayer1Hand(new Hand(player1Cards))
                .withPlayer2Hand(new Hand(player2Cards))
                .build();
    }

    private Card toCard(String card) {
        String[] rankAndSuit = card.split("");
        if (rankAndSuit.length != 2) {
            throw new IllegalArgumentException("Invalid card " + card);
        }
        Rank rank = toRank(rankAndSuit[0]);
        Suit suit = toSuit(rankAndSuit[1]);
        return new Card(rank, suit);
    }

    private Rank toRank(String rank) {
        switch (rank) {
            case "A": return Rank.ACE;
            case "K": return Rank.KING;
            case "Q": return Rank.QUEEN;
            case "J": return Rank.JACK;
            case "T": return Rank.TEN;
            case "9": return Rank.NINE;
            case "8": return Rank.EIGHT;
            case "7": return Rank.SEVEN;
            case "6": return Rank.SIX;
            case "5": return Rank.FIVE;
            case "4": return Rank.FOUR;
            case "3": return Rank.THREE;
            case "2": return Rank.TWO;
            default: throw new IllegalArgumentException("Invalid rank " + rank);
        }
    }

    private Suit toSuit(String suit) {
        switch (suit) {
            case "C": return Suit.CLUBS;
            case "D": return Suit.DIAMONDS;
            case "H": return Suit.HEARTS;
            case "S": return Suit.SPADES;
            default: throw new IllegalArgumentException("Invalid suit " + suit);
        }
    }

}
