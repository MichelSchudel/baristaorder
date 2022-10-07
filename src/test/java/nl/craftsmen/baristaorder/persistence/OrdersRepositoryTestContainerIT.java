package nl.craftsmen.baristaorder.persistence;


import nl.craftsmen.baristaorder.core.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//You need this because otherwise the postgres DB gets replaced by a H2 database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("postgres-container-test")
class OrdersRepositoryTestContainerIT {

    @Autowired
    private OrdersRepository ordersCrudRepository;

    @Test
    void insert_order() {
        Order order = Order
                .builder()
                .name("Design Patterns")
                .price(20)
                .customer("Michel")
                .build();

        ordersCrudRepository.saveOrder(order);
        Order storedOrder = ordersCrudRepository.findOrderByName("Design Patterns");

        assertThat(storedOrder).isNotNull();
        assertThat(storedOrder.id()).isNotNull();
        assertThat(storedOrder.name()).isEqualTo("Design Patterns");
        assertThat(storedOrder.price()).isEqualTo(20);
        assertThat(storedOrder.customer()).isEqualTo("Michel");
    }
}