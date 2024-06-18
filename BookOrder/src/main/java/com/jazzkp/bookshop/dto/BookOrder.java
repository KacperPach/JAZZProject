package com.jazzkp.bookshop.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class BookOrder {
    @Id
    private UUID bookId;
    private Integer orderAmount;
}
