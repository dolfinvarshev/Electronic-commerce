package AlmogAsiaDolfinVarshev;

public class SpecialPackage extends Product {
    private boolean packed;
    private double packagingPrice;

    public SpecialPackage(String productName, double price, Category category, double packagingPrice) {
        super(productName, price, category);
        this.packagingPrice = packagingPrice;
        this.packed = false;
    }

    public SpecialPackage(SpecialPackage prod) {
        super(prod);
        this.packagingPrice = prod.getPackagingPrice();
        this.packed = prod.getPacked();
    }

    public double getPackagingPrice() {
        return packagingPrice;
    }

    public void setPackagingPrice(double packagingPrice) throws Exception {
        if (packagingPrice <= 0) {
            throw new Exception("Packaging price cannot be negative");
        } else {
            this.packagingPrice = packagingPrice;
        }
    }

    public boolean equals(Object other) {
        if (!(other instanceof SpecialPackage)) {
            return false;
        }

        if (!(super.equals(other))) {
            return false;
        }

        SpecialPackage temp = (SpecialPackage) other;
        return this.packed == temp.getPacked() && this.packagingPrice == temp.packagingPrice;

    }

    public boolean getPacked() {
        return packed;
    }

    public double getTotalPrice() {
        return getPrice() + packagingPrice;
    }


    public String toString() {
        return "Product name - " + getProductName() +
                ", Id - " + getId() +
                ", Price - " + getPrice() + "$" +
                ", Special packaging price  - " + packagingPrice + "$" +
                ", Total price - " + getTotalPrice() + "$";
    }

    public void pack() {
        if (!packed) {
            this.packed = true;
            System.out.println("The Package is ready");
        } else {
            System.out.println("The package is already packed");
        }

    }
}
