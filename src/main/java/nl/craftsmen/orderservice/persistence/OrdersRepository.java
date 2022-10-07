package nl.craftsmen.orderservice.persistence;

import nl.craftsmen.orderservice.core.NotFoundException;
import nl.craftsmen.orderservice.core.Order;
import nl.craftsmen.orderservice.core.OrdersProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<OrderEntity, Long>, OrdersProvider {

    default Order saveOrder(Order order) {
        var orderEntity = OrderMapper.toEntity(order);
        save(orderEntity);
        return OrderMapper.fromEntity(orderEntity);
    }

    default Order findOrderById(Long id) {
        var orderEntity = findById(id);
        return orderEntity.map(OrderMapper::fromEntity)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }

}
