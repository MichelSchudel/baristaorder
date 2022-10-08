package nl.craftsmen.orderservice.gateway.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(PriceRestClient.class)
@AutoConfigureWireMock(port = 0, stubs ="classpath:/stubs")
@Import(PriceRestClientWiremockIT.WiremockConfiguration.class)
class PriceRestClientWiremockIT {

    @Autowired
    private PriceRestClient priceRestClient;

    @Test
    void test() {
        assertThat(priceRestClient.getPrice("espresso")).isEqualTo(20);
    }

    @TestConfiguration
    public static class WiremockConfiguration {

        @Value("${wiremock.server.port}")
        String port;

        @Bean
        @Primary
        public RestTemplateBuilder restTemplateBuilder() {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().rootUri("http://localhost:" + port);
            return restTemplateBuilder;
        }

    }
}
