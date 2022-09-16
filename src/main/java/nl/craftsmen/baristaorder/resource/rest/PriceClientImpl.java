package nl.craftsmen.baristaorder.resource.rest;

import nl.craftsmen.baristaorder.core.PriceProvider;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.requireNonNull;

@Service
public class PriceClientImpl implements PriceProvider {

    private final RestTemplate restTemplate;

    public PriceClientImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public double getPrice(String name) {
        return requireNonNull(restTemplate.getForObject("/price", PriceResponseModel.class, name)).getPrice();
    }
}
