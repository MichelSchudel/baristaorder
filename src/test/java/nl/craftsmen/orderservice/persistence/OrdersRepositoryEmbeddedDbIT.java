package nl.craftsmen.orderservice.persistence;

import nl.craftsmen.orderservice.core.Order;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("h2-test")
class OrdersRepositoryEmbeddedDbIT {

    private Logger logger = LoggerFactory.getLogger(OrdersRepositoryEmbeddedDbIT.class);

    @Autowired
    private OrdersRepository ordersCrudRepository;

    @Test
    void repository_should_store_and_retrieve_order() {

        //build an order to store
        var order = Order.builder()
                .name("espresso")
                .price(200L)
                .customer("Michel")
                .build();

        //save and retrieve order
        var persistedOrder = ordersCrudRepository.saveOrder(order);
        logger.info("persistedOrder" + persistedOrder);
        Order storedOrder = ordersCrudRepository.findOrderById(persistedOrder.id());
        logger.info("storedOrder" + storedOrder);

        //assert some properties
        assertThat(storedOrder).isNotNull();
        assertThat(storedOrder.id()).isNotNull();
        assertThat(storedOrder.name()).isEqualTo("espresso");
        assertThat(storedOrder.price()).isEqualTo(200);
        assertThat(storedOrder.customer()).isEqualTo("Michel");
    }
}
