package com.jazzkp.bookshop;

import com.jazzkp.bookshop.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("SELECT b FROM Book b WHERE UPPER(b.genere) like UPPER(:genere)")
    List<Book> findBooksByGenere(@Param("genere") String genere);
}
