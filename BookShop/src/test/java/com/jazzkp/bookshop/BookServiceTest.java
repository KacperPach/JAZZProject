package com.jazzkp.bookshop;

import com.bookapi.openapi.model.BookCreateRequest;
import com.bookapi.openapi.model.BookOrder;
import com.bookapi.openapi.model.BookResponse;
import com.jazzkp.bookshop.dto.Author;
import com.jazzkp.bookshop.dto.Book;
import com.jazzkp.bookshop.exception.InvalidAuthorIdException;
import com.jazzkp.bookshop.exception.InvalidBookCreateRequestException;
import com.jazzkp.bookshop.exception.InvalidBookIdException;
import com.jazzkp.bookshop.feignClients.OrderClient;
import com.jazzkp.bookshop.mapper.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private OrderClient orderClient;

    @InjectMocks
    private BookService bookService;

    private BookCreateRequest bookCreateRequest;
    private Book book;
    private BookResponse bookResponse;
    private UUID bookId;
    private UUID authorId;
    private Author author;

    @BeforeEach
    void setUp() {
        bookId = UUID.randomUUID();
        authorId = UUID.randomUUID();

        bookCreateRequest = new BookCreateRequest();
        bookCreateRequest.setAuthorId(authorId);
        bookCreateRequest.setAvailibleCopies(10);
        bookCreateRequest.setGenere("Fiction");
        bookCreateRequest.setPrice(19.99);
        bookCreateRequest.setNumOfPages(300);

        author = new Author();
        author.setId(authorId);

        book = new Book();
        book.setId(bookId);
        book.setAuthor(author);

        bookResponse = new BookResponse();
        bookResponse.setId(bookId);
    }

    @Test
    void testAddBook() {
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(bookMapper.mapToBook(any(BookCreateRequest.class))).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookMapper.mapToBookResponse(any(Book.class))).thenReturn(bookResponse);

        ResponseEntity<BookResponse> response = bookService.addBook(bookCreateRequest);

        assertNotNull(response);
        assertEquals(bookId, response.getBody().getId());
    }

    @Test
    void testAddBookInvalidRequest() {
        bookCreateRequest.setAuthorId(null);

        assertThrows(InvalidBookCreateRequestException.class, () -> bookService.addBook(bookCreateRequest));
    }

    @Test
    void testFindByGenre() {
        List<Book> books = List.of(book);
        when(bookRepository.findBooksByGenere("Fiction")).thenReturn(books);
        when(bookMapper.mapToBookResponse(book)).thenReturn(bookResponse);

        ResponseEntity<List<BookResponse>> response = bookService.findByGenre("Fiction");

        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testDeleteBook() {
        when(bookRepository.existsById(bookId)).thenReturn(true);

        ResponseEntity<Void> response = bookService.deleteBook(bookId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    void testDeleteBookInvalidId() {
        when(bookRepository.existsById(bookId)).thenReturn(false);

        assertThrows(InvalidBookIdException.class, () -> bookService.deleteBook(bookId));
    }

    @Test
    void testGetBook() {
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.existsById(bookId)).thenReturn(true);
        when(bookMapper.mapToBookResponse(book)).thenReturn(bookResponse);

        ResponseEntity<BookResponse> response = bookService.getBook(bookId);

        assertNotNull(response);
        assertEquals(bookId, response.getBody().getId());
    }

    @Test
    void testGetBookInvalidId() {
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        when(bookRepository.existsById(bookId)).thenReturn(true);

        assertThrows(InvalidBookIdException.class, () -> bookService.getBook(bookId));
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(book);
        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.mapToBookResponse(book)).thenReturn(bookResponse);

        ResponseEntity<List<BookResponse>> response = bookService.getbook();

        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }


}