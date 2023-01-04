package projecteuler.domain;

import projecteuler.adapters.CardReader;
import projecteuler.domain.cardgame.Deal;
import projecteuler.domain.poker.PokerHand;
import projecteuler.domain.poker.PokerHandStrengthComparator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Solver {

    private static final String FILE_NAME = "poker.txt";

    public static void main(String[] args) throws URISyntaxException, IOException {
        CardReader cardReader = new CardReader(FILE_NAME);
        List<Deal> deals = cardReader.readHands();
        deals.forEach(Solver::prettyPrintPlayerHandsAndWinner);
    }

    public static void prettyPrintPlayerHandsAndWinner(Deal deal) {
        PokerHand pokerHandPlayer1 = new PokerHand(deal.getPlayer1Hand());
        PokerHand pokerHandPlayer2 = new PokerHand(deal.getPlayer2Hand());
        int winner = getWinner(pokerHandPlayer1, pokerHandPlayer2);
        System.out.println("Player 1: " + pokerHandPlayer1 + "; Player 2: " + pokerHandPlayer2 + "; Winner: " + winner);
    }

    private static int getWinner(PokerHand pokerHand1, PokerHand pokerHand2) {
        // NB: tied cases ignored as stated in the specification
        return PokerHandStrengthComparator.compareStrength(pokerHand1, pokerHand2) > 0 ? 1 : 2;
    }

}
