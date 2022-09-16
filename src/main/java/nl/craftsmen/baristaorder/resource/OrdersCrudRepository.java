package nl.craftsmen.baristaorder.resource;

import nl.craftsmen.baristaorder.core.Order;
import nl.craftsmen.baristaorder.core.OrdersRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersCrudRepository extends CrudRepository<OrderEntity, Long>, OrdersRepository {

    default void save(Order order) {
        save(OrderMapper.toEntity(order));
    }
}

class OrderMapper {
    public static OrderEntity toEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName(order.getName());
        orderEntity.setId(order.getId());
        return orderEntity;
    }
}
