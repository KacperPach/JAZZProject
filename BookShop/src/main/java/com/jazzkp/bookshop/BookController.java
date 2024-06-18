package com.jazzkp.bookshop;

import com.bookapi.openapi.api.BookControllerApi;
import com.bookapi.openapi.model.BookCreateRequest;
import com.bookapi.openapi.model.BookOrder;
import com.bookapi.openapi.model.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController implements BookControllerApi {

    @Autowired
    private final BookService service;

    @Override
    @PostMapping("/addBook")
    public ResponseEntity<BookResponse> createbook(BookCreateRequest bookCreateRequest) {
        return service.addBook(bookCreateRequest);
    }

    @Override
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        return service.deleteBook(id);
    }

    @Override
    @GetMapping("/getBook/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable UUID id) {
        return service.getBook(id);
    }

    @Override
    @GetMapping("/getBook")
    public ResponseEntity<List<BookResponse>> getbook() {
        return service.getbook();
    }

    @Override
    @GetMapping("/getBookByGenre/{gen}")
    public ResponseEntity<List<BookResponse>> getBookByGenre(@PathVariable String gen) {
        return service.findByGenre(gen);
    }

    @Override
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable UUID id, BookCreateRequest bookCreateRequest) {
        return service.updateBook(id,bookCreateRequest);
    }

    @Override
    @GetMapping("/orderBooks")
    public ResponseEntity<List<BookOrder>> orderBooks() {
        return service.sendOrder();
    }
}
