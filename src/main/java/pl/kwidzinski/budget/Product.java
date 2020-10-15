package pl.kwidzinski.budget;

public class Product {
    private String name;
    private double price;
    private TypePurchase typePurchase;

    public Product(String name, double price, TypePurchase typePurchase) {
        this.name = name;
        this.price = price;
        this.typePurchase = typePurchase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TypePurchase getTypePurchase() {
        return typePurchase;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", typePurchase=" + typePurchase +
                '}';
    }
}

