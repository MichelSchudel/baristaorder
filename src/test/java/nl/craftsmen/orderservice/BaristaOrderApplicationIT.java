package nl.craftsmen.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("h2-test")
class BaristaOrderApplicationIT {

	@Autowired
	private ApplicationContext ctx;

	@Test
	void contextLoads() {
		assertThat(ctx).isNotNull();
	}

}
