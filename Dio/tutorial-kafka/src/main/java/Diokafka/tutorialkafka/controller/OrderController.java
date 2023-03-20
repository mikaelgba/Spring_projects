package Diokafka.tutorialkafka.controller;

import Diokafka.tutorialkafka.data.OrderKafka;
import Diokafka.tutorialkafka.services.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService eventService;

    @PostMapping(path = "/api/save-order")
    public ResponseEntity<String> SaveOrder(@RequestBody OrderKafka order){
        eventService.addEvent("Save Order", order);
        return ResponseEntity.ok("Ok man");
    }
}
