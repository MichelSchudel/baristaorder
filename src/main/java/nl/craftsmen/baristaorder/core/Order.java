package nl.craftsmen.baristaorder.core;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder=true)
public class Order {
    Long id;

    String customer;
    String name;
    double price;
}
