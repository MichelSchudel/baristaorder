package nl.craftsmen.baristaorder.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    void order_service_should_store_order() {
        when(priceProvider.getPrice(any())).thenReturn(2.0);
        Order order = Order.builder()
                .price(2.5)
                .customer("Michel")
                .name("espresso")
                .build();

        orderService.saveNewOrder(order);

        verify(ordersProvider).saveOrder(any());
    }
}
