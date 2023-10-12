package market;

import java.util.Random;

public class Product {
    Random rnd = new Random();
    private long id;
    private String name;
    private String category;
    private Double price;

    //costruttore
    public Product(String name, String category, double price) {
        this.id = rnd.nextLong(100, 199);
        this.name = name;
        this.category = category;
        this.price = price;
    }

    //getters and setters
    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //to string


    @Override
    public String toString() {
        return "Product{id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }

    // sconto

    public void sconta(double off) {
        this.price = (this.price / 100) * (100 - off);
    }
}
