package pl.kwidzinski.budget;

import java.util.*;

public class Purchase {
    private String pathToDirectory = "C:\\NewGitProjects\\Budget-manager-app\\src\\main\\resources\\purchases";
    private List<Product> products = new ArrayList<>();
    private Map<String, Double> purchaseMap = new HashMap<>();
    private PurchaseUtils purchaseUtils = new PurchaseUtils();
    private Save save = new Save();
    private Load load = new Load();
    private Scanner input;
    private double balance = 0;

    public void showAction() {
        input = new Scanner(System.in);
        String numOfAction;
        do {
            System.out.println("Choose your action:\n" +
                    "1) Add income\n" +
                    "2) Add purchase\n" +
                    "3) Show list of purchases\n" +
                    "4) Balance\n" +
                    "5) Save\n" +
                    "6) Load\n" +
                    "7) Analyze (Sort)\n" +
                    "0) Exit");
            numOfAction = input.next();
            System.out.println();

            switch (numOfAction) {
                case "1":
                    income();
                    break;
                case "2":
                    addTypePurchase();
                    break;
                case "3":
                    showListTypePurchase();
                    break;
                case "4":
                    balance();
                    break;
                case "5":
                    save.saveCategory(pathToDirectory, products);
                    save.saveBalance(pathToDirectory, balance);
                    break;
                case "6":
                    load.loadCategory(pathToDirectory, products);
                    balance = load.loadBalance(pathToDirectory);
                    break;
                case "7":
                    showSortOptions();
                    break;

            }
        } while (!numOfAction.equals("0"));
        System.out.print("Bye!");
    }

    private void addTypePurchase() {
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

            switch (numOfType) {
                case "1":
                    purchase(TypePurchase.FOOD);
                    break;
                case "2":
                    purchase(TypePurchase.CLOTHES);
                    break;
                case "3":
                    purchase(TypePurchase.ENTERTAINMENT);
                    break;
                case "4":
                    purchase(TypePurchase.OTHER);
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

            switch (numOfType) {
                case "1":
                    purchaseUtils.showProductsAndPriceByType(products, TypePurchase.FOOD);
                    break;
                case "2":
                    purchaseUtils.showProductsAndPriceByType(products, TypePurchase.CLOTHES);
                    break;
                case "3":
                    purchaseUtils.showProductsAndPriceByType(products, TypePurchase.ENTERTAINMENT);
                    break;
                case "4":
                    purchaseUtils.showProductsAndPriceByType(products, TypePurchase.OTHER);
                    break;
                case "5":
                    if (products.isEmpty()) {
                        System.out.println("\nPurchase list is empty!\n");
                    } else {
                        System.out.println("All:");
                        purchaseUtils.showProductsAndPrice(products);
                    }
                    break;
            }
        } while (!numOfType.equals("6"));
        System.out.println();
    }

    private void showSortOptions() {
        input = new Scanner(System.in);
        String numOfType;

        do {
            System.out.println("How do you want to sort?\n" +
                    "1) Sort all purchases\n" +
                    "2) Sort by type\n" +
                    "3) Sort certain type\n" +
                    "4) Back");

            numOfType = input.nextLine();
            switch (numOfType) {
                case "1":
                    if (products.isEmpty()) {
                        System.out.println("\nPurchase list is empty!\n");
                    } else {
                        purchaseUtils.sortAllPurchaseByPrice(products);
                        System.out.println("All:");
                        purchaseUtils.showProductsAndPrice(products);
                    }
                    break;
                case "2":
                    putTypePurchaseWithTotalPrice(TypePurchase.FOOD, products);
                    putTypePurchaseWithTotalPrice(TypePurchase.CLOTHES, products);
                    putTypePurchaseWithTotalPrice(TypePurchase.ENTERTAINMENT, products);
                    putTypePurchaseWithTotalPrice(TypePurchase.OTHER, products);
                    Map<String, Double> sortedMap = purchaseUtils.sortMapByValue(purchaseMap);
                    purchaseUtils.printMap(sortedMap);
                    break;
                case "3":
                    sortByCertainType();
                    break;
            }
        } while (!numOfType.equals("4"));
        System.out.println();
    }

    private void putTypePurchaseWithTotalPrice(TypePurchase typePurchase, List<Product> products) {
        String type = purchaseUtils.getString(typePurchase);

        List<Product> productList = purchaseUtils.productsListByType(products, typePurchase);
        double total = 0;
        for (Product product : productList) {
            total += product.getPrice();
        }
        purchaseMap.put(type, total);
    }

    private void sortByCertainType() {
        input = new Scanner(System.in);
        String numOfType;

        boolean work = true;
        while (work) {
            System.out.println("\nChoose the type of purchase\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other");

            numOfType = input.nextLine();

            switch (numOfType) {
                case "1":
                    purchaseUtils.showProductsAndPriceByType(products, TypePurchase.FOOD);
                    work = false;
                    break;
                case "2":
                    purchaseUtils.showProductsAndPriceByType(products, TypePurchase.CLOTHES);
                    work = false;
                    break;
                case "3":
                    purchaseUtils.showProductsAndPriceByType(products, TypePurchase.ENTERTAINMENT);
                    work = false;
                    break;
                case "4":
                    purchaseUtils.showProductsAndPriceByType(products, TypePurchase.OTHER);
                    work = false;
                    break;
            }
        }
    }

    private void purchase(TypePurchase typePurchase) {
        input = new Scanner(System.in);
        String product;
        double price;

        System.out.print("\nEnter purchase name:\n");
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
