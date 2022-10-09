package nl.craftsmen.orderservice.presentation.internal;

import nl.craftsmen.orderservice.presentation.OrderResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {

    @GetMapping(value = "/price/{name}", produces = "application/json")
    public String getPrice(@PathVariable("name") String name) {
        return """
                { "price": 2 }
                """;
    }
}
