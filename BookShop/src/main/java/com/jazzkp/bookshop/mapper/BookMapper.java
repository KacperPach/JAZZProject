package com.jazzkp.bookshop.mapper;

import com.bookapi.openapi.model.BookCreateRequest;
import com.bookapi.openapi.model.BookResponse;
import com.jazzkp.bookshop.dto.Book;
import dto.BookOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookMapper {
    /*  this is the weirdest thing I've ever seen
        sometimes mapstruct needs to be reminded
        that "id" indeed maps to "id", otherwise
        it will generate a mapper that just creates
        a new object with all children set to null.
        after a couple of compiles it may just
        start remembering that the line telling it
        to map id to id is stupid and should not be
        there and throw a compile error.
        this is why you my see this line commented
        out or not depending on how mapstruct is
        feeling rn. 🤓
        I GET IT NOW IT THINKS ITS NULL WHEN I
        MVN CLEAN INSTALL BECAUSE THE OBJECTS
        FROM OPENAPI GENERATE AT THE
        SAME TIME/AFTER THIS CLASS BUILDS SO
        IT ACTUALLY THINKS THE OBJECTS DON'T
        HAVE IDS 🤯🤯🤯 (maybe)
     */
    @Mapping(target = "id", source = "id")
    BookResponse mapToBookResponse(Book book);
    BookResponse mapToBookResponse(BookCreateRequest bookCreateRequest);

    Book mapToBook(BookCreateRequest bookCreateRequest);
    Book mapToBook(BookResponse bookResponse);

    BookCreateRequest mapToBookCreateRequest(Book bookDto);
    BookCreateRequest mapToBookCreateRequest(BookResponse bookResponse);

    @Mapping(target = "bookId", source = "id")
    BookOrderRequest mapToBookOrderRequest(Book bookDto);
}
