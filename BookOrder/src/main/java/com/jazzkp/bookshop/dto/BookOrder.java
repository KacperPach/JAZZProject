package com.jazzkp.bookshop.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
public class BookOrder {
    @Id
    @UuidGenerator
    private UUID bookId;
    private Integer orderAmount;
}
