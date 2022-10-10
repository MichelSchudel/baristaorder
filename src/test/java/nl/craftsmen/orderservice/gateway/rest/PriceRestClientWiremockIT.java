package nl.craftsmen.orderservice.gateway.rest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import static org.assertj.core.api.Assertions.assertThat;

@RestClientTest(PriceRestClient.class)
@AutoConfigureWireMock(port = 0, stubs ="classpath:/stubs")
@Import(PriceRestClientWiremockIT.WiremockConfiguration.class)
class PriceRestClientWiremockIT {

    private Logger logger = LoggerFactory.getLogger(PriceRestClientWiremockIT.class);

    @Autowired
    private PriceRestClient priceRestClient;

    @Test
    void should_retrieve_price_from_external_service() {
        final double price = priceRestClient.getPrice("espresso");
        logger.info("espresso price: " + price);
        assertThat(price).isEqualTo(2);
    }

    @TestConfiguration
    public static class WiremockConfiguration {

        @Value("${wiremock.server.port}")
        String port;

        @Bean
        @Primary
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().rootUri("http://localhost:" + port);
        }

    }
}
