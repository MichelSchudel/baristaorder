package nl.craftsmen.orderservice.presentation;

import lombok.RequiredArgsConstructor;
import nl.craftsmen.orderservice.core.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderModelMapper orderModelMapper = new OrderModelMapper();

    private final OrderService orderService;

    @PostMapping
    public OrderResponseModel saveOrder(@Valid @RequestBody OrderRequestModel orderRequestModel) {
        var order = orderModelMapper.toOrder(orderRequestModel);
        var persistedOrder = orderService.saveNewOrder(order);
        return orderModelMapper.toOrderResponseModel(persistedOrder);
    }

    @GetMapping
    public OrderResponseModel getOrder(@RequestParam("id") Long id) {
        var order = orderService.getOrder(id);
        return orderModelMapper.toOrderResponseModel(order);
    }
}
