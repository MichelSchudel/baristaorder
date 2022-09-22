package nl.craftsmen.baristaorder.core;

import org.springframework.stereotype.Component;

public class DiscountCalculator {

    double calculateNewPrice(Order order, double price) {
        if (order.customer().equals("Michel")) {
            return price - 1;
        } else {
            return price;
        }
    }

}
