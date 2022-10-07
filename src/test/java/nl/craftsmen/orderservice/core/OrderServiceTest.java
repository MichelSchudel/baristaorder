package nl.craftsmen.orderservice.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrdersProvider ordersProvider;

    @Mock
    private PriceProvider priceProvider;

    @InjectMocks
    private OrderService orderService;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @Test
    void order_service_should_store_order() {
        when(priceProvider.getPrice(any())).thenReturn(2.0);
        Order order = Order.builder()
                .customer("Pieter")
                .name("Design Patterns")
                .build();

        orderService.saveNewOrder(order);

        verify(ordersProvider).saveOrder(any());
    }

    @Test
    void order_service_should_apply_discount_for_special_customer() {
        when(priceProvider.getPrice(any())).thenReturn(2.0);
        Order order = Order.builder()
                .customer("Michel")
                .name("Design Patterns")
                .build();

        orderService.saveNewOrder(order);

        verify(ordersProvider).saveOrder(orderArgumentCaptor.capture());
        assertThat(orderArgumentCaptor.getValue().price()).isEqualTo(1.0);
    }

}
