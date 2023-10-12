package market;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;


public class Order {
    Random rnd = new Random();
    private long id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Product> product;
    private Customer customer;

    public Order(LocalDate orderDate, LocalDate deliveryDate, List<Product> product, Customer customer) {
        this.id = rnd.nextLong(0, 99);
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.product = product;
        this.customer = customer;
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCustTier(Customer customer) {
        return customer.getTier();
    }

    public String getCustomerName() {
        return getCustomer().getName();
    }


    //to string


    @Override
    public String toString() {
        return "Order{id=" + id +
                ", orderDate=" + orderDate +
                ", cart=" + product +
                "," + customer +
                '}';
    }
}
