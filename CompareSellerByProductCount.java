package AlmogAsiaDolfinVarshev;

import java.util.Comparator;

public class CompareSellerByProductCount implements Comparator<Seller> {

    public int compare(Seller sel1, Seller sel2) {
        return Integer.compare(sel2.getProductList().getProductCount(), sel1.getProductList().getProductCount());
    }
}
