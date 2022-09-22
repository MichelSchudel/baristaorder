package nl.craftsmen.baristaorder.control;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import nl.craftsmen.baristaorder.core.Order;
import nl.craftsmen.baristaorder.core.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    private OrderService orderService;

    @Autowired
    private WebApplicationContext applicationContext;

    @BeforeEach
    void setup() {
        //alternatively, use MockMvc here
        RestAssuredMockMvc.webAppContextSetup(applicationContext);
    }

    @Test
    void test_get() {
        //set up mock order service
        when(orderService.getOrder(any())).thenReturn(Order.builder().id(1L).name("espresso").price(2.5).build());

        //do a call to the web layer
        given()
                .param("name", "espresso")
                .when()
                .get("/orders")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.OK.value())
                .contentType(JSON)
                .body("name", equalTo("espresso"))
                .body("price", equalTo(2.5F));
    }

    @Test
    void test_post() {
        //set up mock order service
        when(orderService.saveNewOrder(any())).thenReturn(
                Order.builder()
                        .id(1L)
                        .name("espresso")
                        .price(2.5)
                        .customer("Michel")
                        .build());

        OrderRequestModel orderRequestModel = OrderRequestModel.builder()
                .customer("Michel")
                .name("espresso")
                .build();
        //do a call to the web layer
        given()
                .body(orderRequestModel)
                .when()
                .post("/orders")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.OK.value())
                .contentType(JSON)
                .body("name", equalTo("espresso"))
                .body("price", equalTo(2.5F));
    }

    @Test
    void test_post_400() {
        //build an invalid model
        OrderRequestModel orderRequestModel = OrderRequestModel.builder().build();
        //do a call to the web layer
        given()
                .body(orderRequestModel)
                .when()
                .post("/orders")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
