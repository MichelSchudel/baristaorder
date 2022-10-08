package nl.craftsmen.orderservice.presentation;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import nl.craftsmen.orderservice.core.NotFoundException;
import nl.craftsmen.orderservice.core.Order;
import nl.craftsmen.orderservice.core.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest
class OrderControllerIT {

    @MockBean
    private OrderService mockOrderService;

    @Autowired
    private WebApplicationContext applicationContext;

    @BeforeEach
    void setup() {
        //alternatively, use MockMvc here
        RestAssuredMockMvc.webAppContextSetup(applicationContext);
    }

    @Test
    void posting_order_should_result_in_order_response() {
        //set up mock order service
        when(mockOrderService.saveNewOrder(any())).thenReturn(
                Order.builder()
                        .id(1L)
                        .name("Design Patterns")
                        .price(2000)
                        .customer("Michel")
                        .build());

        var orderRequestModel = OrderRequestModel.builder()
                .customer("Michel")
                .name("Design Patterns")
                .build();

        //do a call to the web layer
        given()
                .body(orderRequestModel)
                .contentType(JSON)
                .when()
                .post("/orders")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.OK.value())
                .contentType(JSON)
                .body("name", equalTo("Design Patterns"))
                .body("price", equalTo(2000));
    }

    @Test
    void bad_order_model_should_result_in_bad_request() {
        //build an invalid model
        var orderRequestModel = OrderRequestModel.builder()
                .build();
        //do a call to the web layer
        given()
                .body(orderRequestModel)
                .contentType(JSON)
                .when()
                .post("/orders")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }



    @Test
    void get_existing_order() {
        //set up mock order service
        when(mockOrderService.getOrder(any()))
                .thenReturn(
                        Order.builder()
                                .id(1L)
                                .name("Design Patterns")
                                .price(2000)
                                .customer("Michel")
                                .build());

        //do a call to the web layer
        given()
                .when()
                .get("/orders/1")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.OK.value())
                .contentType(JSON)
                .body("name", equalTo("Design Patterns"))
                .body("price", equalTo(2000));
    }

}
