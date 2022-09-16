package nl.craftsmen.baristaorder.resource.db;

import nl.craftsmen.baristaorder.core.Order;
import nl.craftsmen.baristaorder.core.OrdersProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<OrderEntity, Long>, OrdersProvider {

    default Order saveOrder(Order order) {
        return OrderMapper.fromEntity(save(OrderMapper.toEntity(order)));
    }

    OrderEntity findByName(String name);

    default Order findOrderByName(String name) {
        return OrderMapper.fromEntity(findByName(name));
    }
}
