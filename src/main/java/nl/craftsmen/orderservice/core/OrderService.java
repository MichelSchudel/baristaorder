package nl.craftsmen.orderservice.core;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderService {

    private final PriceProvider priceProvider;

    private final OrdersProvider ordersProvider;

    private final PriceConverter priceConverter = new PriceConverter();

    public Order saveNewOrder(Order order) {
        double price = priceProvider.getPrice(order.name());
        long discountedPrice = priceConverter.convertToCents(price);
        val orderWithPrice = order.toBuilder().price(discountedPrice).build();
        return ordersProvider.saveOrder(orderWithPrice);
    }

    public Order getOrder(Long id) {
        return ordersProvider.findOrderById(id);
    }
}
