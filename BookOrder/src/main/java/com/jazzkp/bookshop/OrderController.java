package com.jazzkp.bookshop;

import com.jazzkp.bookshop.dto.BookOrder;
import dto.BookOrderRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private final OrderService service;

    @PostMapping("/sendOrder")
    ResponseEntity<List<BookOrder>> sendOrder (@RequestBody List<BookOrderRequest> orderRequests) {
       return service.receiveOrder(orderRequests);
    }

    @GetMapping("/print")
    public void exportOrder (HttpServletResponse response) {
        response.setContentType("application/pdf");
        String date = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss").format(new Date());

        response.setHeader("Content-Disposition", "attachment; filename=pdf_"+date+".pdf");
        service.exportOrder(response);
    }

}
