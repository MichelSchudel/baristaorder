package nl.craftsmen.baristaorder.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class OrderEntity {
    @Id
    private Long id;
    private String name;

}
