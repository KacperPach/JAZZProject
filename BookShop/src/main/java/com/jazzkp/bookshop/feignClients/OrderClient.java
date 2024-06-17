package com.jazzkp.bookshop.feignClients;

import dto.BookOrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value ="order",  url = "localhost:8080")
public interface OrderClient {
    @PostMapping("/order/sendOrder")
    ResponseEntity<Void> sendOrder (List<BookOrderRequest> orderRequests);
}
