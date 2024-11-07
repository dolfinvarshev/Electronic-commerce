package AlmogAsiaDolfinVarshev;

import java.util.Comparator;

class CompareBuyerByName implements Comparator<Buyer> {
    public int compare(Buyer buy1, Buyer buy2) {
        return buy1.getUsername().compareTo(buy2.getUsername());
    }
}

