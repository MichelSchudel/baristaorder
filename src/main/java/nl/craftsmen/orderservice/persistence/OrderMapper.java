package nl.craftsmen.orderservice.persistence;

import lombok.NoArgsConstructor;
import nl.craftsmen.orderservice.core.Order;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class OrderMapper {

    public static OrderEntity toEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName(order.name());
        orderEntity.setPrice(order.price());
        orderEntity.setId(order.id());
        orderEntity.setCustomer(order.customer());
        return orderEntity;
    }

    public static Order fromEntity(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .name(orderEntity.getName())
                .price(orderEntity.getPrice())
                .customer(orderEntity.getCustomer())
                .build();
    }
}
