package nl.craftsmen.baristaorder.resource.db;

import nl.craftsmen.baristaorder.core.Order;

public class OrderMapper {

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
