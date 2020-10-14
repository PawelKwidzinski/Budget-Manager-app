package pl.kwidzinski.budget;

import java.util.*;

public class Purchase {
    private double balance = 0;
    private List<Product> products = new ArrayList<>();
    private Scanner input;

    public void showAction() {
        input = new Scanner(System.in);
        String numOfAction;
        do {
            System.out.println("Choose your action:\n" +
                    "1) Add income\n" +
                    "2) Add purchase\n" +
                    "3) Show list of purchases\n" +
                    "4) Balance\n" +
                    "0) Exit");
            numOfAction = input.next();
            System.out.println();

            switch (numOfAction) {
                case "1":
                    income();
                    break;
                case "2":
                    showTypePurchase();
                    break;
                case "3":
                    showListTypePurchase();
                    break;
                case "4":
                    balance();
                    break;
            }
        } while (!numOfAction.equals("0"));
        System.out.print("Bye!");
    }

    private void showTypePurchase() {
        input = new Scanner(System.in);
        String numOfType;

        do {
            System.out.println("Choose the type of purchase\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) Back");

            numOfType = input.nextLine();

            TypePurchase[] values = TypePurchase.values();

            switch (numOfType) {
                case "1":
                    TypePurchase food = values[0];
                    purchase(food);
                    break;
                case "2":
                    TypePurchase clothes = values[1];
                    purchase(clothes);
                    break;
                case "3":
                    TypePurchase entertainment = values[2];
                    purchase(entertainment);
                    break;
                case "4":
                    TypePurchase other = values[3];
                    purchase(other);
                    break;
            }
        } while (!numOfType.equals("5"));
        System.out.println();
    }

    private void showListTypePurchase() {
        input = new Scanner(System.in);
        String numOfType;

        do {
            System.out.println("1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) All\n" +
                    "6) Back");

            numOfType = input.nextLine();

            TypePurchase[] values = TypePurchase.values();

            switch (numOfType) {
                case "1":
                    TypePurchase food = values[0];
                    showList(food);
                    break;
                case "2":
                    TypePurchase clothes = values[1];
                    showList(clothes);
                    break;
                case "3":
                    TypePurchase entertainment = values[2];
                    showList(entertainment);
                    break;
                case "4":
                    TypePurchase other = values[3];
                    showList(other);
                    break;
                case "5":
                    showAll();
                    break;
            }
        } while (!numOfType.equals("6"));
        System.out.println();
    }

    private void showAll() {
        double total = 0;
        if (products.isEmpty()) {
            System.out.println("Purchase list is empty!\n");
        } else {
            System.out.println("All:");
            for (Product product : products) {
                System.out.println(product.getName() + " $" + product.getPrice());
                total += product.getPrice();
            }
            System.out.printf("Total sum: $%.2f\n", total);
        }
        System.out.println();
    }

    private void showList(TypePurchase typePurchase) {
        double total = 0;
        String toUpperCase = typePurchase.name().substring(0, 1).toUpperCase();
        String toLowerCase = typePurchase.name().substring(1).toLowerCase();
        System.out.println(toUpperCase + toLowerCase + ":");

        for (Product product : products) {
            if (product.getTypePurchase().name().contains(typePurchase.name())) {
                System.out.println(product.getName() + " $" + product.getPrice());
                total += product.getPrice();
            } else if (!product.getTypePurchase().equals(typePurchase)) {
                System.out.println("Purchase list is empty!\n");
            }
        }
        if (total > 0) {
            System.out.printf("Total sum: $%.2f\n", total);
        }
        System.out.println();
    }

    private void purchase(TypePurchase typePurchase) {
        input = new Scanner(System.in);
        String product;
        double price;

        System.out.print("Enter purchase name:\n");
        product = input.nextLine();

        System.out.print("Enter its price:\n");

        price = Double.parseDouble(input.nextLine());

        balance -= price;

        products.add(new Product(product, price, typePurchase));
        System.out.println("Purchase was added!\n");
    }

    private void balance() {
        System.out.printf("Balance: $%.2f", balance);
        System.out.println();
    }

    private void income() {
        input = new Scanner(System.in);
        double income;
        System.out.println("Enter your income:");
        income = Double.parseDouble(input.nextLine());
        if (income > 0) {
            balance += income;
            System.out.println("Income was added!\n");
        } else {
            balance = 0;
        }
    }
}
