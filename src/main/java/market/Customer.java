package market;

import java.util.Random;

public class Customer {
    Random rnd = new Random();
    private Long id;
    private String name;
    private int tier;

    public Customer(String name, int tier) {
        this.id = rnd.nextLong(200, 299);
        this.name = name;
        this.tier = tier;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    //to string

    @Override
    public String toString() {
        return "Customer{id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
