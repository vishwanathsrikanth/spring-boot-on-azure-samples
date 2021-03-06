package com.example.springsamples.orders;

import com.microsoft.applicationinsights.TelemetryClient;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private  OrdersService ordersService;

    @Autowired
    TelemetryClient client;

    public OrdersController(OrdersService ordersService)
    {
        this.ordersService = ordersService;
    }

    @GetMapping
    public ResponseEntity<List<OrderModel>> getOrdersByUserId(@RequestParam(name = "id")int id)
    {
        Optional<List<OrderModel>> orders;
        orders =  this.ordersService.findByUserId(id);
        return new ResponseEntity<List<OrderModel>>(orders.orElseGet(() -> null), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json", path = "/")
    public ResponseEntity<String> createOrder(@RequestBody OrderModel orderModel)
    {
        return new ResponseEntity<String>(ordersService.createOrder(orderModel), HttpStatus.CREATED);
    }


}
