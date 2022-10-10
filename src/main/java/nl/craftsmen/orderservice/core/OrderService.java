package nl.craftsmen.orderservice.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderService {

    //for storing and retrieving the orders
    private final OrdersProvider ordersProvider;

    //for getting the price from headquarters
    private final PriceProvider priceProvider;

    //converts doubles to long (cents)
    private final PriceConverter priceConverter = new PriceConverter();

    public Order saveNewOrder(Order order) {

        //get the price from headquarters
        double price = priceProvider.getPrice(order.name());

        //convert the price to cents
        long convertedPrice = priceConverter.convertToCents(price);

        //enrich the order with the price
        var orderWithPrice = order
                .toBuilder()
                .price(convertedPrice)
                .build();

        //save the order to the repository
        return ordersProvider.saveOrder(orderWithPrice);
    }

    public Order getOrder(Long id) {
        return ordersProvider.findOrderById(id);
    }
}
