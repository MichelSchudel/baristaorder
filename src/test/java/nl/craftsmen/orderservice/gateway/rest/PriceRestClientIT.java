package nl.craftsmen.orderservice.gateway.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(PriceRestClient.class)
class PriceRestClientIT {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private PriceRestClient priceRestClient;

    @Test
    void should_retrieve_price_from_external_service() {

        this.server.expect(requestTo("/price/espresso"))
                .andRespond(withSuccess("{ \"price\": 2}", MediaType.APPLICATION_JSON));

        assertThat(priceRestClient.getPrice("espresso")).isEqualTo(2);
    }

}
