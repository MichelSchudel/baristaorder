package nl.craftsmen.baristaorder.resource.db;


import nl.craftsmen.baristaorder.core.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("h2-test")
class OrdersCrudRepositoryH2IT {

    @Autowired
    private OrdersRepository ordersCrudRepository;

    @Test
    void insert_order() {
        Order order = Order
                .builder()
                .name("espresso")
                .price(2.5)
                .customer("Michel")
                .build();

        ordersCrudRepository.saveOrder(order);
        Order storedOrder = ordersCrudRepository.findOrderByName("espresso");

        assertThat(storedOrder).isNotNull();
        assertThat(storedOrder.id()).isNotNull();
        assertThat(storedOrder.name()).isEqualTo("espresso");
        assertThat(storedOrder.price()).isEqualTo(2.5);
        assertThat(storedOrder.customer()).isEqualTo("Michel");
    }
}
