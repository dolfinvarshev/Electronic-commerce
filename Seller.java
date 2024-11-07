package AlmogAsiaDolfinVarshev;

public class Seller extends User{
    private ProductList products;

    public Seller(String username, String password) {
        super(username, password);
        this.products = new ProductList();
    }

    public ProductList getProductList() {
        return products;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Seller)) {
            return false;
        }

        if (!(super.equals(other))) {
            return false;
        }

        Seller temp = (Seller) other;
        boolean equal = false;
        if (this.products.equals(temp.getProductList())) {
            equal = true;
        }
        return equal;
    }

    public String toString() {
        return super.toString();
    }

}
