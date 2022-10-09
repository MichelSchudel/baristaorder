package nl.craftsmen.orderservice.presentation;

import lombok.RequiredArgsConstructor;
import nl.craftsmen.orderservice.core.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    //maps request and response models to domain objects
    private final OrderModelMapper orderModelMapper = new OrderModelMapper();

    private final OrderService orderService;

    @PostMapping
    public OrderResponseModel saveOrder(@Valid @RequestBody OrderRequestModel orderRequestModel) {
        //map request model to domain model
        var order = orderModelMapper.toOrder(orderRequestModel);

        //save the order
        var persistedOrder = orderService.saveNewOrder(order);

        //convert to response model
        return orderModelMapper.toOrderResponseModel(persistedOrder);
    }

    @GetMapping("/{id}")
    public OrderResponseModel getOrder(@PathVariable("id") Long id) {
        //get the order
        var order = orderService.getOrder(id);

        //convert to response model
        return orderModelMapper.toOrderResponseModel(order);
    }
}
