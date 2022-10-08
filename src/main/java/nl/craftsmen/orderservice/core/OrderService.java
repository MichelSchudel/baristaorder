package nl.craftsmen.orderservice.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderService {

    //for getting the price from headquarters
    private final PriceProvider priceProvider;

    //for storing and retrieving the orders
    private final OrdersProvider ordersProvider;

    //converts doubles to long (cents)
    private final PriceConverter priceConverter = new PriceConverter();

    public Order saveNewOrder(Order order) {

        //get the price from headquarters
        double price = priceProvider.getPrice(order.name());

        //convert it to cents
        long convertedPrice = priceConverter.convertToCents(price);


        var orderWithPrice = order
                .toBuilder()
                .price(convertedPrice)
                .build();

        return ordersProvider.saveOrder(orderWithPrice);
    }

    public Order getOrder(Long id) {
        return ordersProvider.findOrderById(id);
    }
}
