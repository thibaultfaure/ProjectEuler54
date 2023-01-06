package projecteuler.domain.deck;

import java.util.Comparator;

public class RankComparator implements Comparator<Rank> {

    @Override
    public int compare(Rank rank1, Rank rank2) {
        return compareRanks(rank1, rank2);
    }

    public static int compareRanks(Rank rank1, Rank rank2) {
        return Integer.compare(rank1.getValue(), rank2.getValue());
    }

}
