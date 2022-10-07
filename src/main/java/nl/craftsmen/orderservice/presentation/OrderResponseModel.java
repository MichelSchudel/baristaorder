package nl.craftsmen.orderservice.presentation;

import lombok.Builder;

@Builder(toBuilder = true)
public record OrderResponseModel(Long id,
                                 String customer,
                                 String name,
                                 long price
) {
}