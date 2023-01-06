package projecteuler.adapters;

import projecteuler.domain.deck.Deal;
import projecteuler.domain.poker.Combination;
import projecteuler.domain.poker.CombinationFactory;

public class ConsoleSolutionWriter {

    private static final int PLAYER1_WINS = 1;
    private static final int PLAYER2_WINS = 2;

    private static final CombinationFactory combinationFactory = new CombinationFactory();

    public void prettyPrintPlayerHandsAndWinner(Deal deal) {
        Combination combinationPlayer1 = combinationFactory.createCombination(deal.getPlayer1Hand());
        Combination combinationPlayer2 = combinationFactory.createCombination(deal.getPlayer2Hand());
        int winner = getWinner(combinationPlayer1, combinationPlayer2);
        System.out.println("Player 1: " + combinationPlayer1 + "; Player 2: " + combinationPlayer2 + "; Winner: " + winner);
    }

    private static int getWinner(Combination combination1, Combination combination2) {
        // NB: tied cases ignored as stated in the specification
        return combination1.compareTo(combination2) > 0 ? PLAYER1_WINS : PLAYER2_WINS;
    }

}
