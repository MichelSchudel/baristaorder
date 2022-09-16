package nl.craftsmen.baristaorder.core;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Order {
    Long id;
    String name;
}
