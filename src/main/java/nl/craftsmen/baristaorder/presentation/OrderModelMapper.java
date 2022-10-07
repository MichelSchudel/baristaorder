package nl.craftsmen.baristaorder.presentation;

import nl.craftsmen.baristaorder.core.Order;

class OrderModelMapper {

    Order toOrder(OrderRequestModel orderRequestModel) {
        return Order.builder()
                .name(orderRequestModel.name())
                .customer(orderRequestModel.customer()).
                build();
    }

    OrderResponseModel toOrderResponseModel(Order order) {
        return OrderResponseModel.builder()
                .price(order.price())
                .id(order.id())
                .name(order.name())
                .customer(order.customer())
                .build();
    }
}
