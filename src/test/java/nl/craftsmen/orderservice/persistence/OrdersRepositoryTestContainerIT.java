package nl.craftsmen.orderservice.persistence;


import nl.craftsmen.orderservice.core.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//You need this because otherwise the postgres DB gets replaced by an H2 database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("postgres-container-test")
class OrdersRepositoryTestContainerIT {

    @Autowired
    private OrdersRepository ordersCrudRepository;

    @Test
    void insert_order() {
        Order order = Order
                .builder()
                .name("espresso")
                .price(200L)
                .customer("Michel")
                .build();

        var persistedOrder = ordersCrudRepository.saveOrder(order);
        Order storedOrder = ordersCrudRepository.findOrderById(persistedOrder.id());

        assertThat(storedOrder).isNotNull();
        assertThat(storedOrder.id()).isNotNull();
        assertThat(storedOrder.name()).isEqualTo("espresso");
        assertThat(storedOrder.price()).isEqualTo(200);
        assertThat(storedOrder.customer()).isEqualTo("Michel");
    }
}
