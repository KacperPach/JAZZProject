import dto.BookOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private final OrderService service;

    @PostMapping("/sendOrder")
    ResponseEntity<Void> sendOrder (List<BookOrderRequest> orderRequests) {
       return service.receiveOrder(orderRequests);
    }

}
