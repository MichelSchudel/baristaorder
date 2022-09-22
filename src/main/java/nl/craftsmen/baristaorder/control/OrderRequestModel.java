package nl.craftsmen.baristaorder.control;

import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder(toBuilder=true)
public record OrderRequestModel(@NotNull String customer,
                                @NotNull String name) {
}
