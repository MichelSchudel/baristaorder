package nl.craftsmen.baristaorder.control;

import lombok.RequiredArgsConstructor;
import nl.craftsmen.baristaorder.core.Order;
import nl.craftsmen.baristaorder.core.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderModelMapper orderModelMapper = new OrderModelMapper();
    private final OrderService orderService;

    @GetMapping
    public OrderResponseModel getOrder(@RequestParam("name") String name) {
        return orderModelMapper.toOrderResponseModel(orderService.getOrder(name));
    }

    @PostMapping
    public OrderResponseModel saveOrder(@Valid OrderRequestModel orderRequestModel) {
        Order order = orderModelMapper.toOrder(orderRequestModel);
        return orderModelMapper.toOrderResponseModel(orderService.saveNewOrder(order));
    }
}
