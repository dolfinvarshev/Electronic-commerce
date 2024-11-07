package AlmogAsiaDolfinVarshev;

public class Buyer extends User{
    private Address address;
    private Cart[] orderHistory;
    private int orderHistoryCount;
    private Cart currentCart;

    public Buyer(String username, String password, Address address) {
        super(username, password);
        this.address = address;
        this.currentCart = new Cart();
        this.orderHistory = new Cart[2];
        this.orderHistoryCount = 0;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) throws Exception {
        this.address = address;
    }

    public Cart getCurrentCart() {
        return currentCart;
    }

    public Cart[] getOrderHistory() {
        return orderHistory;
    }

    public int getOrderHistoryCount() {
        return orderHistoryCount;
    }

    public String toString() {
        return super.toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Buyer)) {
            return false;
        }

        if (!(super.equals(other))) {
            return false;
        }

        Buyer temp = (Buyer) other;
        boolean equal = false;
        if (this.address.equals(temp.getAddress())
                && this.currentCart.equals(temp.getCurrentCart())
                && this.orderHistoryCount == temp.getOrderHistoryCount()) {
            equal = true;
            for (int i = 0; i < this.orderHistoryCount; i++) {
                if (!this.orderHistory[i].equals(temp.getOrderHistory()[i])) {
                    equal = false;
                }
            }
        }
        return equal;
    }

    public void replaceCurrentCart(int cartNumber) {
        if (cartNumber < 1 || cartNumber > orderHistoryCount) {
            System.out.println("Cart doesn't exist");
            return;
        }
        currentCart = new Cart(orderHistory[cartNumber - 1]);
        currentCart.setDate(null);
    }

    public void printOrderHistory() {
        if (orderHistoryCount > 0) {
            System.out.println("Order History:");
            for (int j = 0; j < orderHistoryCount; j++) {
                System.out.println("Cart " + (j + 1) + ":\n" + orderHistory[j].toString());
            }
        } else {
            System.out.println("Order history is empty");
        }
    }

    public void printCurrentCart() {
        if (currentCart.getProductList().getProductCount() > 0) {
            System.out.println("Current cart:\n" + currentCart.toString());
        } else {
            System.out.println("Current cart is empty\n");
        }
    }

    public void printAddress() {
        System.out.println("Address: " + address.toString());
    }

    public void moveCart() {
        if (orderHistoryCount >= orderHistory.length) {
            Cart[] newArray = new Cart[2 * orderHistory.length];
            for (int i = 0; i < orderHistoryCount; i++) {
                newArray[i] = orderHistory[i];
            }
            this.orderHistory = newArray;
        }
        currentCart.setPurchaseTime();
        orderHistory[orderHistoryCount] = currentCart;
        orderHistoryCount += 1;
        clearCart();
    }

    public void clearCart() {
        currentCart = new Cart();
    }

}
