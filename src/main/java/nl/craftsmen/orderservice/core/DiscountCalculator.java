package nl.craftsmen.orderservice.core;

public class DiscountCalculator {

    double calculateNewPrice(Order order, double price) {
        if (order.customer().equals("Michel")) {
            return price - 1;
        } else {
            return price;
        }
    }

}
