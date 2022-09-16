package nl.craftsmen.baristaorder.control;

import lombok.RequiredArgsConstructor;
import nl.craftsmen.baristaorder.core.Order;
import nl.craftsmen.baristaorder.core.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Order getOrder(@RequestParam("name") String name) {
        return orderService.getOrder(name);
    }

    @PostMapping
    public Order saveOrder(Order order) {
        return orderService.saveNewOrder(order);
    }
}
