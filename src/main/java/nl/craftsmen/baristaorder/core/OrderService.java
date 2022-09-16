package nl.craftsmen.baristaorder.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    public void saveNewOrder(Order order) {
        ordersRepository.save(order);

    }
}
