package coso;

import com.github.javafaker.Faker;
import market.Customer;
import market.Order;
import market.Product;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        String[] cat = {"book", "boys", "baby"};

        Supplier<Product> catalogoSupplier = () -> {
            return new Product(faker.name().title(), cat[rnd.nextInt(0, 3)], rnd.nextDouble(1, 100));
        };
        Supplier<Customer> clientiSupplier = () -> {
            return new Customer(faker.name().firstName(), rnd.nextInt(0, 6));
        };

        List<Product> catalogo = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            catalogo.add(catalogoSupplier.get());
        }

        List<Customer> clienti = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            clienti.add(clientiSupplier.get());
        }


        Supplier<List<Product>> cart = () -> {
            List<Product> cartEnd = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                cartEnd.add(catalogo.get(rnd.nextInt(0, 19)));
            }
            return cartEnd;
        };


        Supplier<Order> ordiniSupplier = () -> {
            return new Order(date, date1, cart.get(), clienti.get(rnd.nextInt(0, 9)));
        };

        List<Order> ordini = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ordini.add(ordiniSupplier.get());
        }


        System.out.println("**************************************esercizio 1***************************************");

        Map<String, List<Order>> ordiniInordine = ordini.stream().collect(Collectors.groupingBy(Order::getCustomerName));
        ordiniInordine.forEach((s, orders) -> System.out.println(s + " " + orders));
        System.out.println("**************************************esercizio 2***************************************");
        Map<String, Double> utili = ordini.stream().collect(Collectors.groupingBy(Order::getCustomerName, Collectors.summingDouble(Order -> Order.getProduct().stream().mapToDouble(Product::getPrice).sum() * 100 / 100)));
        utili.forEach((client, total) -> System.out.println("cliente : " + client + " totale : " + total));
        System.out.println("**************************************esercizio 3***************************************");

        Map<String, Double> piuCostosi = catalogo.stream().sorted(Comparator.comparing(Product::getPrice, Comparator.reverseOrder())).limit(5).collect(Collectors.groupingBy(Product::getName, Collectors.averagingDouble(Product::getPrice)));
        piuCostosi.forEach((client, total) -> System.out.println(client + " costo : " + total));


        System.out.println("**************************************esercizio 4***************************************");
        double mediaUtili = ordini.stream().mapToDouble(Order::totaleOrdine).average().orElse(0.0);
        System.out.println(mediaUtili);
        System.out.println("**************************************esercizio 5***************************************");

        double utiliBooks = catalogo.stream().filter(Product -> Objects.equals(Product.getCategory(), "book")).mapToDouble(Product::getPrice).sum();
        double utiliBaby = catalogo.stream().filter(Product -> Objects.equals(Product.getCategory(), "baby")).mapToDouble(Product::getPrice).sum();
        double utiliBoys = catalogo.stream().filter(Product -> Objects.equals(Product.getCategory(), "boys")).mapToDouble(Product::getPrice).sum();

        System.out.println("libri : " + utiliBooks);
        System.out.println("baby : " + utiliBaby);
        System.out.println("boys : " + utiliBoys);

        System.out.println("**************************************esercizio 6***************************************");

        File file = new File("src/output.txt");

        StringBuilder catalogoCompleto = new StringBuilder();
        for (int i = 0; i < catalogo.size(); i++) {
            catalogoCompleto.append(catalogo.get(i).toString()).append(System.getProperty("line.separator"));
        }
        try {
            FileUtils.writeStringToFile(file, catalogoCompleto + System.lineSeparator(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("**************************************esercizio 7***************************************");

        try {
            String[] contenuto = FileUtils.readFileToString(file, StandardCharsets.UTF_8).split("/n");
            List<Product> nuovoContenuto = new ArrayList<>();
//            for (int i = 0; i < contenuto.length; i++) {
//                String[] txtToProd = contenuto[i].split("'");
//                String[] priceInArr = txtToProd[8].split("=");
//                String[] priceInArr2 = priceInArr[1].split(" ");
//                double priceInDouble = Double.parseDouble(priceInArr2[0]);
//                System.out.println(priceInArr);
//                nuovoContenuto.add(new Product(txtToProd[4], txtToProd[6], rnd.nextDouble()));
//            }

            System.out.println(nuovoContenuto);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


}
