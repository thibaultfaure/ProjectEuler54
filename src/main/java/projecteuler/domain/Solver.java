package projecteuler.domain;

import projecteuler.adapters.ConsoleSolutionWriter;
import projecteuler.adapters.FileCardReader;
import projecteuler.domain.deck.Deal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Solver {

    private static final String FILE_NAME = "poker.txt";

    public static void main(String[] args) throws URISyntaxException, IOException {
        FileCardReader cardReader = new FileCardReader(FILE_NAME);
        List<Deal> deals = cardReader.readHands();
        ConsoleSolutionWriter consoleSolutionWriter = new ConsoleSolutionWriter();
        deals.forEach(consoleSolutionWriter::prettyPrintPlayerHandsAndWinner);
    }

}
