package com.jazzkp.bookshop;

import com.jazzkp.bookshop.dto.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthorRepository extends CrudRepository<Author, UUID> {
}
