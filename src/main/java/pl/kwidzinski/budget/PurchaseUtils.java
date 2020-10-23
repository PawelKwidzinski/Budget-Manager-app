package pl.kwidzinski.budget;

import java.util.*;
import java.util.stream.Collectors;

class PurchaseUtils {

    protected List<Product> productsListByType(List<Product> products, TypePurchase typePurchase) {
        return products.stream()
                .filter(product -> typePurchase.equals(product.getTypePurchase()))
                .collect(Collectors.toList());
    }

    void showProductsAndPriceByType(List<Product> products, TypePurchase typePurchase) {

        List<Product> listByType = productsListByType(products, typePurchase);

        if (listByType.isEmpty()) {
            System.out.println("\nPurchase list is empty!\n");
        } else {
            String type = getString(typePurchase);
            System.out.printf("\n%s:\n", type);
            showProductsAndPrice(listByType);
        }
    }

    String getString(TypePurchase typePurchase) {
        String toUpperCase = typePurchase.name().substring(0, 1);
        String toLowerCase = typePurchase.name().substring(1).toLowerCase();
        return toUpperCase + toLowerCase;
    }

    void showProductsAndPrice(List<Product> productList) {
        double total = 0;

        for (Product product : productList) {
            System.out.println(product.getName() + " $" + product.getPrice());
            total += product.getPrice();
        }
        if (total > 0) {
            System.out.printf("Total sum: $%.2f\n", total);
        }
        System.out.println();
    }

    void sortAllPurchaseByPrice(List<Product> products) {
        products.sort((o1, o2) -> {
            if (o1.getPrice() > o2.getPrice()) {
                return -1;
            } else if (o1.getPrice() < o2.getPrice()) {
                return 1;
            }
            return 0;
        });
    }

    Map<String, Double> sortMapByValue(Map<String, Double> unsortedMap) {
        List<Map.Entry<String, Double>> list = new LinkedList<>(unsortedMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
       
        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public void printMap(Map<String, Double> map) {
        double total = 0;
        System.out.println("\nTypes:");
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            System.out.printf("%s - $%.2f\n", entry.getKey(), entry.getValue());
            total += entry.getValue();
        }
        if (total >= 0) {
            System.out.printf("Total sum: $%.2f\n", total);
        } else {
            System.err.println("Error, prices must not be negative!");
        }
        System.out.println();
    }
}
