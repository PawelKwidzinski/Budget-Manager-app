package pl.kwidzinski.budget;

import java.io.*;
import java.util.*;

public class Load {
    double loadBalance(String directoryPath) {
        File file = new File(directoryPath + "\\balance.txt");
        double balance = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                balance = Double.parseDouble(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found!\n");
        }
        return balance;
    }

    void loadCategory(String directoryPath, List<Product> categoryList) {
        File otherFile = new File(directoryPath + "\\products.txt");
        try (Scanner scanner = new Scanner(otherFile)) {
            while (scanner.hasNext()) {
                categoryList.add(new Product(scanner.nextLine(), Double.parseDouble(scanner.nextLine()), TypePurchase.valueOf(scanner.nextLine())));
            }
            System.out.println("Purchases were loaded!\n");
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found!\n");
        }
    }
}
