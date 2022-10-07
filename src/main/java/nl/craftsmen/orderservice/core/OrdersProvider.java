package nl.craftsmen.orderservice.core;

public interface OrdersProvider {

    Order saveOrder(Order order);

    Order findOrderByName(String name);
}
