package nl.craftsmen.baristaorder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("h2-test")
class BaristaOrderApplicationTests {

	@Test
	void contextLoads() {
	}

}
