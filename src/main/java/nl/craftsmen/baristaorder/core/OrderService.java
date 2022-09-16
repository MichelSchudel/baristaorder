package nl.craftsmen.baristaorder.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderService {

    private final OrdersProvider ordersProvider;

    private final PriceProvider priceProvider;

    private final DiscountCalculator discountCalculator;

    public Order saveNewOrder(Order order) {
        double price = priceProvider.getPrice(order.getName());
        double discountedPrice = discountCalculator.calculateNewPrice(order, price);
        return ordersProvider.saveOrder(order.toBuilder().price(discountedPrice).build());
    }

    public Order getOrder(String name) {
        return ordersProvider.findOrderByName(name);
    }
}
