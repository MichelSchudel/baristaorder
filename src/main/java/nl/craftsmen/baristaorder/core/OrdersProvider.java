package nl.craftsmen.baristaorder.core;

public interface OrdersProvider {
    Order saveOrder(Order order);

    Order findOrderByName(String name);
}
