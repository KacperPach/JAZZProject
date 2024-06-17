package com.jazzkp.bookshop.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
public class Book {
    @Id
    @UuidGenerator
    private UUID id;
    // 🙏🙏🙏 do not mind the typos too far gone to fix now 🙏🙏🙏
    private String genere;
    private String bookName;
    private Double price;
    private Integer numOfPages;
    private Integer numOfVisits = 0;
    private Integer availibleCopies;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
