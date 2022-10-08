package nl.craftsmen.orderservice.core;

import lombok.Builder;

@Builder(toBuilder = true)
public record Order(
        Long id,
        String customer,
        String name,
        Long price
) {
}
