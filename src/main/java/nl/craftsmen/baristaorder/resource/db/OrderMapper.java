package nl.craftsmen.baristaorder.resource.db;

import nl.craftsmen.baristaorder.core.Order;

public class OrderMapper {

    public static OrderEntity toEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName(order.getName());
        orderEntity.setPrice(order.getPrice());
        orderEntity.setId(order.getId());
        orderEntity.setCustomer(order.getCustomer());
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
