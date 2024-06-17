package dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BookOrderRequest {
    private UUID bookId;
    private String bookName;
    private Integer numOfVisits;
}
