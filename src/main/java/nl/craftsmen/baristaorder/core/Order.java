package nl.craftsmen.baristaorder.core;

import lombok.Builder;

@Builder(toBuilder=true)
public record Order(
        Long id,
        String customer,
        String name,
        double price
)  {}
