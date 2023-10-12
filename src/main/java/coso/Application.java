package coso;

import com.github.javafaker.Faker;
import market.Customer;
import market.Order;
import market.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {


        LocalDate primoFeb = LocalDate.parse("2023-01-02", DateTimeFormatter.ofPattern("yyyy-dd-MM"));
        LocalDate primoApr = LocalDate.parse("2023-01-04", DateTimeFormatter.ofPattern("yyyy-dd-MM"));
        LocalDate date = LocalDate.parse("2023-21-03", DateTimeFormatter.ofPattern("yyyy-dd-MM"));
        LocalDate date1 = LocalDate.parse("2023-18-02", DateTimeFormatter.ofPattern("yyyy-dd-MM"));
        LocalDate date2 = LocalDate.parse("2023-05-01", DateTimeFormatter.ofPattern("yyyy-dd-MM"));
        LocalDate date3 = LocalDate.parse("2023-07-08", DateTimeFormatter.ofPattern("yyyy-dd-MM"));
//        List<Product> catalogo = new ArrayList<>();
//        Product prodotto = new Product("libro0", "book", 4.99);
//        catalogo.add(new Product("libro1", "book", 152.45));
//        catalogo.add(new Product("libro2", "book", 50.32));
//        catalogo.add(new Product("libro3", "book", 120.99));
//        catalogo.add(new Product("libro4", "book", 200.99));
//        catalogo.add(new Product("coso0", "baby", 4.99));
//        catalogo.add(new Product("coso1", "baby", 152.45));
//        catalogo.add(new Product("coso2", "baby", 50.32));
//        catalogo.add(new Product("coso3", "baby", 120.99));
//        catalogo.add(new Product("coso4", "baby", 200.99));
//        catalogo.add(new Product("boh0", "boys", 4.99));
//        catalogo.add(new Product("boh1", "boys", 152.45));
//        catalogo.add(new Product("boh2", "boys", 50.32));
//        catalogo.add(new Product("boh3", "boys", 120.99));
//        catalogo.add(new Product("boh4", "boys", 200.99));

//        List<Product> cart = new ArrayList<>();
//        List<Product> cart2 = new ArrayList<>();
//
//        cart.add(catalogo.get(0));
//        cart.add(catalogo.get(3));
//        cart2.add(catalogo.get(10));
//        cart2.add(catalogo.get(12));
//        cart2.add(catalogo.get(6));
//        cart2.add(catalogo.get(2));
//
//        List<Customer> clienti = new ArrayList<>();
//        clienti.add(new Customer("coso", 2));
//        clienti.add(new Customer("pinko", 1));
//        clienti.add(new Customer("pallino", 2));
//        clienti.add(new Customer("francescano", 3));
//
//
//        List<Order> ordini = new ArrayList<>();
//        ordini.add(new Order(date1, date, cart, clienti.get(3)));
//        ordini.add(new Order(date2, date3, cart, clienti.get(1)));
//        ordini.add(new Order(date3, date2, cart, clienti.get(2)));
//        ordini.add(new Order(date1, date, cart, clienti.get(3)));
//        ordini.add(new Order(date, date2, cart2, clienti.get(0)));


        Faker faker = new Faker(Locale.ITALIAN);
        Random rnd = new Random();

        Supplier<Product> catalogoSupplier = () -> {
            return new Product(faker.name().title(), "book", rnd.nextDouble(1, 100));
        };
        Supplier<Customer> clientiSupplier = () -> {
            return new Customer(faker.name().firstName(), rnd.nextInt(1, 5));
        };

        List<Product> catalogo = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            catalogo.add(catalogoSupplier.get());
        }

        List<Customer> clienti = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            clienti.add(clientiSupplier.get());
        }


        List<Product> cart1 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cart1.add(catalogo.get(rnd.nextInt(0, 19)));
        }
        List<Product> cart2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cart2.add(catalogo.get(rnd.nextInt(0, 19)));
        }
        List<Product> cart3 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cart3.add(catalogo.get(rnd.nextInt(0, 19)));
        }


        Supplier<Order> ordiniSupplier = () -> {
            return new Order(date, date1, cart1, clienti.get(rnd.nextInt(0, 9)));
        };

        List<Order> ordini = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ordini.add(ordiniSupplier.get());
        }


        System.out.println("**************************************esercizio 1***************************************");

        Map<String, List<Order>> ordiniInordine = ordini.stream().collect(Collectors.groupingBy(Order::getCustomerName));
        ordiniInordine.forEach((s, orders) -> System.out.println(s + " " + orders));
        System.out.println("**************************************esercizio 2***************************************");

        List<Double> ordiniConPrezziUnici = new ArrayList<>();

        Map<String, Double> utili = ordini.stream().collect(Collectors.groupingBy(Order::getCustomerName, Collectors.averagingDouble(Order::totaleOrdine)));

        utili.forEach((client, total) -> System.out.println(client + " " + total));
        System.out.println("**************************************esercizio 3***************************************");

    }
}