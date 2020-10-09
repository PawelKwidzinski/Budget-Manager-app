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
            System.out.println("Choose your action:\n 1) Add income\n 2) Add purchase\n 3) Show list of purchases\n" +
                    " 4) Balance\n 0) Exit");
            numOfAction = input.next();
            System.out.println();

            switch (numOfAction) {
                case "1":
                    income();
                    break;
                case "2":
                    purchase();
                    break;
                case "3":
                    showList();
                    break;
                case "4":
                    balance();
                    break;
            }
        } while (!numOfAction.equals("0"));
        System.out.print("Bye!");
    }

    private void showList() {
        double total = 0;
        if (products.isEmpty()) {
            System.out.println("Purchase list is empty");
        } else {
            for (Product product : products) {
                System.out.println(product.getName() + " $" + product.getPrice());
                total += product.getPrice();
            }
            System.out.printf("Total sum: $%.2f\n", total);
        }
        System.out.println();
    }

    private void purchase() {
        input = new Scanner(System.in);
        String product;
        double price;

        System.out.print("Enter purchase name:\n");
        product = input.nextLine();

        System.out.print("Enter its price:\n");

        price = Double.parseDouble(input.nextLine());

        balance -= price;

        products.add(new Product(product, price));
        System.out.println("Purchase was added!\n");
    }

    private void balance() {
        System.out.printf("Balance: $%.2f\n", balance);
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
