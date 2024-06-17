import dto.BookOrderRequest;
import dto.Order;
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
                orderRepository.save(new Order(b.getBookId(), b.getNumOfVisits() % 10));
            }
        });
        return ResponseEntity.ok().build();
    }

}
