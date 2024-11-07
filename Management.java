package AlmogAsiaDolfinVarshev;

import java.util.Comparator;

public class Management {
    private Buyer[] buyerArray;
    private int buyersCount, sellersCount;
    private Seller[] sellerArray;

    public Management() {
        this.sellersCount = 0;
        this.buyersCount = 0;
        this.buyerArray = new Buyer[2];
        this.sellerArray = new Seller[2];
    }

    public void addBuyer(String username, String password, Address address) {
        if (buyersCount >= buyerArray.length) {
            Buyer[] newArray = new Buyer[buyerArray.length * 2];
            for (int i = 0; i < buyerArray.length; i++) {
                newArray[i] = buyerArray[i];
            }
            buyerArray = newArray;
        }
        buyerArray[buyersCount] = new Buyer(username, password, address);
        buyersCount += 1;

        bubbleSort(buyerArray, new CompareBuyerByName());
    }

    public void addSeller(String username, String password) {
        if (sellersCount >= sellerArray.length) {

            Seller[] newArray = new Seller[sellerArray.length * 2];
            for (int i = 0; i < sellerArray.length; i++) {
                newArray[i] = sellerArray[i];
            }
            sellerArray = newArray;
        }

        sellerArray[sellersCount] = new Seller(username, password);
        sellersCount += 1;
    }

    public void addProduct(int sellerIndex, String productName, double productPrice, Product.Category category, boolean specialPackage, double packagingPrice) {

        if (specialPackage) {
            SpecialPackage newProduct = new SpecialPackage(productName, productPrice, category, packagingPrice);
            sellerArray[sellerIndex].getProductList().addProduct(newProduct);

        } else {
            Product newProduct = new Product(productName, productPrice, category);
            sellerArray[sellerIndex].getProductList().addProduct(newProduct);
        }

        bubbleSort(sellerArray, new CompareSellerByProductCount());
    }

    public void buyProduct(int buyerIndex, int sellerIndex, int productIndex) {
        Product myProduct = sellerArray[sellerIndex].getProductList().getProductArray()[productIndex];
        if (myProduct instanceof SpecialPackage) {
            SpecialPackage newProduct = new SpecialPackage((SpecialPackage) myProduct);
            buyerArray[buyerIndex].getCurrentCart().getProductList().addProduct(newProduct);
        } else {
            Product newProduct = new Product(myProduct);
            buyerArray[buyerIndex].getCurrentCart().getProductList().addProduct(newProduct);
        }
    }

    public void payForCart(int buyerIndex) throws Exception {
        double cartPrice = buyerArray[buyerIndex].getCurrentCart().getProductList().getCartPrice();

        if (cartPrice > 0) {
            buyerArray[buyerIndex].moveCart();
            System.out.println("The price of the cart is " + cartPrice + "$");
        } else {
            throw new Exception("Cannot order an empty cart!");
        }
    }

    public void printBuyerInfo() {
        if (buyersCount > 0) {
            System.out.println("Buyers info: ");
            for (int i = 0; i < buyersCount; i++) {
                System.out.println("Buyer " + (i + 1) + ": \n-----------\n" + buyerArray[i].toString());
                buyerArray[i].printAddress();
                buyerArray[i].printCurrentCart();
                buyerArray[i].printOrderHistory();
                System.out.println();
            }
        } else {
            System.out.println("There are no buyers currently");
        }
    }

    public void printSellerInfo() {
        if (sellersCount > 0) {
            System.out.println("Sellers info: ");
            for (int i = 0; i < sellersCount; i++) {
                System.out.println("Seller " + (i + 1) + ": \n-----------\n" + sellerArray[i].toString());
                System.out.println(sellerArray[i].getProductList().toString());
            }
        } else {
            System.out.println("There are no sellers currently");
        }
    }

    public void printAllCategory(Product.Category category) { //change print
        System.out.println("Products of Category " + category + ":");
        for (int i = 0; i < sellersCount; i++) {
            sellerArray[i].getProductList().printCategory(category);
        }
    }

    public void replaceCart(int buyerIndex, int cartNumber) {
        buyerArray[buyerIndex].replaceCurrentCart(cartNumber);
        System.out.println("Successfully replaced");
    }

    public void bubbleSort(Object[] arr, Comparator c) {
        int realLength = 0;
        int i = 0;
        while (i < arr.length && arr[i] != null) {
            realLength++;
            i++;
        }

        for (i = realLength - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (c.compare(arr[j], arr[j + 1]) > 0) {
                    Object temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }

    public int isSellerExist(String sellerName) {
        for (int i = 0; i < sellersCount; i++) {
            if (sellerName.equals(sellerArray[i].getUsername())) {
                return i;
            }
        }
        return -1;
    }

    public int isBuyerExist(String buyerName) {
        for (int i = 0; i < buyersCount; i++) {
            if (buyerName.equals(buyerArray[i].getUsername())) {
                return i;
            }
        }
        return -1;
    }

    public int isProductExist(int id, int sellerIndex) {
        return sellerArray[sellerIndex].getProductList().isProductExist(id);
    }

    public void printBuyerNames() {
        System.out.print("[ ");
        if (buyersCount > 0) {
            System.out.printf(buyerArray[0].getUsername());
        }
        for (int i = 1; i < buyersCount; i++) {
            System.out.printf(", " + buyerArray[i].getUsername());
        }
        System.out.println(" ]");
    }

    public void printSellerNames() {
        System.out.print("[ ");
        if (sellersCount > 0) {
            System.out.printf(sellerArray[0].getUsername());
        }
        for (int i = 1; i < sellersCount; i++) {
            System.out.printf(", " + sellerArray[i].getUsername());
        }
        System.out.println(" ]");
    }

    public void printProducts(int sellerIndex) {
        System.out.println(sellerArray[sellerIndex].getProductList().toString());
    }

    public void printOrderHistory(int buyerIndex) {
        buyerArray[buyerIndex].printOrderHistory();
    }

    public boolean hasAnySellers() {
        return sellersCount > 0;
    }

    public boolean hasAnyBuyers() {
        return buyersCount > 0;
    }

    public boolean hasAnyProducts(int sellerIndex) {
        return sellerArray[sellerIndex].getProductList().getProductCount() > 0;
    }

    public boolean hasAnyOrderHistory(int buyerIndex) {
        return buyerArray[buyerIndex].getOrderHistoryCount() > 0;
    }

    public boolean hasCurrentCart(int buyerIndex) {
        return buyerArray[buyerIndex].getCurrentCart().getProductList().getProductCount() > 0;
    }

    public String toString() {
        return "Number of buyers = " + buyersCount + "\nNumber of sellers = " + sellersCount;
    }

    public boolean equals() {
        return false;
    }
}