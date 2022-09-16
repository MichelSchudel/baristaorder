package nl.craftsmen.baristaorder.resource.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(PriceClientImpl.class)
class SimpleClientIT {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private PriceClientImpl simpleClient;

    @Test
    void test() {
        this.server.expect(requestTo("/price"))
                .andRespond(withSuccess("{ \"price\": 2.50}", MediaType.APPLICATION_JSON));
        assertThat(simpleClient.getPrice("espresso")).isEqualTo(2.50);
    }

}
