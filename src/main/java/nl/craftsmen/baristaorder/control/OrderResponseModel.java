package nl.craftsmen.baristaorder.control;

import lombok.Builder;

@Builder(toBuilder=true)
public record OrderResponseModel(Long id,
                                 String customer,
                                 String name,
                                 double price
) {}