package pl.kwidzinski.budget;

import java.io.*;
import java.util.*;

public class Save {

    void saveBalance(String directoryPath, double balance) {
        File file = new File(directoryPath);
        try (PrintWriter writer = new PrintWriter(file + "\\balance.txt")) {
            writer.println(balance);
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found!\n");
        }
    }

    void saveCategory(String directoryPath, List<Product> productList) {
        File file = new File(directoryPath);

        try (PrintWriter writer = new PrintWriter(file + "\\products.txt")) {
            for (Product product : productList) {
                writer.println(product.getName());
                writer.println(product.getPrice());
                writer.println(product.getTypePurchase());
            }
            System.out.println("Purchases were saved!\n");
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found\n");
        }
    }
}
