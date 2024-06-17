package com.jazzkp.bookshop;

import com.bookapi.openapi.model.BookCreateRequest;
import com.bookapi.openapi.model.BookResponse;
import com.jazzkp.bookshop.dto.Author;
import com.jazzkp.bookshop.dto.Book;
import com.jazzkp.bookshop.exception.InvalidAuthorIdException;
import com.jazzkp.bookshop.exception.InvalidBookCreateRequestException;
import com.jazzkp.bookshop.exception.InvalidBookIdException;
import com.jazzkp.bookshop.feignClients.OrderClient;
import com.jazzkp.bookshop.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    @Autowired
    private final OrderClient orderClient;

    public ResponseEntity<BookResponse> addBook(BookCreateRequest bookCreateRequest) {
        validateBookCreateRequest(bookCreateRequest);

        Book book = bookMapper.mapToBook(bookCreateRequest);
        book.setAuthor(this.getAuthorFormId(bookCreateRequest.getAuthorId()));

        BookResponse body =  bookMapper.mapToBookResponse(bookRepository.save(book));

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<List<BookResponse>> findByGenre(String genre) {
        return ResponseEntity.ok(bookRepository.findBooksByGenere(genre).stream().map(bookMapper::mapToBookResponse).toList());
    }

    public ResponseEntity<Void> deleteBook(UUID id) {
        validateBookId(id);

        bookRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<BookResponse> getBook(UUID id) {
        validateBookId(id);

        Book book = bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        book.setNumOfVisits(book.getNumOfVisits()+1);
        bookRepository.save(book);

        BookResponse body = bookMapper.mapToBookResponse(book);

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<List<BookResponse>> getbook() {
        List<BookResponse> body = bookRepository.findAll().stream().map(bookMapper::mapToBookResponse).toList();

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<BookResponse> updateBook(UUID id, BookCreateRequest bookCreateRequest) {
        validateBookId(id);
        validateBookCreateRequest(bookCreateRequest);

        Book book = bookRepository.getReferenceById(id);
        Book newBook = bookMapper.mapToBook(bookCreateRequest);
        newBook.setAuthor(this.getAuthorFormId(bookCreateRequest.getAuthorId()));
        newBook.setId(book.getId());

        BookResponse body =  bookMapper.mapToBookResponse(
                bookRepository.save(newBook
                ));

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<Void> sendOrder() {
        return orderClient.sendOrder(bookRepository.findAll().stream().map(bookMapper::mapToBookOrderRequest).toList());
    }

    // Validation utilities
    private void validateBookCreateRequest(BookCreateRequest bookCreateRequest) {
        if (bookCreateRequest == null ||bookCreateRequest.getAuthorId() == null || bookCreateRequest.getAvailibleCopies() == null  || bookCreateRequest.getGenere() == null || bookCreateRequest.getPrice() == null || bookCreateRequest.getNumOfPages() == null)
            throw new InvalidBookCreateRequestException();
    }

    private void validateBookId(UUID uuid) {
        if (!bookRepository.existsById(uuid))
            throw new InvalidBookIdException();
    }

    // utils
    private Author getAuthorFormId(UUID uuid) {
        return authorRepository.findById(uuid).orElseThrow(InvalidAuthorIdException::new);
    }


}
