package AlmogAsiaDolfinVarshev;

public class ProductList {
    private Product[] productArray;
    private int productCount;

    public ProductList() {
        this.productCount = 0;
        this.productArray = new Product[2];
    }

    public ProductList(ProductList prodList) {
        this.productCount = prodList.getProductCount();
        this.productArray = new Product[prodList.getProductArray().length];
        for (int i = 0; i < prodList.getProductCount(); i++) {
            if (prodList.getProductArray()[i] instanceof SpecialPackage) {
                this.productArray[i] = new SpecialPackage((SpecialPackage) prodList.getProductArray()[i]);
            } else {
                this.productArray[i] = new Product(prodList.getProductArray()[i]);
            }
        }
    }

    public Product[] getProductArray() {
        return productArray;
    }

    public int getProductCount() {
        return productCount;
    }

    public void addProduct(Product newProduct) {
        if (productCount >= productArray.length) {
            Product[] newProductArray = new Product[productArray.length * 2];
            for (int i = 0; i < productArray.length; i++) {
                newProductArray[i] = productArray[i];
            }
            productArray = newProductArray;
        }
        productArray[productCount] = newProduct;
        productCount += 1;
    }

    public double getCartPrice() {
        double cartPrice = 0;
        for (int i = 0; i < productCount; i++) {
            cartPrice += productArray[i].getTotalPrice();
        }
        return cartPrice;
    }

    public String toString() {
        StringBuilder temp = new StringBuilder();
        if (productCount > 0) {
            for (int i = 0; i < productCount; i++) {
                temp.append("Product ").append(i + 1).append(") [ ").append(productArray[i].toString()).append(" ]\n");
            }
        } else {
            temp.append("There are no products currently\n");
        }
        return temp.toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof ProductList)) {
            return false;
        }

        if (!(super.equals(other))) {
            return false;
        }

        ProductList temp = (ProductList) other;
        boolean equal = false;
        if (this.productCount == temp.getProductCount()) {
            equal = true;
            for (int i = 0; i < this.productCount; i++) {
                if (!this.productArray[i].equals(temp.getProductArray()[i])) {
                    equal = false;
                }
            }
        }
        return equal;
    }

    public int isProductExist(int id) {
        for (int i = 0; i < productCount; i++) {
            if (productArray[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void printCategory(Product.Category category) {
        for (int i = 0; i < productCount; i++) {
            if (category == productArray[i].getCategory()) {
                System.out.println("[ " + productArray[i].toString() + " ]");
            }
        }
    }
}
