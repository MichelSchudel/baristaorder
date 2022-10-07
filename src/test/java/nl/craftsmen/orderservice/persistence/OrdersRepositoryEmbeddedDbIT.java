package nl.craftsmen.orderservice.persistence;


import nl.craftsmen.orderservice.core.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("h2-test")
class OrdersRepositoryEmbeddedDbIT {

    @Autowired
    private OrdersRepository ordersCrudRepository;

    @Test
    void insert_order() {
        var order = Order
                .builder()
                .name("Design Patterns")
                .price(2000)
                .customer("Michel")
                .build();

        var persistedOrder = ordersCrudRepository.saveOrder(order);
        Order storedOrder = ordersCrudRepository.findOrderById(persistedOrder.id());

        assertThat(persistedOrder).isNotNull();
        assertThat(persistedOrder.id()).isNotNull();
        assertThat(persistedOrder.name()).isEqualTo("Design Patterns");
        assertThat(persistedOrder.price()).isEqualTo(2000);
        assertThat(persistedOrder.customer()).isEqualTo("Michel");
    }
}
