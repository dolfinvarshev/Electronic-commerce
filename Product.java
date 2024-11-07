package AlmogAsiaDolfinVarshev;

public class Product implements Cloneable {
    private static int counter;
    private final int id;
    private final Category category;
    private String productName;
    private double price;

    public Product(String productName, double price, Category category) {
        this.productName = productName;
        this.price = price;
        this.id = ++counter;
        this.category = category;
    }

    public Product(Product prod) {
        this.productName = prod.getProductName();
        this.price = prod.getPrice();
        this.id = prod.getId();
        this.category = prod.getCategory();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    final public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws Exception {
        if (price <= 0) {
            throw new Exception("Price must be greater than 0");
        } else {
            this.price = price;
        }

    }

    public double getTotalPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Product)) {
            return false;
        }

        if (!(super.equals(other))) {
            return false;
        }

        Product temp = (Product) other;
        return this.productName.equals(temp.getProductName())
                && this.price == temp.getPrice()
                && this.id == temp.getId()
                && this.category.equals(temp.getCategory());
    }

    public String toString() {
        return "Product name - " + productName + ", Id - " + id + ", Total price - " + price + "$";
    }

    public void pack() {
    }


    public enum Category {Kids, Electricity, Office, Clothing}
}
