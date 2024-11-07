package AlmogAsiaDolfinVarshev;

import java.util.Date;

public class Cart {
    private ProductList products;
    private Date date;

    public Cart() {
        this.products = new ProductList();
        this.date = null;
    }

    public Cart(Cart cart) {
        this.products = new ProductList(cart.getProductList());
        this.date = cart.getDate();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductList getProductList() {
        return products;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Cart)) {
            return false;
        }

        if (!(super.equals(other))) {
            return false;
        }

        Cart temp = (Cart) other;
        boolean equal = false;
        if (this.products.equals(temp.getProductList())
                && this.date.equals(temp.getDate())) {
            equal = true;
        }
        return equal;
    }

    public String toString() {
        StringBuilder temp = new StringBuilder();
        if (date != null) {
            temp.append("Purchase date: ").append(date.toString());
            temp.append(", Total price = ").append(products.getCartPrice()).append("$").append("\n");
        }
        temp.append(products.toString());
        return temp.toString();
    }

    public void setPurchaseTime() {
        this.date = new Date();
    }
}
