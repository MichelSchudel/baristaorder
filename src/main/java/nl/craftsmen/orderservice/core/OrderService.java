package nl.craftsmen.orderservice.core;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderService {

    private final PriceProvider priceProvider;

    private final OrdersProvider ordersProvider;

    private final DiscountCalculator discountCalculator = new DiscountCalculator();

    public Order saveNewOrder(Order order) {
        double price = priceProvider.getPrice(order.name());
        double discountedPrice = discountCalculator.calculateNewPrice(order, price);
        val orderWithPrice = order.toBuilder().price(discountedPrice).build();
        return ordersProvider.saveOrder(orderWithPrice);
    }

    public Order getOrder(String name) {
        return ordersProvider.findOrderByName(name);
    }
}
