// Almog Asia - id = 325553543
// and Dolfin Varhsev - id = 315853101
// Students of - Pini

package AlmogAsiaDolfinVarshev;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Management manager = new Management();
        int option = 1;
        while (option != 0) {
            boolean isValid = false;
            do {
                try {
                    System.out.println("Please choose an option: ");
                    System.out.println("1. Add a seller\n" +
                            "2. Add a buyer\n" +
                            "3. Add a product to a seller\n" +
                            "4. Buy a product\n" +
                            "5. Pay for a cart\n" +
                            "6. Show all buyers info\n" +
                            "7. Show all sellers info\n" +
                            "8. Show all products of a category\n" +
                            "9. Replace current cart\n" +
                            "0. Exit\n");
                    option = scn.nextInt();
                    isValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, must be integer");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                scn.nextLine(); //clean buffer
            } while (!isValid);

            switch (option) {
                case 1:
                    handleAddSeller(manager, scn);
                    break;
                case 2:
                    handleAddBuyer(manager, scn);
                    break;
                case 3:
                    handleAddProduct(manager, scn);
                    break;
                case 4:
                    handleBuyProduct(manager, scn);
                    break;
                case 5:
                    handlePayForCart(manager, scn);
                    break;
                case 6:
                    handlePrintBuyers(manager);
                    break;
                case 7:
                    handlePrintSellers(manager);
                    break;
                case 8:
                    handlePrintCategory(manager, scn);
                    break;
                case 9:
                    handleChangeCart(manager, scn);
                    break;
                case 0:
                    System.out.println("Thank you!");
                    scn.close();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            System.out.println();
        }
    }

    //########################################### Case 1 ###########################################################\\
    public static void handleAddSeller(Management manager, Scanner scn) {
        boolean isValid = false;
        String addUsername;
        do {
            System.out.println("Enter new seller username: ");
            addUsername = scn.nextLine();
            int sellerIndex = manager.isSellerExist(addUsername);
            if (sellerIndex != -1) {
                System.out.println("Seller username already taken");
            } else {
                isValid = true;
            }
        } while (!isValid);
        System.out.println("Enter new seller password: ");
        String addPassword = scn.nextLine();
        manager.addSeller(addUsername, addPassword);
        System.out.println("Successfully added new seller");
    }

    //########################################### Case 2 ###########################################################\\
    public static void handleAddBuyer(Management manager, Scanner scn) {
        boolean isValid = false;
        String addUsername;
        do {
            System.out.println("Enter new buyer username: ");
            addUsername = scn.nextLine();
            int buyerIndex = manager.isBuyerExist(addUsername);
            if (buyerIndex != -1) {
                System.out.println("Buyer username already taken");
            } else {
                isValid = true;
            }

        } while (!isValid);
        System.out.println("Enter new buyer password: ");
        String addPassword = scn.nextLine();
        System.out.println("Enter new buyer country: ");
        String country = scn.nextLine();
        System.out.println("Enter new buyer city: ");
        String city = scn.nextLine();
        System.out.println("Enter new buyer street: ");
        String street = scn.nextLine();
        isValid = false;
        int houseNumber = 0;
        do {
            try {
                System.out.println("Enter new buyer house number: ");
                houseNumber = scn.nextInt();
                if (houseNumber <= 0) {
                    System.out.println("Invalid house number");
                } else {
                    isValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, must be integer");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            scn.nextLine();
        } while (!isValid);
        Address addAddress = new Address(country, city, street, houseNumber);
        manager.addBuyer(addUsername, addPassword, addAddress);
        System.out.println("Successfully added new buyer");
    }

    //########################################### Case 3 ###########################################################\\
    public static void handleAddProduct(Management manager, Scanner scn) {
        boolean isValid = false;
        if (!manager.hasAnySellers()) {
            System.out.println("There are no sellers currently");
            return;
        }
        System.out.print("Choose a seller: ");
        manager.printSellerNames();
        String sellerName = scn.nextLine();
        int sellerIndex = manager.isSellerExist(sellerName);
        if (sellerIndex == -1) {
            System.out.println("Seller " + sellerName + " doesn't exist");
            return;
        }
        System.out.println("Name of the product: ");
        String productName = scn.nextLine();
        double productPrice = 0;
        do {
            try {
                System.out.println("Price of the product: ");
                productPrice = scn.nextDouble();
                if (productPrice <= 0) {
                    System.out.println("Price must be greater than 0");
                } else {
                    isValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, must be a number");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            scn.nextLine();
        } while (!isValid);

        isValid = false;
        int res = 0;
        do {
            try {
                System.out.println("Category of the product:\n1 - Kids\n2 - Electricity\n3 - Office\n4 - Clothing");
                res = scn.nextInt();
                if (res > 4 || res < 1) {
                    System.out.println("Invalid Category");
                } else {
                    isValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, must be integer");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            scn.nextLine();
        } while (!isValid);
        Product.Category category = null;
        switch (res) {
            case 1:
                category = Product.Category.Kids;
                break;
            case 2:
                category = Product.Category.Electricity;
                break;
            case 3:
                category = Product.Category.Office;
                break;
            case 4:
                category = Product.Category.Clothing;
                break;
        }
        isValid = false;
        do {
            try {
                System.out.println("Does the product need special packaging?\n0 - No\n1 - Yes");
                res = scn.nextInt();
                if (res != 1 && res != 0) {
                    System.out.println("Invalid option");
                } else {
                    isValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, must be integer");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            scn.nextLine();
        } while (!isValid);
        boolean specialPackage = (res != 0);
        isValid = false;
        double packagingPrice = 0;
        if (specialPackage) {
            do {
                try {
                    System.out.println("Enter the special packaging price: ");
                    packagingPrice = scn.nextDouble();
                    if (packagingPrice < 0) {
                        System.out.println("Packaging price cannot be negative.");

                    } else {
                        isValid = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, must be a number");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                scn.nextLine();
            } while (!isValid);
        }
        manager.addProduct(sellerIndex, productName, productPrice, category, specialPackage, packagingPrice);
        System.out.println("Successfully added");
    }

    //########################################### Case 4 ###########################################################\\
    public static void handleBuyProduct(Management manager, Scanner scn) {
        boolean hasSellers = manager.hasAnySellers();
        boolean hasBuyers = manager.hasAnyBuyers();
        if (!hasSellers || !hasBuyers) {
            if (!hasSellers) {
                System.out.println("There are no sellers currently");
            }
            if (!hasBuyers) {
                System.out.println("There are no buyers currently");
            }
            return;
        }

        System.out.print("Choose a buyer: ");
        manager.printBuyerNames();
        String buyerName = scn.nextLine();
        int buyerIndex = manager.isBuyerExist(buyerName);
        if (buyerIndex == -1) {
            System.out.println("Buyer " + buyerName + " doesn't exist");
            return;
        }
        System.out.print("Choose a seller: ");
        manager.printSellerNames();
        String sellerName = scn.nextLine();
        int sellerIndex = manager.isSellerExist(sellerName);
        if (sellerIndex == -1) {
            System.out.println("Seller " + sellerName + " doesn't exist");
            return;
        }
        if (!manager.hasAnyProducts(sellerIndex)) {
            System.out.println("This seller does not have any products");
            return;
        }
        manager.printProducts(sellerIndex);
        boolean isValid = false;
        int id = 0;
        do {
            try {
                System.out.println("Choose a product (by id): ");
                id = scn.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, must be integer");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            scn.nextLine();
        } while (!isValid);
        int productIndex = manager.isProductExist(id, sellerIndex);
        if (productIndex == -1) {
            System.out.println("Product id{" + id + "} doesn't exist");
            return;
        }
        manager.buyProduct(buyerIndex, sellerIndex, productIndex);
        System.out.println("Successfully purchased");
    }

    //########################################### Case 5 ###########################################################\\
    public static void handlePayForCart(Management manager, Scanner scn) {
        if (!manager.hasAnyBuyers()) {
            System.out.println("There are no buyers currently");
            return;
        }
        System.out.print("Choose a buyer: ");
        manager.printBuyerNames();
        String buyerName = scn.nextLine();
        int buyerIndex = manager.isBuyerExist(buyerName);
        if (buyerIndex != -1) {
            try {
                manager.payForCart(buyerIndex);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Buyer doesn't exist");
        }
    }

    //########################################### Case 6 ###########################################################\\
    public static void handlePrintBuyers(Management manager) {
        manager.printBuyerInfo();
    }

    //########################################### Case 7 ###########################################################\\
    public static void handlePrintSellers(Management manager) {
        manager.printSellerInfo();
    }

    //########################################### Case 8 ###########################################################\\
    public static void handlePrintCategory(Management manager, Scanner scn) {
        int res = 0;
        boolean isValid = false;
        do {
            try {
                System.out.println("Category of the product:\n1 - Kids\n2 - Electricity\n3 - Office\n4 - Clothing");
                res = scn.nextInt();
                if (res > 4 || res < 1) {
                    System.out.println("Invalid Category");
                } else {
                    isValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, must be integer");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            scn.nextLine();
        } while (!isValid);
        Product.Category category = null;
        switch (res) {
            case 1:
                category = Product.Category.Kids;
                break;
            case 2:
                category = Product.Category.Electricity;
                break;
            case 3:
                category = Product.Category.Office;
                break;
            case 4:
                category = Product.Category.Clothing;
                break;
        }
        manager.printAllCategory(category);
    }

    //########################################### Case 9 ###########################################################\\
    public static void handleChangeCart(Management manager, Scanner scn) {
        if (!manager.hasAnyBuyers()) {
            System.out.println("There are no buyers currently");
            return;
        }
        System.out.print("Choose a buyer: ");
        manager.printBuyerNames();
        String buyerName = scn.nextLine();
        int buyerIndex = manager.isBuyerExist(buyerName);
        if (buyerIndex == -1) {
            System.out.println("Buyer " + buyerName + " doesn't exist");
            return;
        }
        if (!manager.hasAnyOrderHistory(buyerIndex)) {
            System.out.println("Buyer " + buyerName + "'s order history is empty");
            return;
        }
        boolean isValid = false;
        if (manager.hasCurrentCart(buyerIndex)) {
            int res = 0;
            do {
                try {
                    System.out.println("Are you sure you want to replace the cart?\n0 - No\n1 - Yes");
                    res = scn.nextInt();
                    if (res != 1 && res != 0) {
                        System.out.println("Invalid option");
                    } else {
                        isValid = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, must be integer");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                scn.nextLine();
            } while (!isValid);
            if (res == 0) {
                System.out.println("Okay!");
                return;
            }
        }
        isValid = false;
        int cartNumber = 0;
        do {
            try {
                System.out.println("Which cart from the order history would you like? (Cart Number)");
                manager.printOrderHistory(buyerIndex);
                cartNumber = scn.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, must be integer");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            scn.nextLine();
        } while (!isValid);
        manager.replaceCart(buyerIndex, cartNumber);
    }
}

