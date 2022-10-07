package nl.craftsmen.orderservice.core;

public class PriceConverter {

    long convertToCents(double price) {
        return Math.round(price * 100);
    }

}
