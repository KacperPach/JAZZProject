package com.jazzkp.bookshop;

import com.jazzkp.bookshop.dto.BookOrder;
import dto.BookOrderRequest;

import exception.NoBookIDException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    ResponseEntity<Void> receiveOrder (List<BookOrderRequest> orderRequests) {
        orderRequests.forEach( b -> {
            if (b.getNumOfVisits() > 9){
                if(b.getBookId() == null)
                    throw new NoBookIDException();
                BookOrder bo = new BookOrder();
                bo.setBookId(b.getBookId());
                bo.setOrderAmount((int)b.getNumOfVisits()/2);
                orderRepository.save(bo);
            }
        });
        return ResponseEntity.ok().build();
    }

}
