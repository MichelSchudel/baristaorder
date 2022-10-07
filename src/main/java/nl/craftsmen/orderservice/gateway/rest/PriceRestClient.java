package nl.craftsmen.orderservice.gateway.rest;

import nl.craftsmen.orderservice.core.PriceProvider;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.requireNonNull;

@Repository
public class PriceRestClient implements PriceProvider {

    private final RestTemplate restTemplate;

    public PriceRestClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public double getPrice(String name) {
        return requireNonNull(restTemplate.getForObject("/price/{name}", PriceResponseModel.class, name)).getPrice();
    }
}
