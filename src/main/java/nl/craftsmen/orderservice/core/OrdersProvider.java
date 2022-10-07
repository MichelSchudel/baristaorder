package nl.craftsmen.orderservice.core;

public interface OrdersProvider {

    Order saveOrder(Order order);

    Order findOrderById(Long id);
}
